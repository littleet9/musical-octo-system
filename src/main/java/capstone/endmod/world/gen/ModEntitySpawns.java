package capstone.endmod.world.gen;

import capstone.endmod.EndModRoot;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntitySpawns extends AbstractGeneration
{
    public static void configureFeatures()
    {

    }

//    @SubscribeEvent
//    public static void generateOres(FMLLoadCompleteEvent event) {
//        for (Biome biome : ForgeRegistries.BIOMES) {
//            if (biome.getBiomeCategory() == Biome.BiomeCategory.THEEND)
//            {
//                biome.getMobSettings()
//                        .getMobs(MobCategory.CREATURE).unwrap()
//                        .add(new Biome.SpawnListEntry(ModEntityType.HOG.get(), 10, 3, 5));
//            }
//        }
//    }
}
