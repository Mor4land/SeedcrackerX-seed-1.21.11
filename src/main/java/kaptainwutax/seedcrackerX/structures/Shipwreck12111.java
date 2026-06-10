package kaptainwutax.seedcrackerX.structures;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;
import com.seedfinding.mcfeature.structure.RegionStructure;
import com.seedfinding.mcfeature.structure.UniformStructure;

/**
 * Shipwreck (Кораблекрушение) с переопределёнными параметрами для 1.21.11.
 *
 * Shipwreck — UniformStructure (равномерное распределение внутри региона).
 * Это критичная структура для Lifting-фазы крякера (pokeLifting).
 *
 * Изменения worldgen 1.21.11:
 * - spacing: 24 → 26
 * - separation: 4 → 6
 * - salt: 165745295 → 198273415
 */
public class Shipwreck12111 extends UniformStructure<Shipwreck12111> {

    public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
            .add(MCVersion.v1_21, new RegionStructure.Config(26, 6, 198273415));

    public Shipwreck12111(MCVersion version) {
        super(CONFIGS.getAsOf(version), version);
    }

    @Override
    public String getName() {
        return "shipwreck";
    }

    @Override
    public Dimension getValidDimension() {
        return Dimension.OVERWORLD;
    }

    @Override
    public boolean isValidBiome(Biome biome) {
        return biome == Biomes.BEACH || biome == Biomes.SNOWY_BEACH
                || biome == Biomes.OCEAN || biome == Biomes.DEEP_OCEAN
                || biome == Biomes.COLD_OCEAN || biome == Biomes.DEEP_COLD_OCEAN
                || biome == Biomes.FROZEN_OCEAN || biome == Biomes.DEEP_FROZEN_OCEAN
                || biome == Biomes.LUKEWARM_OCEAN || biome == Biomes.DEEP_LUKEWARM_OCEAN
                || biome == Biomes.WARM_OCEAN || biome == Biomes.DEEP_WARM_OCEAN;
    }
}
