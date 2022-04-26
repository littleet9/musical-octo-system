package capstone.endmod.init;

import capstone.endmod.EndModRoot;
import capstone.endmod.entities.SpaceWhaleEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class EntityInit {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, EndModRoot.MODID);

    public static final RegistryObject<EntityType<SpaceWhaleEntity>> SPACE_WHALE_ENTITY = ENTITIES.register(
            "space_whale_entity",
            () -> EntityType.Builder.of(SpaceWhaleEntity::new, MobCategory.CREATURE).sized(0.8f, 0.6f).clientTrackingRange(10)
                    .build(new ResourceLocation(EndModRoot.MODID, "space_whale_entity").toString()));

    private EntityInit()
    {

    }
}