package capstone.endmod.client.event;

import capstone.endmod.EndModRoot;
import capstone.endmod.client.entity.model.BunnyEntityModel;
import capstone.endmod.client.entity.render.BunnyEntityRenderer;
import capstone.endmod.init.EntityInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = EndModRoot.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvents
{
    private ClientModEvents()
    {
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event)
    {

    }

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(BunnyEntityModel.LAYER_LOCATION, BunnyEntityModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(EntityInit.BUNNY_ENTITY.get(), BunnyEntityRenderer::new);
    }
}