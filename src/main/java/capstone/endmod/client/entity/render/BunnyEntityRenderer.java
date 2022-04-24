package capstone.endmod.client.entity.render;

import capstone.endmod.EndModRoot;
import capstone.endmod.client.entity.model.BunnyEntityModel;
import capstone.endmod.entities.BunnyEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BunnyEntityRenderer<Type extends BunnyEntity> extends MobRenderer<Type, BunnyEntityModel<Type>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(EndModRoot.MODID,
            "textures/entities/bunny_entity.png");

    public BunnyEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new BunnyEntityModel<>(context.bakeLayer(BunnyEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return TEXTURE;
    }
}