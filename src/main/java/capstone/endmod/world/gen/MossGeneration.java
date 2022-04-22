package capstone.endmod.world.gen;

import capstone.endmod.RegistryHandler;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;

public class MossGeneration extends AbstractGeneration
{
    public static PlacedFeature END_MOSS_GENERATION;

    public static void configureFeatures()
    {
        configureEndMossBlock();
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

        addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, END_MOSS_GENERATION);
    }
}
