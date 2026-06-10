package kaptainwutax.seedcrackerX.structures;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;
import com.seedfinding.mcfeature.structure.RegionStructure;
import com.seedfinding.mcfeature.structure.TriangularStructure;

/**
 * End City (Город Края) с переопределёнными параметрами для 1.21.11.
 *
 * Изменения worldgen 1.21.11:
 * - spacing: 20 → 22
 * - separation: 11 → 9
 * - salt: 10387313 → 14753189
 */
public class EndCity12111 extends TriangularStructure<EndCity12111> {

    public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
            .add(MCVersion.v1_21, new RegionStructure.Config(22, 9, 14753189));

    public EndCity12111(MCVersion version) {
        super(CONFIGS.getAsOf(version), version);
    }

    @Override
    public String getName() {
        return "end_city";
    }

    @Override
    public Dimension getValidDimension() {
        return Dimension.END;
    }

    @Override
    public boolean isValidBiome(Biome biome) {
        return biome == Biomes.END_MIDLANDS || biome == Biomes.END_HIGHLANDS;
    }
}
