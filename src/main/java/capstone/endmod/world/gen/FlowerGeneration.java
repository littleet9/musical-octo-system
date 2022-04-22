package capstone.endmod.world.gen;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;

import static net.minecraft.data.worldgen.placement.PlacementUtils.HEIGHTMAP_WORLD_SURFACE;

public class FlowerGeneration extends AbstractGeneration
{
    private static final int AMOUNT = 1;
    private static final int RARITY = 8;
    private static final UniformInt XZ_OFFSET = UniformInt.of(1, 16);

    public static PlacedFeature END_BLUE_ORCHID_GENERATION;
    public static PlacedFeature END_ALLIUM_FLOWER_GENERATION;
    public static PlacedFeature END_CORN_FLOWER_GENERATION;
    public static PlacedFeature END_LILAC_FLOWER_GENERATION;
    public static PlacedFeature END_WITHER_FLOWER_GENERATION;

    public static void configureFeatures()
    {
        configureBlueOrchids();
        configureAlliums();
        configureCornFlowers();
        configureLilacs();
        configureWitherRose();
    }

    private static void configureBlueOrchids()
    {
        BlockPileConfiguration blueOrchidConfig = new BlockPileConfiguration(BlockStateProvider.simple(Blocks.BLUE_ORCHID));
        END_BLUE_ORCHID_GENERATION = registerPlacedFeature("end_blue_orchid_flower", Feature.BLOCK_PILE.configured(blueOrchidConfig),
                CountPlacement.of(AMOUNT),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(57), VerticalAnchor.absolute(265)),
                HEIGHTMAP_WORLD_SURFACE,
                RarityFilter.onAverageOnceEvery(RARITY),
                RandomOffsetPlacement.horizontal(XZ_OFFSET));
        addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_BLUE_ORCHID_GENERATION);
    }

    private static void configureAlliums()
    {
        BlockPileConfiguration alliumConfig = new BlockPileConfiguration(BlockStateProvider.simple(Blocks.ALLIUM));
        END_ALLIUM_FLOWER_GENERATION = registerPlacedFeature("end_allium_flower", Feature.BLOCK_PILE.configured(alliumConfig),
                CountPlacement.of(AMOUNT),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(57), VerticalAnchor.absolute(265)),
                HEIGHTMAP_WORLD_SURFACE,
                RarityFilter.onAverageOnceEvery(RARITY),
                RandomOffsetPlacement.horizontal(XZ_OFFSET));
        addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_ALLIUM_FLOWER_GENERATION);
    }

    private static void configureCornFlowers()
    {
        BlockPileConfiguration cornFlowerConfig = new BlockPileConfiguration(BlockStateProvider.simple(Blocks.CORNFLOWER));
        END_CORN_FLOWER_GENERATION = registerPlacedFeature("end_corn_flower", Feature.BLOCK_PILE.configured(cornFlowerConfig),
                CountPlacement.of(AMOUNT),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(57), VerticalAnchor.absolute(265)),
                HEIGHTMAP_WORLD_SURFACE,
                RarityFilter.onAverageOnceEvery(RARITY),
                RandomOffsetPlacement.horizontal(XZ_OFFSET));
        addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_CORN_FLOWER_GENERATION);
    }

    private static void configureLilacs() {
        BlockPileConfiguration lilacConfig = new BlockPileConfiguration(BlockStateProvider.simple(Blocks.LILAC));
        END_LILAC_FLOWER_GENERATION = registerPlacedFeature("end_lilac_flower", Feature.BLOCK_PILE.configured(lilacConfig),
                CountPlacement.of(AMOUNT),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(57), VerticalAnchor.absolute(265)),
                HEIGHTMAP_WORLD_SURFACE,
                RarityFilter.onAverageOnceEvery(RARITY),
                RandomOffsetPlacement.horizontal(XZ_OFFSET));
        addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_LILAC_FLOWER_GENERATION);
    }

    private static void configureWitherRose()
    {
        BlockPileConfiguration witherRoseConfig = new BlockPileConfiguration(BlockStateProvider.simple(Blocks.WITHER_ROSE));
        END_WITHER_FLOWER_GENERATION = registerPlacedFeature("end_wither_flower", Feature.BLOCK_PILE.configured(witherRoseConfig),
                CountPlacement.of(AMOUNT),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(57), VerticalAnchor.absolute(265)),
                HEIGHTMAP_WORLD_SURFACE,
                RarityFilter.onAverageOnceEvery(RARITY),
                RandomOffsetPlacement.horizontal(XZ_OFFSET));
        addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, END_WITHER_FLOWER_GENERATION);
    }
}

