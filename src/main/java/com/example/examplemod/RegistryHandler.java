package com.example.examplemod;

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


    // register block
    public static final RegistryObject<Block> ROCK_BLOCK = BLOCKS.register("rock_block", () ->
        new Block(
                Block.Properties
                        .of(Material.STONE)
                        .sound(SoundType.STONE)
        )
    );

    // register item
    public static final RegistryObject<Item> ROCK = ITEMS.register("rock", () ->
            new Item(
                    new Item.Properties()
            )
    );
}