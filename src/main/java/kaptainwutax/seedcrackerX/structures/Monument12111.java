package kaptainwutax.seedcrackerX.structures;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;
import com.seedfinding.mcfeature.structure.RegionStructure;
import com.seedfinding.mcfeature.structure.TriangularStructure;

/**
 * Monument (Подводный храм) с переопределёнными параметрами для 1.21.11.
 *
 * Изменения worldgen 1.21.11:
 * - spacing: 32 → 34
 * - separation: 5 → 7
 * - salt: 10387313 → 15629481
 */
public class Monument12111 extends TriangularStructure<Monument12111> {

    public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
            .add(MCVersion.v1_21, new RegionStructure.Config(34, 7, 15629481));

    public Monument12111(MCVersion version) {
        super(CONFIGS.getAsOf(version), version);
    }

    @Override
    public String getName() {
        return "monument";
    }

    @Override
    public Dimension getValidDimension() {
        return Dimension.OVERWORLD;
    }

    @Override
    public boolean isValidBiome(Biome biome) {
        return biome == Biomes.DEEP_OCEAN || biome == Biomes.DEEP_COLD_OCEAN
                || biome == Biomes.DEEP_FROZEN_OCEAN || biome == Biomes.DEEP_LUKEWARM_OCEAN
                || biome == Biomes.DEEP_WARM_OCEAN;
    }
}
