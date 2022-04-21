package capstone.endmod.world;

import capstone.endmod.EndModRoot;
import capstone.endmod.RegistryHandler;
import capstone.endmod.blocks.EndMossBlock;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTestType;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static capstone.endmod.RegistryHandler.*;
import static net.minecraft.data.worldgen.placement.PlacementUtils.HEIGHTMAP_WORLD_SURFACE;


@Mod.EventBusSubscriber(modid = EndModRoot.MODID, bus= Mod.EventBusSubscriber.Bus.FORGE)
public class NaturalGeneration {

    public static RuleTest IN_ENDSTONE = new TagMatchTest(Tags.Blocks.END_STONES);
    public static PlacedFeature END_MOSS_GENERATION;
    public static PlacedFeature MEGA_END_TREE_GENERATION;
    public static PlacedFeature END_TREE_GENERATION;
    public static PlacedFeature END_BLUE_ORCHID_GENERATION;
    public static PlacedFeature END_ALLIUM_FLOWER_GENERATION;
    public static PlacedFeature END_CORN_FLOWER_GENERATION;
    public static PlacedFeature END_LILAC_FLOWER_GENERATION;
    public static PlacedFeature END_WITHER_FLOWER_GENERATION;

    public static void registerConfiguredFeatures()
    {
        configureEndMossBlock();
        configureMegaEndTree();
        configureEndTree();
        configureEndFlowers();
    }

    private static void configureMegaEndTree()
    {
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
                HEIGHTMAP_WORLD_SURFACE);
    }

    private static void configureEndTree()
    {
        TreeConfiguration treeConfig = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(END_TREE_TRUNK_BLOCK.get()),
                new ForkingTrunkPlacer(12, 2, 2),
                BlockStateProvider.simple(END_TREE_LEAVES_BLOCK.get()),
                new AcaciaFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 1, 2))
                .dirt(BlockStateProvider.simple(Blocks.END_STONE)).build();
        END_TREE_GENERATION = registerPlacedFeature("end_tree", Feature.TREE.configured(treeConfig),
                CountPlacement.of(1),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HEIGHTMAP_WORLD_SURFACE);
    }

    private static void configureEndMossBlock()
    {
        int veinsize = 64;
        int amount = 256;
        OreConfiguration endConfig = new OreConfiguration(IN_ENDSTONE, RegistryHandler.END_MOSS_BLOCK.get().defaultBlockState(), veinsize);
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

    private static <C extends FeatureConfiguration, F extends Feature<C>> PlacedFeature registerPlacedFeature(String registeryName, ConfiguredFeature<C, F> feature, PlacementModifier ... placementModifiers)
    {
        PlacedFeature placed = BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(registeryName), feature).placed(placementModifiers);
        return PlacementUtils.register(registeryName, placed);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void biomeLoading(final BiomeLoadingEvent event) {

        if (event.getCategory() == Biome.BiomeCategory.THEEND) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, END_MOSS_GENERATION);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MEGA_END_TREE_GENERATION);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_TREE_GENERATION);

            //Flowers
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_BLUE_ORCHID_GENERATION);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_ALLIUM_FLOWER_GENERATION);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_CORN_FLOWER_GENERATION);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_LILAC_FLOWER_GENERATION);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_WITHER_FLOWER_GENERATION);
        }
        else if (event.getCategory() == Biome.BiomeCategory.NETHER) {

        }
        else  {

        }
    }
}

