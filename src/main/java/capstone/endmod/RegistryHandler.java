package capstone.endmod;

import capstone.endmod.blocks.EndMossBlock;
import capstone.endmod.blocks.EndMossGlowingBlock;
import capstone.endmod.blocks.EndMossLightBlock;
import com.google.common.collect.ImmutableList;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;
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
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, EndModRoot.MODID);

    public static void init() {
        // attach DeferredRegisters to the event bus
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        MOBS.register(FMLJavaModLoadingContext.get().getModEventBus());
        FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
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
     Mega End Tree
     ***************************************/
    public static final TreeConfiguration.TreeConfigurationBuilder MEGA_END_TREE_CONFIGURATION = new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.OBSIDIAN), //Trunk
            new MegaJungleTrunkPlacer(24, 2, 19),
            BlockStateProvider.simple(Blocks.LAPIS_BLOCK), //Leaves
            new MegaJungleFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), 2),
            new TwoLayersFeatureSize(1, 1, 2))
            .dirt(BlockStateProvider.simple(Blocks.END_STONE));
    public static final ConfiguredFeature<TreeConfiguration, ?> MEGA_END_TREE = FeatureUtils.register("mega_end_tree",
            Feature.TREE.configured((MEGA_END_TREE_CONFIGURATION).build()));

    public static final TreeConfiguration.TreeConfigurationBuilder END_TREE_CONFIGURATION = new TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(Blocks.OBSIDIAN),
            new ForkingTrunkPlacer(12, 2, 2),
            BlockStateProvider.simple(Blocks.LAPIS_BLOCK),
            new AcaciaFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
            new TwoLayersFeatureSize(1, 1, 2))
            .dirt(BlockStateProvider.simple(Blocks.END_STONE));

    public static final ConfiguredFeature<TreeConfiguration, ?> END_TREE = FeatureUtils.register("end_tree",
            Feature.TREE.configured((END_TREE_CONFIGURATION).build()));



}