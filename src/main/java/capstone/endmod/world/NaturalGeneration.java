package capstone.endmod.world;

import capstone.endmod.EndModRoot;
import capstone.endmod.RegistryHandler;
import com.google.common.collect.ImmutableSet;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.EndFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static capstone.endmod.RegistryHandler.*;
import static net.minecraft.data.worldgen.placement.PlacementUtils.HEIGHTMAP_WORLD_SURFACE;


@Mod.EventBusSubscriber(modid = EndModRoot.MODID, bus= Mod.EventBusSubscriber.Bus.FORGE)
public class NaturalGeneration {

    public static PlacedFeature END_MOSS_GENERATION;
    public static PlacedFeature MEGA_END_TREE_GENERATION;
    public static PlacedFeature END_TREE_GENERATION;
    public static PlacedFeature END_SMALL_TREE_GENERATION;
    public static PlacedFeature END_BLUE_ORCHID_GENERATION;
    public static PlacedFeature END_ALLIUM_FLOWER_GENERATION;
    public static PlacedFeature END_CORN_FLOWER_GENERATION;
    public static PlacedFeature END_LILAC_FLOWER_GENERATION;
    public static PlacedFeature END_WITHER_FLOWER_GENERATION;
    public static PlacedFeature END_LAKE_GENERATION;
    public static PlacedFeature END_SPRING_GENERATION;
    public static PlacedFeature CHORUS_PLANT_GENERATION;

    public static void registerConfiguredFeatures()
    {
        configureEndMossBlock();
        configureMegaEndTree();
        configureEndTree();
        configureEndSmallTree();
        configureEndFlowers();
        configureEndWater();
        configureChorusPlant();
    }
    private static void configureChorusPlant()
    {
        CHORUS_PLANT_GENERATION = PlacementUtils.register("chorus_plant",
                EndFeatures.CHORUS_PLANT.placed(CountPlacement.of(UniformInt.of(0, 4)),
                        InSquarePlacement.spread(),
                        HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()));
    }

    private static void configureMegaEndTree()
    {
        int baseHeight = 1;
        int heightRandA = 0;
        int heightRandB = 0;
        int rairity = 2;
        TreeConfiguration treeConfig = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(END_TREE_TRUNK_BLOCK.get()), //Trunk
                new MegaJungleTrunkPlacer(24, 2, 19),
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
    }

