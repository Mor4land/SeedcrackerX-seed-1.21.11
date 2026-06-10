package kaptainwutax.seedcrackerX.structures;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;
import com.seedfinding.mcfeature.structure.RegionStructure;
import com.seedfinding.mcfeature.structure.TriangularStructure;

/**
 * Swamp Hut с переопределёнными параметрами для 1.21.11.
 *
 * Изменения worldgen 1.21.11:
 * - spacing: 32 → 34
 * - separation: 8 → 10
 * - salt: 14357620 → 20485734
 */
public class SwampHut12111 extends TriangularStructure<SwampHut12111> {

    public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
            .add(MCVersion.v1_21, new RegionStructure.Config(34, 10, 20485734));

    public SwampHut12111(MCVersion version) {
        super(CONFIGS.getAsOf(version), version);
    }

    @Override
    public String getName() {
        return "swamp_hut";
    }

    @Override
    public Dimension getValidDimension() {
        return Dimension.OVERWORLD;
    }

    @Override
    public boolean isValidBiome(Biome biome) {
        return biome == Biomes.SWAMP || biome == Biomes.SWAMP_HILLS;
    }
}
