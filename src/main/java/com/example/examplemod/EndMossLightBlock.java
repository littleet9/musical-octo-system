package com.example.examplemod;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

public class EndMossLightBlock extends Block
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ExampleMod.MODID);
    private int age = 0;


    public EndMossLightBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState p_152728_, ServerLevel p_152729_, BlockPos p_152730_, Random p_152731_)
    {
        age = age + 1;
        if(age >= 7)
        {
            p_152729_.setBlockAndUpdate(p_152730_, RegistryHandler.END_MOSS_GLOWING_BLOCK.get().defaultBlockState());
        }
    }
}