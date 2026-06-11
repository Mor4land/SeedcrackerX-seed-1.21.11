package kaptainwutax.seedcrackerX.util;

import com.seedfinding.mccore.rand.ChunkRand;
import com.seedfinding.mccore.version.MCVersion;

/**
 * Кастомный генератор для Minecraft 1.21.11.
 * 
 * В 1.21.11 генерация позиций структур (RegionStructure) всё ещё использует LCG (LegacyRandomSource).
 * Однако перед вычислением offsetX и offsetZ был добавлен дополнительный шаг генератора (LCG advance).
 * Мы переопределяем setRegionSeed, чтобы сымитировать этот дополнительный шаг.
 */
public class ChunkRand12111 extends ChunkRand {

    public ChunkRand12111() {
        super();
    }

    public ChunkRand12111(long seed) {
        super(seed);
    }

    public ChunkRand12111(long seed, boolean scramble) {
        super(seed, scramble);
    }

    @Override
    public long setRegionSeed(long worldSeed, int regionX, int regionZ, int salt, MCVersion version) {
        long result = super.setRegionSeed(worldSeed, regionX, regionZ, salt, version);
        
        // В 1.21.11 добавлен дополнительный холостой шаг LCG при генерации позиций структур.
        this.nextInt();
        
        return result;
    }
}
