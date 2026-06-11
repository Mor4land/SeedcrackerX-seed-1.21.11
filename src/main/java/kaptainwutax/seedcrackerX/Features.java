package kaptainwutax.seedcrackerX;

import com.seedfinding.mccore.version.MCVersion;
import com.seedfinding.mcfeature.Feature;
import com.seedfinding.mcfeature.decorator.DesertWell;
import com.seedfinding.mcfeature.decorator.EndGateway;
import com.seedfinding.mcfeature.structure.BuriedTreasure;
import com.seedfinding.mcfeature.structure.DesertPyramid;
import com.seedfinding.mcfeature.structure.EndCity;
import com.seedfinding.mcfeature.structure.Igloo;
import com.seedfinding.mcfeature.structure.JunglePyramid;
import com.seedfinding.mcfeature.structure.Monument;
import com.seedfinding.mcfeature.structure.PillagerOutpost;
import com.seedfinding.mcfeature.structure.RegionStructure;
import com.seedfinding.mcfeature.structure.Shipwreck;
import com.seedfinding.mcfeature.structure.SwampHut;
import kaptainwutax.seedcrackerX.cracker.decorator.DeepDungeon;
import kaptainwutax.seedcrackerX.cracker.decorator.Dungeon;
import kaptainwutax.seedcrackerX.cracker.decorator.EmeraldOre;
import kaptainwutax.seedcrackerX.cracker.decorator.WarpedFungus;
import kaptainwutax.seedcrackerX.finder.Finder;
import kaptainwutax.seedcrackerX.structures.BuriedTreasure12111;
import kaptainwutax.seedcrackerX.structures.DesertPyramid12111;
import kaptainwutax.seedcrackerX.structures.EndCity12111;
import kaptainwutax.seedcrackerX.structures.Igloo12111;
import kaptainwutax.seedcrackerX.structures.JunglePyramid12111;
import kaptainwutax.seedcrackerX.structures.Monument12111;
import kaptainwutax.seedcrackerX.structures.PillagerOutpost12111;
import kaptainwutax.seedcrackerX.structures.Shipwreck12111;
import kaptainwutax.seedcrackerX.structures.SwampHut12111;
import kaptainwutax.seedcrackerX.structures.TrialChambers;
import kaptainwutax.seedcrackerX.util.VersionHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Features {
    public static final ArrayList<RegionStructure<?, ?>> STRUCTURE_TYPES = new ArrayList<>();

    // Типы расширены до RegionStructure<?,?> для поддержки переопределений 1.21.11
    public static RegionStructure<?, ?> BURIED_TREASURE;
    public static RegionStructure<?, ?> DESERT_PYRAMID;
    public static RegionStructure<?, ?> END_CITY;
    public static RegionStructure<?, ?> JUNGLE_PYRAMID;
    public static RegionStructure<?, ?> MONUMENT;
    public static RegionStructure<?, ?> SHIPWRECK;
    public static RegionStructure<?, ?> SWAMP_HUT;
    public static RegionStructure<?, ?> PILLAGER_OUTPOST;
    public static RegionStructure<?, ?> IGLOO;
    public static TrialChambers TRIAL_CHAMBERS;

    public static EndGateway END_GATEWAY;
    public static DesertWell DESERT_WELL;
    public static EmeraldOre EMERALD_ORE;
    public static Dungeon DUNGEON;
    public static DeepDungeon DEEP_DUNGEON;
    public static WarpedFungus WARPED_FUNGUS;

    public static void init(MCVersion version) {
        STRUCTURE_TYPES.clear();

        boolean newWorldgen = false; // We proved that 1.21.11 actually uses vanilla parameters

        // Buried Treasure: 1.21.11 → salt=14753196
        if (newWorldgen) {
            BURIED_TREASURE = safe(STRUCTURE_TYPES, Finder.Type.BURIED_TREASURE, () -> new BuriedTreasure12111(version));
        } else {
            BURIED_TREASURE = safe(STRUCTURE_TYPES, Finder.Type.BURIED_TREASURE, () -> new BuriedTreasure(version));
        }

        // Desert Pyramid: 1.21.11 → spacing=34, sep=10, salt=20485731
        if (newWorldgen) {
            DESERT_PYRAMID = safe(STRUCTURE_TYPES, Finder.Type.DESERT_TEMPLE, () -> new DesertPyramid12111(version));
        } else {
            DESERT_PYRAMID = safe(STRUCTURE_TYPES, Finder.Type.DESERT_TEMPLE, () -> new DesertPyramid(version));
        }

        // End City: 1.21.11 → spacing=22, sep=9, salt=14753189
        if (newWorldgen) {
            END_CITY = safe(STRUCTURE_TYPES, Finder.Type.END_CITY, () -> new EndCity12111(version));
        } else {
            END_CITY = safe(STRUCTURE_TYPES, Finder.Type.END_CITY, () -> new EndCity(version));
        }

        // Jungle Pyramid: 1.21.11 → spacing=34, sep=10, salt=20485733
        if (newWorldgen) {
            JUNGLE_PYRAMID = safe(STRUCTURE_TYPES, Finder.Type.JUNGLE_TEMPLE, () -> new JunglePyramid12111(version));
        } else {
            JUNGLE_PYRAMID = safe(STRUCTURE_TYPES, Finder.Type.JUNGLE_TEMPLE, () -> new JunglePyramid(version));
        }

        // Monument: 1.21.11 → spacing=34, sep=7, salt=15629481
        if (newWorldgen) {
            MONUMENT = safe(STRUCTURE_TYPES, Finder.Type.MONUMENT, () -> new Monument12111(version));
        } else {
            MONUMENT = safe(STRUCTURE_TYPES, Finder.Type.MONUMENT, () -> new Monument(version));
        }

        // Shipwreck: 1.21.11 → spacing=26, sep=6, salt=198273415
        if (newWorldgen) {
            SHIPWRECK = safe(STRUCTURE_TYPES, Finder.Type.SHIPWRECK, () -> new Shipwreck12111(version));
        } else {
            SHIPWRECK = safe(STRUCTURE_TYPES, Finder.Type.SHIPWRECK, () -> new Shipwreck(version));
        }

        // Swamp Hut: 1.21.11 → spacing=34, sep=10, salt=20485734
        if (newWorldgen) {
            SWAMP_HUT = safe(STRUCTURE_TYPES, Finder.Type.SWAMP_HUT, () -> new SwampHut12111(version));
        } else {
            SWAMP_HUT = safe(STRUCTURE_TYPES, Finder.Type.SWAMP_HUT, () -> new SwampHut(version));
        }

        // Pillager Outpost: 1.21.11 → spacing=34, sep=10, salt=192837452
        if (newWorldgen) {
            PILLAGER_OUTPOST = safe(STRUCTURE_TYPES, Finder.Type.PILLAGER_OUTPOST, () -> new PillagerOutpost12111(version));
        } else {
            PILLAGER_OUTPOST = safe(STRUCTURE_TYPES, Finder.Type.PILLAGER_OUTPOST, () -> new PillagerOutpost(version));
        }

        // Igloo: 1.21.11 → spacing=32, sep=5, salt=18593021
        if (newWorldgen) {
            IGLOO = safe(STRUCTURE_TYPES, Finder.Type.IGLOO, () -> new Igloo12111(version));
        } else {
            IGLOO = safe(STRUCTURE_TYPES, Finder.Type.IGLOO, () -> new Igloo(version));
        }

        TRIAL_CHAMBERS = safe(STRUCTURE_TYPES, Finder.Type.TRIAL_CHAMBERS, () -> new TrialChambers(version));

        END_GATEWAY = safe(Finder.Type.END_GATEWAY, () -> new EndGateway(version));
        DESERT_WELL = safe(Finder.Type.DESERT_WELL, () -> new DesertWell(version));
        EMERALD_ORE = safe(Finder.Type.EMERALD_ORE, () -> new EmeraldOre(version));
        DUNGEON = safe(Finder.Type.DUNGEON, () -> new Dungeon(version));
        DEEP_DUNGEON = safe(Finder.Type.DUNGEON, () -> new DeepDungeon(version));
        WARPED_FUNGUS = safe(Finder.Type.WARPED_FUNGUS, () -> new WarpedFungus(version));

        STRUCTURE_TYPES.trimToSize();
    }

    private static <F extends Feature<?, ?>> F safe(Finder.Type finderType, Supplier<F> lambda) {
        try {
            return lambda.get();
        } catch (Throwable t) {
            SeedCracker.LOGGER.error("Exception thrown loading feature", t);
            finderType.enabled.set(false);
            return null;
        }
    }

    private static <F extends RegionStructure<?, ?>> F safe(List<RegionStructure<?, ?>> list, Finder.Type finderType, Supplier<F> lambda) {
        F initializedFeature = safe(finderType, lambda);
        if (initializedFeature != null) list.add(initializedFeature);
        return initializedFeature;
    }

}
