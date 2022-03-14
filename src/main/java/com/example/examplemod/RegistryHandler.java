package com.example.examplemod;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryHandler {
    // create DeferredRegister objects
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ExampleMod.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MODID);

    public static void init() {
        // attach DeferredRegisters to the event bus
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

/***************************************
    End Moss Block
 ***************************************/
    public static final RegistryObject<Block> END_MOSS_BLOCK = BLOCKS.register("end_moss_block",
        () -> new EndMossBlock(Block.Properties
                    .of(Material.STONE)
                    .sound(SoundType.WART_BLOCK)
                    .lightLevel((state) -> 0)
                    .strength(1)
                    .requiresCorrectToolForDrops()
        )
    );
    public static final RegistryObject<Item> END_MOSS_BLOCK_ITEM = ITEMS.register("end_moss_block", () ->
        new BlockItem(
                END_MOSS_BLOCK.get(),
                new Item.Properties()
                        .tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
        )
    );
/***************************************
     End Moss Glowing Block
 ***************************************/
    public static final RegistryObject<Block> END_MOSS_GLOWING_BLOCK = BLOCKS.register("end_moss_glowing_block",
        () -> new EndMossGlowingBlock(Block.Properties
                .of(Material.STONE)
                .sound(SoundType.WART_BLOCK)
                .lightLevel((state) -> 7)
                .strength(1)
                .requiresCorrectToolForDrops()
        )
    );
    public static final RegistryObject<Item> END_MOSS_GLOWING_BLOCK_ITEM = ITEMS.register("end_moss_glowing_block", () ->
            new BlockItem(
                    END_MOSS_GLOWING_BLOCK.get(),
                    new Item.Properties()
                            .tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
            )
    );
}