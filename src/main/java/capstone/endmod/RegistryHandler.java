package capstone.endmod;

import capstone.endmod.blocks.EndMossBlock;
import capstone.endmod.blocks.EndMossGlowingBlock;
import capstone.endmod.blocks.EndMossLightBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RegistryHandler {
    // create DeferredRegister objects
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EndModRoot.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EndModRoot.MODID);
    public static final DeferredRegister<EntityType<?>> MOBS = DeferredRegister.create(ForgeRegistries.ENTITIES, EndModRoot.MODID);

    public static void init() {
        // attach DeferredRegisters to the event bus
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        MOBS.register(FMLJavaModLoadingContext.get().getModEventBus());
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
    public static final RegistryObject<Block> END_MOSS_GLOWING_STATIC_BLOCK = BLOCKS.register("end_moss_glowing_static_block",
            () -> new Block(Block.Properties
                    .of(Material.STONE)
                    .sound(SoundType.WART_BLOCK)
                    .lightLevel((state) -> 7)
                    .strength(1)
                    .requiresCorrectToolForDrops()
            )
    );
    public static final RegistryObject<Item> END_MOSS_GLOWING_BLOCK_ITEM = ITEMS.register("end_moss_glowing_static_block", () ->
            new BlockItem(
                    END_MOSS_GLOWING_STATIC_BLOCK.get(),
                    new Item.Properties()
                            .tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
            )
    );
/***************************************
    End Moss Light Block
 ***************************************/
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
    public static final RegistryObject<Item> END_MOSS_LIGHT_BLOCK_ITEM = ITEMS.register("end_moss_light_static_block", () ->
            new BlockItem(
                    END_MOSS_LIGHT_STATIC_BLOCK.get(),
                    new Item.Properties()
                            .tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
            )
    );

/***************************************
 End Tree Leaves
 ***************************************/
    public static final RegistryObject<Block> END_TREE_LEAVES_BLOCK = BLOCKS.register("end_tree_leaves_block",
        () -> new Block(Block.Properties
                .of(Material.LEAVES)
                .sound(SoundType.AZALEA_LEAVES)
                .lightLevel((state) -> 0)
                .strength(0.25F)
                .noOcclusion()
                .isViewBlocking(RegistryHandler::never)
                .isValidSpawn(RegistryHandler::never)
        )
    );
    public static final RegistryObject<Item> END_TREE_LEAVES_BLOCK_ITEM = ITEMS.register("end_tree_leaves_block", () ->
            new BlockItem(
                    END_TREE_LEAVES_BLOCK.get(),
                    new Item.Properties()
                            .tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
            )
    );
    public static final RegistryObject<Block> END_SMALL_TREE_LEAVES_BLOCK = BLOCKS.register("end_small_tree_leaves_block",
            () -> new Block(Block.Properties
                    .of(Material.LEAVES)
                    .sound(SoundType.AZALEA_LEAVES)
                    .lightLevel((state) -> 0)
                    .strength(0.25F)
                    .noOcclusion()
                    .isViewBlocking(RegistryHandler::never)
                    .isValidSpawn(RegistryHandler::never)
            )
    );
    public static final RegistryObject<Item> END_SMALL_TREE_LEAVES_BLOCK_ITEM = ITEMS.register("end_small_tree_leaves_block", () ->
            new BlockItem(
                    END_SMALL_TREE_LEAVES_BLOCK.get(),
                    new Item.Properties()
                            .tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
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

/***************************************
    End Tree Trunk
 ***************************************/
    public static final RegistryObject<Block> END_TREE_TRUNK_BLOCK = BLOCKS.register("end_tree_trunk_block",
            () -> new Block(Block.Properties
                    .of(Material.WOOD)
                    .sound(SoundType.STONE)
                    .lightLevel((state) -> 0)
                    .strength(1)
                    .noOcclusion()

            )
    );
    public static final RegistryObject<Item> END_TREE_TRUNK_BLOCK_ITEM = ITEMS.register("end_tree_trunk_block", () ->
            new BlockItem(
                    END_TREE_TRUNK_BLOCK.get(),
                    new Item.Properties()
                            .tab(CreativeModeTab.TAB_BUILDING_BLOCKS)
            )
    );

/***************************************
    End Rabbit
 ***************************************/
   // public static final EntityType<Rabbit> RABBIT = MOBS.register("rabbit", EntityType.Builder.<Rabbit>of(Rabbit::new, MobCategory.CREATURE).sized(0.4F, 0.5F).clientTrackingRange(8));
}