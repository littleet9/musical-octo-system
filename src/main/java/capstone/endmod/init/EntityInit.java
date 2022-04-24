package capstone.endmod.init;

import capstone.endmod.EndModRoot;
import capstone.endmod.entities.BunnyEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class EntityInit {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, EndModRoot.MODID);

    public static final RegistryObject<EntityType<BunnyEntity>> BUNNY_ENTITY = ENTITIES.register(
            "bunny_entity",
            () -> EntityType.Builder.of(BunnyEntity::new, MobCategory.CREATURE).sized(0.8f, 0.6f)
                    .build(new ResourceLocation(EndModRoot.MODID, "bunny_entity").toString()));

    private EntityInit()
    {

    }
}