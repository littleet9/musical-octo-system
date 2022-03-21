package com.example.world;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.RegistryHandler;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;


@Mod.EventBusSubscriber(modid = ExampleMod.MODID, bus= Mod.EventBusSubscriber.Bus.FORGE)
public class NaturalGeneration {

    public static final int END_VEINSIZE = 10;
    public static final int END_AMOUNT = 6;
    public static RuleTest IN_ENDSTONE = new TagMatchTest(Tags.Blocks.END_STONES);
    public static PlacedFeature END_MOSS_GENERATION;

    public static void registerConfiguredFeatures()
    {
        OreConfiguration endConfig = new OreConfiguration(IN_ENDSTONE, RegistryHandler.END_MOSS_BLOCK.get().defaultBlockState(), END_VEINSIZE);
        END_MOSS_GENERATION = registerPlacedFeature("end_moss_block", Feature.ORE.configured(endConfig),
                CountPlacement.of(END_AMOUNT),
                InSquarePlacement.spread(),
                BiomeFilter.biome(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(265)));
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
        }
        else if (event.getCategory() == Biome.BiomeCategory.NETHER) {
            //event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, NETHER_OREGEN);
        }
        else  {
            //event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OVERWORLD_OREGEN);
            //event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DEEPSLATE_OREGEN);
        }
    }
}