    private static void configureEndTree()
    {
        int baseHeight = 1;
        int heightRandA = 0;
        int heightRandB = 0;
        int rairity = 2;
        TreeConfiguration treeConfig = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(END_TREE_TRUNK_BLOCK.get()),
                new ForkingTrunkPlacer(12, 2, 2),
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
                new TwoLayersFeatureSize(0, 0, 0)).build();
        END_SMALL_TREE_GENERATION = registerPlacedFeature("end_small_tree", Feature.TREE.configured(treeConfig),
                CountPlacement.of(1),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                RarityFilter.onAverageOnceEvery(rairity),
                HEIGHTMAP_WORLD_SURFACE);
    }

    private static void configureEndMossBlock()
    {
        int veinsize = 64;
        int amount = 256;
        OreConfiguration endConfig = new OreConfiguration(new TagMatchTest(Tags.Blocks.END_STONES), RegistryHandler.END_MOSS_BLOCK.get().defaultBlockState(), veinsize);
        END_MOSS_GENERATION = registerPlacedFeature("end_moss_block", Feature.ORE.configured(endConfig),
                CountPlacement.of(amount),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(57), VerticalAnchor.absolute(265)));
    }

    private static void configureEndFlowers()
    {
        int amount = 1;
        int rairity = 8;
        UniformInt xzOffset = UniformInt.of(1, 16);
        //Blue Orchid
        BlockPileConfiguration blueOrchidConfig = new BlockPileConfiguration(BlockStateProvider.simple(Blocks.BLUE_ORCHID));
        END_BLUE_ORCHID_GENERATION = registerPlacedFeature("end_blue_orchid_flower", Feature.BLOCK_PILE.configured(blueOrchidConfig),
                CountPlacement.of(amount),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(57), VerticalAnchor.absolute(265)),
                HEIGHTMAP_WORLD_SURFACE,
                RarityFilter.onAverageOnceEvery(rairity),
                RandomOffsetPlacement.horizontal(xzOffset));
        //Allium
        BlockPileConfiguration alliumConfig = new BlockPileConfiguration(BlockStateProvider.simple(Blocks.ALLIUM));
        END_ALLIUM_FLOWER_GENERATION = registerPlacedFeature("end_allium_flower", Feature.BLOCK_PILE.configured(alliumConfig),
                CountPlacement.of(amount),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(57), VerticalAnchor.absolute(265)),
                HEIGHTMAP_WORLD_SURFACE,
                RarityFilter.onAverageOnceEvery(rairity),
                RandomOffsetPlacement.horizontal(xzOffset));
        //CornFlower
        BlockPileConfiguration cornFlowerConfig = new BlockPileConfiguration(BlockStateProvider.simple(Blocks.CORNFLOWER));
        END_CORN_FLOWER_GENERATION = registerPlacedFeature("end_corn_flower", Feature.BLOCK_PILE.configured(cornFlowerConfig),
                CountPlacement.of(amount),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(57), VerticalAnchor.absolute(265)),
                HEIGHTMAP_WORLD_SURFACE,
                RarityFilter.onAverageOnceEvery(rairity),
                RandomOffsetPlacement.horizontal(xzOffset));
        //Lilac
        BlockPileConfiguration lilacConfig = new BlockPileConfiguration(BlockStateProvider.simple(Blocks.LILAC));
        END_LILAC_FLOWER_GENERATION = registerPlacedFeature("end_lilac_flower", Feature.BLOCK_PILE.configured(lilacConfig),
                CountPlacement.of(amount),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(57), VerticalAnchor.absolute(265)),
                HEIGHTMAP_WORLD_SURFACE,
                RarityFilter.onAverageOnceEvery(rairity),
                RandomOffsetPlacement.horizontal(xzOffset));
        //WitherRose
        BlockPileConfiguration witherRoseConfig = new BlockPileConfiguration(BlockStateProvider.simple(Blocks.WITHER_ROSE));
        END_WITHER_FLOWER_GENERATION = registerPlacedFeature("end_wither_flower", Feature.BLOCK_PILE.configured(witherRoseConfig),
                CountPlacement.of(amount),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(57), VerticalAnchor.absolute(265)),
                HEIGHTMAP_WORLD_SURFACE,
                RarityFilter.onAverageOnceEvery(rairity),
                RandomOffsetPlacement.horizontal(xzOffset));
    }

    private static void configureEndWater()
    {
        int amount = 1;
        int rairity = 2;
        UniformInt xzOffset = UniformInt.of(1, 16);

        LakeFeature.Configuration endLakeConfig = new LakeFeature.Configuration(BlockStateProvider.simple(Blocks.WATER), BlockStateProvider.simple(END_MOSS_GLOWING_STATIC_BLOCK.get()));
        END_LAKE_GENERATION = registerPlacedFeature("end_lake", Feature.LAKE.configured(endLakeConfig),
                CountPlacement.of(amount),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(57), VerticalAnchor.absolute(265)),
                HEIGHTMAP_WORLD_SURFACE,
                RarityFilter.onAverageOnceEvery(rairity),
                RandomOffsetPlacement.horizontal(xzOffset));

        ImmutableSet<Block> validBlocks = ImmutableSet.of(END_MOSS_BLOCK.get(), Blocks.END_STONE);
        int rockCount = 4;
        int holeCount = 1;
        SpringConfiguration endSpringConfig = new SpringConfiguration(Fluids.WATER.defaultFluidState(), true, rockCount, holeCount, validBlocks);
        END_SPRING_GENERATION = registerPlacedFeature("end_spring", Feature.SPRING.configured(endSpringConfig),
                CountPlacement.of(128),
                BiomeFilter.biome());
    }
    private static <C extends FeatureConfiguration, F extends Feature<C>> PlacedFeature registerPlacedFeature(String registeryName, ConfiguredFeature<C, F> feature, PlacementModifier ... placementModifiers)
    {
        PlacedFeature placed = BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(registeryName), feature).placed(placementModifiers);
        return PlacementUtils.register(registeryName, placed);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void biomeLoading(final BiomeLoadingEvent event) {

        if (event.getCategory() == Biome.BiomeCategory.THEEND) {
            //Blocks
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, END_MOSS_GENERATION);
            //Trees
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MEGA_END_TREE_GENERATION);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_TREE_GENERATION);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_SMALL_TREE_GENERATION);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CHORUS_PLANT_GENERATION);
            //Flowers
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_BLUE_ORCHID_GENERATION);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_ALLIUM_FLOWER_GENERATION);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_CORN_FLOWER_GENERATION);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_LILAC_FLOWER_GENERATION);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_WITHER_FLOWER_GENERATION);
            //Water
            event.getGeneration().addFeature(GenerationStep.Decoration.LAKES, END_LAKE_GENERATION);
            event.getGeneration().addFeature(GenerationStep.Decoration.FLUID_SPRINGS, END_SPRING_GENERATION);
        }
        else if (event.getCategory() == Biome.BiomeCategory.NETHER) {

        }
        else  {

        }
    }
}

