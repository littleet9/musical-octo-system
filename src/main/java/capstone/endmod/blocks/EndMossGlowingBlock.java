package capstone.endmod.blocks;

import capstone.endmod.EndModRoot;
import capstone.endmod.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

public class EndMossGlowingBlock extends Block
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EndModRoot.MODID);
    private int age = 0;

    public EndMossGlowingBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void stepOn(Level p_152431_, BlockPos p_152432_, BlockState p_152433_, Entity p_152434_) {
        if (p_152434_.getType() == EntityType.PLAYER)
        {
            p_152431_.setBlockAndUpdate(p_152432_, BlockInit.END_MOSS_LIGHT_BLOCK.get().defaultBlockState());
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState p_152728_, ServerLevel p_152729_, BlockPos p_152730_, Random p_152731_)
    {
        age = age + 1;
        if(age >= 3)
        {
            p_152729_.setBlockAndUpdate(p_152730_, BlockInit.END_MOSS_BLOCK.get().defaultBlockState());
        }
    }

    @Override
    public boolean canSustainPlant(BlockState state, BlockGetter world, BlockPos pos, Direction facing, net.minecraftforge.common.IPlantable plantable) {
        Block plant = plantable.getPlant(world, pos.relative(facing)).getBlock();

        if (plant == Blocks.CHORUS_PLANT || plant == Blocks.BLUE_ORCHID || plant == Blocks.ALLIUM || plant == Blocks.CORNFLOWER || plant == Blocks.WITHER_ROSE || plant == Blocks.LILAC)
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