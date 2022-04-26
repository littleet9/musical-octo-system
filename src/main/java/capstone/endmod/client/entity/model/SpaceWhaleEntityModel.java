package capstone.endmod.client.entity.model;

import capstone.endmod.EndModRoot;
import capstone.endmod.entities.SpaceWhaleEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class SpaceWhaleEntityModel<Type extends SpaceWhaleEntity> extends EntityModel<Type> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation(EndModRoot.MODID, "space_whale_entity"), "main");

    private final ModelPart body;

    public SpaceWhaleEntityModel(ModelPart root) {
        this.body = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-24.0F, -32.0F, -8.0F, 48.0F, 32.0F, 64.0F, new CubeDeformation(0.0F))
                .texOffs(0, 96).addBox(-13.0F, -30.0F, -19.0F, 25.0F, 28.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(126, 103).addBox(-41.0F, -16.0F, -4.0F, 23.0F, 7.0F, 80.0F, new CubeDeformation(0.0F))
                .texOffs(0, 96).addBox(18.0F, -16.0F, -4.0F, 23.0F, 7.0F, 80.0F, new CubeDeformation(0.0F))
                .texOffs(173, 197).addBox(-53.0F, -16.0F, 12.0F, 12.0F, 7.0F, 59.0F, new CubeDeformation(0.0F))
                .texOffs(90, 190).addBox(41.0F, -16.0F, 12.0F, 12.0F, 7.0F, 59.0F, new CubeDeformation(0.0F))
                .texOffs(0, 11).addBox(-10.0F, -32.0F, -17.0F, 3.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(7.0F, -32.0F, -17.0F, 3.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(0, 183).addBox(7.0F, -34.0F, -10.0F, 3.0F, 2.0F, 84.0F, new CubeDeformation(0.0F))
                .texOffs(140, 12).addBox(-10.0F, -34.0F, -10.0F, 3.0F, 2.0F, 84.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 512, 512);
    }

    @Override
    public void setupAnim(Type entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
                          float headPitch) {
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay,
                               float red, float green, float blue, float alpha) {
        this.body.render(poseStack, buffer, packedLight, packedOverlay);
    }
}