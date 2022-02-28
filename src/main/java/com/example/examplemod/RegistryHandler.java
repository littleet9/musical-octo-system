package com.example.examplemod;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.ToIntFunction;

public class RegistryHandler {
    // create DeferredRegister objects
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ExampleMod.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MODID);

    public static void init() {
        // attach DeferredRegisters to the event bus
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }


    // register block
    public static final RegistryObject<Block> ROCK_BLOCK = BLOCKS.register("rock_block", () ->
        new Block(
                Block.Properties
                        .of(Material.STONE)
                        .sound(SoundType.WART_BLOCK)
                        .lightLevel((state) -> 14)
                        .strength(1)
                        .requiresCorrectToolForDrops()
        )
    );

    // register item
    public static final RegistryObject<Item> ROCK_BLOCK_ITEM = ITEMS.register("rock_block", () ->
            new BlockItem(
                    ROCK_BLOCK.get(),
                    new Item.Properties()
                            .tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
            )
    );
}