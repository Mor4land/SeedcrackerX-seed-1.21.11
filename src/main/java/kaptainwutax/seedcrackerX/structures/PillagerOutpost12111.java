package kaptainwutax.seedcrackerX.structures;

import com.seedfinding.mcbiome.biome.Biome;
import com.seedfinding.mcbiome.biome.Biomes;
import com.seedfinding.mccore.state.Dimension;
import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mccore.version.VersionMap;
import com.seedfinding.mcfeature.structure.RegionStructure;
import com.seedfinding.mcfeature.structure.TriangularStructure;

/**
 * Pillager Outpost с переопределёнными параметрами для 1.21.11.
 *
 * Изменения worldgen 1.21.11:
 * - spacing: 32 → 34
 * - separation: 8 → 10
 * - salt: 165745296 → 192837452
 *
 * Примечание: PillagerOutpost в крякере исключается из основного поиска
 * (см. TimeMachine.pokeStructures — instanceof PillagerOutpost), поэтому
 * обновление salt критично в основном для pokeLifting и pokeStructureReduce.
 */
public class PillagerOutpost12111 extends TriangularStructure<PillagerOutpost12111> {

    public static final VersionMap<RegionStructure.Config> CONFIGS = new VersionMap<RegionStructure.Config>()
            .add(MCVersion.v1_21, new RegionStructure.Config(34, 10, 192837452));

    public PillagerOutpost12111(MCVersion version) {
        super(CONFIGS.getAsOf(version), version);
    }

    @Override
    public String getName() {
        return "pillager_outpost";
    }

    @Override
    public Dimension getValidDimension() {
        return Dimension.OVERWORLD;
    }

    @Override
    public boolean isValidBiome(Biome biome) {
        return biome == Biomes.PLAINS || biome == Biomes.DESERT
                || biome == Biomes.SAVANNA || biome == Biomes.TAIGA
                || biome == Biomes.SNOWY_TUNDRA || biome == Biomes.FROZEN_OCEAN;
    }
}
