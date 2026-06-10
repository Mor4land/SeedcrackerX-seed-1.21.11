package kaptainwutax.seedcrackerX.structures;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;
import com.seedfinding.mcfeature.structure.RegionStructure;
import com.seedfinding.mcfeature.structure.TriangularStructure;

/**
 * Desert Pyramid с переопределёнными параметрами для 1.21.11.
 *
 * Изменения worldgen 1.21.11:
 * - spacing: 32 → 34
 * - separation: 8 → 10
 * - salt: 14357617 → 20485731
 */
public class DesertPyramid12111 extends TriangularStructure<DesertPyramid12111> {

    public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
            .add(MCVersion.v1_21, new RegionStructure.Config(34, 10, 20485731));

    public DesertPyramid12111(MCVersion version) {
        super(CONFIGS.getAsOf(version), version);
    }

    @Override
    public String getName() {
        return "desert_pyramid";
    }

    @Override
    public Dimension getValidDimension() {
        return Dimension.OVERWORLD;
    }

    @Override
    public boolean isValidBiome(Biome biome) {
        return biome == Biomes.DESERT || biome == Biomes.DESERT_HILLS || biome == Biomes.DESERT_LAKES;
    }
}
