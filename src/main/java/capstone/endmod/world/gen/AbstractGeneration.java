package capstone.endmod.world.gen;

import capstone.endmod.EndModRoot;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = EndModRoot.MODID, bus= Mod.EventBusSubscriber.Bus.FORGE)
public abstract class AbstractGeneration
{
    static ArrayList<DecorativeFeature> decorativeFeatures = new ArrayList<>();

    public static void registerConfiguredFeatures()
    {
        FlowerGeneration.configureFeatures();
        LakeGeneration.configureFeatures();
        ModEntitySpawns.configureFeatures();
        MossGeneration.configureFeatures();
        TreeGeneration.configureFeatures();
    }

    protected static <C extends FeatureConfiguration, F extends Feature<C>> PlacedFeature registerPlacedFeature(String registeryName, ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers)
    {
        PlacedFeature placed = BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(registeryName), feature).placed(placementModifiers);
        return PlacementUtils.register(registeryName, placed);
    }

    protected static void addFeature(GenerationStep.Decoration decoration, PlacedFeature feature)
    {
        decorativeFeatures.add(new DecorativeFeature(decoration, feature));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void biomeLoading(final BiomeLoadingEvent event)
    {
        if (event.getCategory() == Biome.BiomeCategory.THEEND)
        {
            for(DecorativeFeature decorativeFeature : decorativeFeatures)
            {
                event.getGeneration().addFeature(decorativeFeature.getDecoration(), decorativeFeature.getFeature());
            }
        }
    }

    private record DecorativeFeature(GenerationStep.Decoration decoration, PlacedFeature feature) {
        public GenerationStep.Decoration getDecoration() {
            return decoration;
        }

        public PlacedFeature getFeature() {
            return feature;
        }
    }
}
