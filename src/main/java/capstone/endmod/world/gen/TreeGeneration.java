package capstone.endmod.world.gen;

import net.minecraft.data.worldgen.features.EndFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;

import static capstone.endmod.init.BlockInit.*;
import static net.minecraft.data.worldgen.placement.PlacementUtils.HEIGHTMAP_WORLD_SURFACE;

public class TreeGeneration extends AbstractGeneration
{
    public static PlacedFeature MEGA_END_TREE_GENERATION;
    public static PlacedFeature END_TREE_GENERATION;
    public static PlacedFeature END_SMALL_TREE_GENERATION;
    public static PlacedFeature CHORUS_PLANT_GENERATION;


    public static void configureFeatures()
    {
        configureMegaEndTree();
        configureEndTree();
        configureEndSmallTree();
        configureChorusPlant();
    }

    private static void configureChorusPlant()
    {
        CHORUS_PLANT_GENERATION = PlacementUtils.register("chorus_plant",
                EndFeatures.CHORUS_PLANT.placed(CountPlacement.of(UniformInt.of(0, 4)),
                        InSquarePlacement.spread(),
                        HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()));
        addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CHORUS_PLANT_GENERATION);
    }

    private static void configureMegaEndTree()
    {
        int baseHeight = 24;
        int heightRandA = 2;
        int heightRandB = 19;
        int rairity = 2;
        TreeConfiguration treeConfig = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(END_TREE_TRUNK_BLOCK.get()), //Trunk
                new MegaJungleTrunkPlacer(baseHeight, heightRandA, heightRandB),
                BlockStateProvider.simple(END_TREE_LEAVES_BLOCK.get()), //Leaves
                new MegaJungleFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2),
                new TwoLayersFeatureSize(1, 1, 2))
                .dirt(BlockStateProvider.simple(Blocks.END_STONE)).build();
        MEGA_END_TREE_GENERATION = registerPlacedFeature("mega_end_tree", Feature.TREE.configured(treeConfig),
                CountPlacement.of(1),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                RarityFilter.onAverageOnceEvery(rairity),
                HEIGHTMAP_WORLD_SURFACE);

        addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MEGA_END_TREE_GENERATION);
    }

    private static void configureEndTree()
    {
        int baseHeight = 12;
        int heightRandA = 2;
        int heightRandB = 2;
        int rairity = 2;
        TreeConfiguration treeConfig = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(END_TREE_TRUNK_BLOCK.get()),
                new ForkingTrunkPlacer(baseHeight, heightRandA, heightRandB),
                BlockStateProvider.simple(END_SMALL_TREE_LEAVES_BLOCK.get()),
                new AcaciaFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 1, 2))
                .dirt(BlockStateProvider.simple(Blocks.END_STONE)).build();
        END_TREE_GENERATION = registerPlacedFeature("end_tree", Feature.TREE.configured(treeConfig),
                CountPlacement.of(1),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                RarityFilter.onAverageOnceEvery(rairity),
                HEIGHTMAP_WORLD_SURFACE);

        addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_TREE_GENERATION);
    }

    private static void configureEndSmallTree()
    {
        int baseHeight = 1;
        int heightRandA = 0;
        int heightRandB = 0;
        int rairity = 2;
        TreeConfiguration treeConfig = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(END_TREE_TRUNK_BLOCK.get()),
                new StraightTrunkPlacer(baseHeight, heightRandA, heightRandB),
                BlockStateProvider.simple(END_SMALL_TREE_LEAVES_BLOCK.get()),
                new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
                new TwoLayersFeatureSize(0, 0, 0))
                .dirt(BlockStateProvider.simple(Blocks.END_STONE)).build();
        END_SMALL_TREE_GENERATION = registerPlacedFeature("end_small_tree", Feature.TREE.configured(treeConfig),
                CountPlacement.of(1),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                RarityFilter.onAverageOnceEvery(rairity),
                HEIGHTMAP_WORLD_SURFACE);

        addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_SMALL_TREE_GENERATION);
    }
}
