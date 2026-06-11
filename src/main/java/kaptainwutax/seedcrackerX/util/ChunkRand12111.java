package kaptainwutax.seedcrackerX.util;

import com.seedfinding.mccore.rand.ChunkRand;
import kaptainwutax.seedcrackerX.SeedCracker;

import java.lang.reflect.Field;

public class ChunkRand12111 extends ChunkRand {
    private static Field xoroshiroField;
    private static Field loField;
    private static Field hiField;
    private static boolean reflectionInitialized = false;
    private static boolean reflectionFailed = false;

    private static void initReflection() {
        if (reflectionInitialized || reflectionFailed) return;
        try {
            Class<?> current = ChunkRand.class;
            while (current != null) {
                for (Field field : current.getDeclaredFields()) {
                    if (field.getType().getName().toLowerCase().contains("xoroshiro")) {
                        xoroshiroField = field;
                        break;
                    }
                }
                if (xoroshiroField != null) break;
                current = current.getSuperclass();
            }

            if (xoroshiroField == null) {
                throw new NoSuchFieldException("No field of type *Xoroshiro* found in ChunkRand hierarchy.");
            }
            xoroshiroField.setAccessible(true);
            
            Class<?> xoroshiroClass = xoroshiroField.getType();
            Field[] fields = xoroshiroClass.getDeclaredFields();
            int longCount = 0;
            for (Field f : fields) {
                if (f.getType() == long.class) {
                    if (longCount == 0) loField = f;
                    else if (longCount == 1) hiField = f;
                    longCount++;
                }
            }
            if (loField != null) loField.setAccessible(true);
            if (hiField != null) hiField.setAccessible(true);
            
            reflectionInitialized = true;
            SeedCracker.LOGGER.info("[ChunkRand12111] Рефлексия Xoroshiro успешно инициализирована! Поле: " + xoroshiroField.getName());
        } catch (Exception e) {
            SeedCracker.LOGGER.error("[ChunkRand12111] Ошибка инициализации рефлексии Xoroshiro: " + e.getMessage());
            
            // Если не нашли, выведем все поля для отладки:
            Class<?> dbg = ChunkRand.class;
            while(dbg != null) {
                for(Field f : dbg.getDeclaredFields()) {
                    SeedCracker.LOGGER.error("DEBUG FIELD: class=" + dbg.getSimpleName() + " name=" + f.getName() + " type=" + f.getType().getSimpleName());
                }
                dbg = dbg.getSuperclass();
            }
            
            reflectionFailed = true;
        }
    }

    public ChunkRand12111() {
        super();
        initReflection();
    }

    public ChunkRand12111(long seed) {
        super(seed);
        initReflection();
    }

    public ChunkRand12111(long seed, boolean scramble) {
        super(seed, scramble);
        initReflection();
    }

    @Override
    public void setSeed(long seed) {
        // Вызываем оригинальный метод, который инициализирует xoroshiro по старым правилам
        super.setSeed(seed);
        
        // Перезаписываем состояние Xoroshiro по правилам 1.21.11
        if (reflectionInitialized && !reflectionFailed) {
            try {
                Object xoroshiroInstance = xoroshiroField.get(this);
                if (xoroshiroInstance != null) {
                    long lo = Xoroshiro12111.computeLo(seed);
                    long hi = Xoroshiro12111.computeHi(seed);
                    loField.setLong(xoroshiroInstance, lo);
                    hiField.setLong(xoroshiroInstance, hi);
                }
            } catch (Exception e) {
                // Игнорируем ошибки при частых вызовах
            }
        }
    }
}
