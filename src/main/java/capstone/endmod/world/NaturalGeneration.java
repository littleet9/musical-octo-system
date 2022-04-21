package capstone.endmod.world;

import capstone.endmod.EndModRoot;
import capstone.endmod.RegistryHandler;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static capstone.endmod.RegistryHandler.*;
import static net.minecraft.data.worldgen.placement.PlacementUtils.HEIGHTMAP_WORLD_SURFACE;


@Mod.EventBusSubscriber(modid = EndModRoot.MODID, bus= Mod.EventBusSubscriber.Bus.FORGE)
public class NaturalGeneration {

    public static final int END_VEINSIZE = 64;
    public static final int END_AMOUNT = 256;
    public static RuleTest IN_ENDSTONE = new TagMatchTest(Tags.Blocks.END_STONES);
    public static PlacedFeature END_MOSS_GENERATION;
    public static PlacedFeature MEGA_END_TREE_GENERATION;
    public static PlacedFeature END_TREE_GENERATION;

    public static void registerConfiguredFeatures()
    {
        configureEndMossBlock();
        configureMegaEndTree();
        configureEndTree();
    }

    private static void configureMegaEndTree()
    {
        TreeConfiguration treeConfig = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OBSIDIAN), //Trunk
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
                BlockStateProvider.simple(Blocks.OBSIDIAN),
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
        OreConfiguration endConfig = new OreConfiguration(IN_ENDSTONE, RegistryHandler.END_MOSS_BLOCK.get().defaultBlockState(), END_VEINSIZE);
        END_MOSS_GENERATION = registerPlacedFeature("end_moss_block", Feature.ORE.configured(endConfig),
                CountPlacement.of(END_AMOUNT),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(57), VerticalAnchor.absolute(265)));
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
        }
        else if (event.getCategory() == Biome.BiomeCategory.NETHER) {

        }
        else  {

        }
    }
}

