package capstone.endmod.client.entity.render;

import capstone.endmod.EndModRoot;
import capstone.endmod.client.entity.model.SpaceWhaleEntityModel;
import capstone.endmod.entities.SpaceWhaleEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SpaceWhaleEntityRenderer<Type extends SpaceWhaleEntity> extends MobRenderer<Type, SpaceWhaleEntityModel<Type>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(EndModRoot.MODID,
            "textures/entities/space_whale_entity.png");

    public SpaceWhaleEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new SpaceWhaleEntityModel<>(context.bakeLayer(SpaceWhaleEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return TEXTURE;
    }
}