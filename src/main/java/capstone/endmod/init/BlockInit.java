package capstone.endmod.init;

import capstone.endmod.EndModRoot;
import capstone.endmod.blocks.EndMossBlock;
import capstone.endmod.blocks.EndMossGlowingBlock;
import capstone.endmod.blocks.EndMossLightBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EndModRoot.MODID);

    public static final RegistryObject<Block> END_MOSS_BLOCK = BLOCKS.register("end_moss_block",
            () -> new EndMossBlock(Block.Properties
                    .of(Material.STONE)
                    .sound(SoundType.WART_BLOCK)
                    .lightLevel((state) -> 0)
                    .strength(1)
                    .requiresCorrectToolForDrops()
            )
    );

    public static final RegistryObject<Block> END_MOSS_GLOWING_BLOCK = BLOCKS.register("end_moss_glowing_block",
            () -> new EndMossGlowingBlock(Block.Properties
                    .of(Material.STONE)
                    .sound(SoundType.WART_BLOCK)
                    .lightLevel((state) -> 7)
                    .strength(1)
                    .requiresCorrectToolForDrops()
            )
    );
    public static final RegistryObject<Block> END_MOSS_GLOWING_STATIC_BLOCK = BLOCKS.register("end_moss_glowing_static_block",
            () -> new Block(Block.Properties
                    .of(Material.STONE)
                    .sound(SoundType.WART_BLOCK)
                    .lightLevel((state) -> 7)
                    .strength(1)
                    .requiresCorrectToolForDrops()
            )
    );

    public static final RegistryObject<Block> END_MOSS_LIGHT_BLOCK = BLOCKS.register("end_moss_light_block",
            () -> new EndMossLightBlock(Block.Properties
                    .of(Material.STONE)
                    .sound(SoundType.WART_BLOCK)
                    .lightLevel((state) -> 13)
                    .strength(1)
                    .requiresCorrectToolForDrops()
            )
    );
    public static final RegistryObject<Block> END_MOSS_LIGHT_STATIC_BLOCK = BLOCKS.register("end_moss_light_static_block",
            () -> new Block(Block.Properties
                    .of(Material.STONE)
                    .sound(SoundType.WART_BLOCK)
                    .lightLevel((state) -> 13)
                    .strength(1)
                    .requiresCorrectToolForDrops()
            )
    );

    public static final RegistryObject<Block> END_TREE_LEAVES_BLOCK = BLOCKS.register("end_tree_leaves_block",
            () -> new Block(Block.Properties
                    .of(Material.LEAVES)
                    .sound(SoundType.AZALEA_LEAVES)
                    .lightLevel((state) -> 0)
                    .strength(0.25F)
                    .noOcclusion()
                    .isViewBlocking(BlockInit::never)
                    .isValidSpawn(BlockInit::never)
            )
    );

    public static final RegistryObject<Block> END_SMALL_TREE_LEAVES_BLOCK = BLOCKS.register("end_small_tree_leaves_block",
            () -> new Block(Block.Properties
                    .of(Material.LEAVES)
                    .sound(SoundType.AZALEA_LEAVES)
                    .lightLevel((state) -> 0)
                    .strength(0.25F)
                    .noOcclusion()
                    .isViewBlocking(BlockInit::never)
                    .isValidSpawn(BlockInit::never)
            )
    );

    private static boolean never(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_)
    {
        return false;
    }
    private static Boolean never(BlockState p_50779_, BlockGetter p_50780_, BlockPos p_50781_, EntityType<?> p_50782_)
    {
        return (boolean)false;
    }

    public static final RegistryObject<Block> END_TREE_TRUNK_BLOCK = BLOCKS.register("end_tree_trunk_block",
            () -> new Block(Block.Properties
                    .of(Material.WOOD)
                    .sound(SoundType.STONE)
                    .lightLevel((state) -> 0)
                    .strength(1)
                    .noOcclusion()

            )
    );

    public static final RegistryObject<Block> JAI_PLANKS_BLOCK = BLOCKS.register("jai_planks_block", () -> new Block(Block.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> JAI_SLAB = BLOCKS.register("jai_slab",() -> new Block(SlabBlock.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> JAI_STAIRS = BLOCKS.register("jai_stairs",() -> new Block(StairBlock.Properties.of(Material.WOOD).strength(2.0F, 3.0F).sound(SoundType.WOOD)));


}
