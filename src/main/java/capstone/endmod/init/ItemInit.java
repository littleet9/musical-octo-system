package capstone.endmod.init;

import capstone.endmod.EndModRoot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static capstone.endmod.init.BlockInit.*;

public class ItemInit
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            EndModRoot.MODID);


    public static final RegistryObject<ForgeSpawnEggItem> SPACE_WHALE_SPAWN_EGG = ITEMS
            .register("space_whale_spawn_egg", () -> new ForgeSpawnEggItem(EntityInit.SPACE_WHALE_ENTITY, 0xFFFFFF,
                    0x1782db, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> END_MOSS_BLOCK_ITEM = ITEMS.register("end_moss_block", () ->
            new BlockItem(
                    END_MOSS_BLOCK.get(),
                    new Item.Properties()
                            .tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
            )
    );

    public static final RegistryObject<Item> END_MOSS_GLOWING_BLOCK_ITEM = ITEMS.register("end_moss_glowing_static_block", () ->
            new BlockItem(
                    END_MOSS_GLOWING_STATIC_BLOCK.get(),
                    new Item.Properties()
                            .tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
            )
    );

    public static final RegistryObject<Item> END_MOSS_LIGHT_BLOCK_ITEM = ITEMS.register("end_moss_light_static_block", () ->
            new BlockItem(
                    END_MOSS_LIGHT_STATIC_BLOCK.get(),
                    new Item.Properties()
                            .tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
            )
    );

    public static final RegistryObject<Item> END_TREE_LEAVES_BLOCK_ITEM = ITEMS.register("end_tree_leaves_block", () ->
            new BlockItem(
                    END_TREE_LEAVES_BLOCK.get(),
                    new Item.Properties()
                            .tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
            )
    );

    public static final RegistryObject<Item> END_SMALL_TREE_LEAVES_BLOCK_ITEM = ITEMS.register("end_small_tree_leaves_block", () ->
            new BlockItem(
                    END_SMALL_TREE_LEAVES_BLOCK.get(),
                    new Item.Properties()
                            .tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
            )
    );

    public static final RegistryObject<Item> END_TREE_TRUNK_BLOCK_ITEM = ITEMS.register("end_tree_trunk_block", () ->
            new BlockItem(
                    END_TREE_TRUNK_BLOCK.get(),
                    new Item.Properties()
                            .tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
            )
    );

}
