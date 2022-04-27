package capstone.endmod;

import capstone.endmod.init.EntityInit;
import capstone.endmod.init.ItemInit;
import capstone.endmod.world.gen.AbstractGeneration;
import capstone.endmod.world.gen.FlowerGeneration;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("endmod")
public class EndModRoot
{
    public static final String MODID = "endmod";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public EndModRoot() {

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);


        final var bus = FMLJavaModLoadingContext.get().getModEventBus();


        RegistryHandler.init();
        EntityInit.ENTITIES.register(bus);
        ItemInit.ITEMS.register(bus);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(AbstractGeneration::registerConfiguredFeatures);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo(MODID, "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.messageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // do something when the server starts
        //LOGGER.info("HELLO from server starting");
    }

    @SubscribeEvent
    public void pickupItem(EntityItemPickupEvent event)
    {
        //String itemName = event.getItem().getItem().getItem().getRegistryName().toString();
        //Component message = new TextComponent("You Picked Up " + itemName);
        //Minecraft.getInstance().gui.getChat().addMessage(message);
    }

    @SubscribeEvent
    public void onLivingAttack(LivingAttackEvent event)
    {
        //if(event.getEntity().getType() == EntityType.PLAYER && event.getAmount() != 0)
        //{
            //Component message = new TextComponent("You Took " + event.getAmount() + " Damage");
            //Minecraft.getInstance().gui.getChat().addMessage(message);
        //}
    }


    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            //LOGGER.info("HELLO from Register Block");
        }
    }
}
