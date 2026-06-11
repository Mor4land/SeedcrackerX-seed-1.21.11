package kaptainwutax.seedcrackerX.util;

import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.version.MCVersion;
import kaptainwutax.seedcrackerX.SeedCracker;

/**
 * Тестовый скрипт для отладки ChunkRand поведения.
 * Вызвать через: DebugChunkRand.debugLifting()
 */
public class DebugChunkRand {

    public static void main(String[] args) {
        // Заглушка для логгера, если мы запускаем вне майнкрафта
        System.out.println("Starting debug script...");
        debugLifting();
    }

    public static void debugLifting() {
        System.out.println("=== DEBUG ChunkRand Lifting ===");
        
        // Тест: для заданных regionX, regionZ и salt, 
        // покажем что выдает nextInt() с и без доп. шага
        int[] testRegionXs = {0, 1, -1, 5};
        int[] testRegionZs = {0, 1, -1, 3};
        int salt = 198273415; // Shipwreck12111 salt
        int offset = 20; // spacing(26) - separation(6) 
        
        for (int i = 0; i < testRegionXs.length; i++) {
            int rx = testRegionXs[i];
            int rz = testRegionZs[i];
            
            // Без доп. шага
            ChunkRand rand1 = new ChunkRand();
            rand1.setRegionSeed(0L, rx, rz, salt, MCVersion.v1_21_3);
            int ox1 = rand1.nextInt(offset);
            int oz1 = rand1.nextInt(offset);
            
            // С доп. шагом  
            ChunkRand12111 rand2 = new ChunkRand12111();
            rand2.setRegionSeed(0L, rx, rz, salt, MCVersion.v1_21_3);
            int ox2 = rand2.nextInt(offset);
            int oz2 = rand2.nextInt(offset);
            
            System.out.println("region(" + rx + "," + rz + ") salt=" + salt + ": WITHOUT extra step: offset=(" + ox1 + "," + oz1 + ") | WITH extra step: offset=(" + ox2 + "," + oz2 + ")");
        }
        
        // Также выведем все поля ChunkRand для понимания внутренней структуры
        System.out.println("=== ChunkRand class hierarchy ===");
        Class<?> cls = ChunkRand.class;
        while (cls != null) {
            System.out.println("Class: " + cls.getName() + " (superclass: " + (cls.getSuperclass() != null ? cls.getSuperclass().getName() : "null") + ")");
            for (java.lang.reflect.Field f : cls.getDeclaredFields()) {
                System.out.println("  field: " + f.getName() + " type=" + f.getType().getName());
            }
            for (java.lang.reflect.Method m : cls.getDeclaredMethods()) {
                if (m.getName().contains("Region") || m.getName().contains("region") || 
                    m.getName().contains("setSeed") || m.getName().contains("nextInt")) {
                    StringBuilder params = new StringBuilder();
                    for (Class<?> p : m.getParameterTypes()) {
                        if (params.length() > 0) params.append(", ");
                        params.append(p.getSimpleName());
                    }
                    System.out.println("  method: " + m.getReturnType().getSimpleName() + " " + m.getName() + "(" + params + ")");
                }
            }
            cls = cls.getSuperclass();
        }
        
        SeedCracker.LOGGER.info("=== END DEBUG ===");
    }
}
