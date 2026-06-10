package kaptainwutax.seedcrackerX.structures;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;
import com.seedfinding.mcfeature.structure.RegionStructure;

/**
 * Buried Treasure (Зарытый клад) с переопределёнными параметрами для 1.21.11.
 *
 * BuriedTreasure использует специфичную логику placement (не TriangularStructure,
 * а RegionStructure с fixed-позицией внутри чанка). Наследуем напрямую от RegionStructure.
 *
 * Изменения worldgen 1.21.11:
 * - spacing: 1 → 1 (без изменений — каждый чанк)
 * - separation: 0 → 0 (без изменений)
 * - salt: 10387320 → 14753196
 */
public class BuriedTreasure12111 extends RegionStructure<RegionStructure.Config, RegionStructure.Data<?>> {

    public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
            .add(MCVersion.v1_21, new RegionStructure.Config(1, 0, 14753196));

    public BuriedTreasure12111(MCVersion version) {
        super(CONFIGS.getAsOf(version), version);
    }

    @Override
    public String getName() {
        return "buried_treasure";
    }

    @Override
    public Dimension getValidDimension() {
        return Dimension.OVERWORLD;
    }

    @Override
    public boolean isValidBiome(Biome biome) {
        return biome == Biomes.BEACH || biome == Biomes.SNOWY_BEACH;
    }
}
