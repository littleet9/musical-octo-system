package capstone.endmod.events;

import capstone.endmod.EndModRoot;
import capstone.endmod.init.EntityInit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EndModRoot.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonForgeEvents {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void biomeLoading(BiomeLoadingEvent event) {
        if (event.getName().equals(new ResourceLocation("minecraft:small_end_islands")) || event.getName().equals(new ResourceLocation("minecraft:end_barrens"))) {
            event.getSpawns().addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityInit.SPACE_WHALE_ENTITY.get(), 5, 1, 1));
        }
    }
}

