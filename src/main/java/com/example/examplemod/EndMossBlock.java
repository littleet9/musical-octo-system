package com.example.examplemod;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EndMossBlock extends Block
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ExampleMod.MODID);

    public EndMossBlock(Block.Properties properties)
    {
        super(properties);
    }

    @Override
    public void stepOn(Level p_152431_, BlockPos p_152432_, BlockState p_152433_, Entity p_152434_) {
        p_152431_.setBlockAndUpdate(p_152432_, RegistryHandler.END_MOSS_GLOWING_BLOCK.get().defaultBlockState());
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable) {
        Block plant = plantable.getPlant(world, pos.relative(facing)).getBlock();

        if (plant == Blocks.CHORUS_PLANT)
        {
            return true;
        }
        else if (plant == Blocks.CHORUS_FLOWER)
        {
            return true;
        }
        else
        {
            return super.canSustainPlant(state, world, pos, facing, plantable);
        }
    }

}