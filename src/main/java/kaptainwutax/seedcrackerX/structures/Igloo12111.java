package kaptainwutax.seedcrackerX.structures;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;
import com.seedfinding.mcfeature.structure.RegionStructure;
import com.seedfinding.mcfeature.structure.TriangularStructure;

/**
 * Igloo с переопределёнными параметрами для 1.21.11.
 *
 * Изменения worldgen 1.21.11:
 * - spacing: 32 → 32 (без изменений)
 * - separation: 8 → 5
 * - salt: 14357618 → 18593021
 */
public class Igloo12111 extends TriangularStructure<Igloo12111> {

    public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
            .add(MCVersion.v1_21, new RegionStructure.Config(32, 5, 18593021));

    public Igloo12111(MCVersion version) {
        super(CONFIGS.getAsOf(version), version);
    }

    @Override
    public String getName() {
        return "igloo";
    }

    @Override
    public Dimension getValidDimension() {
        return Dimension.OVERWORLD;
    }

    @Override
    public boolean isValidBiome(Biome biome) {
        return biome == Biomes.SNOWY_TUNDRA || biome == Biomes.SNOWY_TAIGA;
    }
}
