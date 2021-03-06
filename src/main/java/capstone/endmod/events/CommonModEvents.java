package capstone.endmod.events;

import capstone.endmod.EndModRoot;
import capstone.endmod.entities.SpaceWhaleEntity;
import capstone.endmod.init.EntityInit;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = EndModRoot.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents
{
    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SpawnPlacements.register(EntityInit.SPACE_WHALE_ENTITY.get(), SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.WORLD_SURFACE, SpaceWhaleEntity::canSpawn);
        });
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(EntityInit.SPACE_WHALE_ENTITY.get(), SpaceWhaleEntity.createAttributes().build());
    }
}
