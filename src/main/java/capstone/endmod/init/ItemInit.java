package capstone.endmod.init;

import capstone.endmod.EndModRoot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            EndModRoot.MODID);


    public static final RegistryObject<ForgeSpawnEggItem> SPACE_WHALE_SPAWN_EGG = ITEMS
            .register("space_whale_spawn_egg", () -> new ForgeSpawnEggItem(EntityInit.SPACE_WHALE_ENTITY, 0xFFFFFF,
                    0x1782db, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

}
