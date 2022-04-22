package capstone.endmod.world.gen;

import com.google.common.collect.ImmutableSet;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.SpringConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.material.Fluids;

import static capstone.endmod.RegistryHandler.END_MOSS_BLOCK;
import static capstone.endmod.RegistryHandler.END_MOSS_GLOWING_STATIC_BLOCK;
import static net.minecraft.data.worldgen.placement.PlacementUtils.HEIGHTMAP_WORLD_SURFACE;

public class LakeGeneration extends AbstractGeneration
{
    private static final int AMOUNT = 1;
    private static final int RARITY = 2;
    private static final UniformInt XZ_OFFSET = UniformInt.of(1, 16);

    public static PlacedFeature END_LAKE_GENERATION;
    public static PlacedFeature END_SPRING_GENERATION;

    public static void configureFeatures()
    {
        configureLakes();
        configureSprings();
    }

    private static void configureLakes()
    {
        LakeFeature.Configuration endLakeConfig = new LakeFeature.Configuration(BlockStateProvider.simple(Blocks.WATER), BlockStateProvider.simple(END_MOSS_GLOWING_STATIC_BLOCK.get()));
        END_LAKE_GENERATION = registerPlacedFeature("end_lake", Feature.LAKE.configured(endLakeConfig),
                CountPlacement.of(AMOUNT),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(57), VerticalAnchor.absolute(265)),
                HEIGHTMAP_WORLD_SURFACE,
                RarityFilter.onAverageOnceEvery(RARITY),
                RandomOffsetPlacement.horizontal(XZ_OFFSET));
        addFeature(GenerationStep.Decoration.LAKES, END_LAKE_GENERATION);
    }

    private static void configureSprings()
    {
        ImmutableSet<Block> validBlocks = ImmutableSet.of(END_MOSS_BLOCK.get(), Blocks.END_STONE);
        int rockCount = 4;
        int holeCount = 1;
        SpringConfiguration endSpringConfig = new SpringConfiguration(Fluids.WATER.defaultFluidState(), true, rockCount, holeCount, validBlocks);
        END_SPRING_GENERATION = registerPlacedFeature("end_spring", Feature.SPRING.configured(endSpringConfig),
                CountPlacement.of(128),
                BiomeFilter.biome());
        //addFeature(GenerationStep.Decoration.FLUID_SPRINGS, END_SPRING_GENERATION);
    }
}
