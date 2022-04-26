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
import net.minecraft.util.Mth;

public class SpaceWhaleEntityModel<Type extends SpaceWhaleEntity> extends EntityModel<Type> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation(EndModRoot.MODID, "space_whale_entity"), "main");

    private final ModelPart Whale;
    private final ModelPart Tail;
    private final ModelPart LeftFin;
    private final ModelPart RightFin;


    public SpaceWhaleEntityModel(ModelPart root)
    {
        this.Whale = root.getChild("Whale");
        this.LeftFin = root.getChild("Whale").getChild("LeftFin");
        this.RightFin = root.getChild("Whale").getChild("RightFin");
        this.Tail = root.getChild("Whale").getChild("Tail");
    }

    public static LayerDefinition createBodyLayer()
    {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Whale = partdefinition.addOrReplaceChild("Whale", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 23.0F, 0.0F, -3.1416F, 0.0F, -3.1416F));

        PartDefinition Body = Whale.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -49.0F, -46.0F, 16.0F, 49.0F, 95.0F, new CubeDeformation(0.0F))
                .texOffs(0, 144).addBox(8.0F, -43.0F, -46.0F, 11.0F, 37.0F, 95.0F, new CubeDeformation(0.0F))
                .texOffs(127, 49).addBox(-19.0F, -43.0F, -46.0F, 11.0F, 37.0F, 95.0F, new CubeDeformation(0.0F))
                .texOffs(0, 351).addBox(-8.0F, -49.0F, -102.0F, 16.0F, 17.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(127, 42).addBox(-8.0F, -32.0F, -101.0F, 16.0F, 18.0F, 17.0F, new CubeDeformation(0.0F))
                .texOffs(0, 144).addBox(-17.0F, -43.0F, -77.0F, 9.0F, 31.0F, 31.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(8.0F, -43.0F, -77.0F, 9.0F, 31.0F, 31.0F, new CubeDeformation(0.0F))
                .texOffs(244, 42).addBox(-8.0F, -49.0F, -84.0F, 16.0F, 46.0F, 38.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Head = Whale.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(96, 276).addBox(8.0F, -43.0F, -46.0F, 9.0F, 31.0F, 31.0F, new CubeDeformation(0.0F))
                .texOffs(244, 223).addBox(-17.0F, -43.0F, -46.0F, 9.0F, 31.0F, 31.0F, new CubeDeformation(0.0F))
                .texOffs(0, 276).addBox(-8.0F, -49.0F, -30.0F, 16.0F, 43.0F, 32.0F, new CubeDeformation(0.0F))
                .texOffs(0, 351).addBox(-8.0F, -49.0F, 2.0F, 16.0F, 40.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(344, 264).addBox(17.0F, -40.0F, -46.0F, 3.0F, 14.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(175, 59).addBox(-20.0F, -40.0F, -46.0F, 3.0F, 14.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(314, 42).addBox(-8.0F, -49.0F, 20.0F, 16.0F, 28.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(80, 338).addBox(-8.0F, -49.0F, -46.0F, 16.0F, 46.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 95.0F));

        PartDefinition Tail = Whale.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(117, 181).addBox(-35.0F, -43.0F, -210.0F, 68.0F, 11.0F, 31.0F, new CubeDeformation(0.0F))
                .texOffs(117, 181).addBox(33.0F, -43.0F, -223.0F, 12.0F, 11.0F, 33.0F, new CubeDeformation(0.0F))
                .texOffs(117, 181).addBox(21.0F, -43.0F, -223.0F, 12.0F, 11.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(117, 181).addBox(-47.0F, -43.0F, -223.0F, 12.0F, 11.0F, 33.0F, new CubeDeformation(0.0F))
                .texOffs(117, 181).addBox(-35.0F, -43.0F, -223.0F, 12.0F, 11.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 95.0F));

        PartDefinition LeftFin = Whale.addOrReplaceChild("LeftFin", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -33.0F));

        PartDefinition cube_r1 = LeftFin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(145, 337).addBox(39.0F, 16.0F, 5.0F, 11.0F, 10.0F, 31.0F, new CubeDeformation(0.0F))
                .texOffs(321, 95).addBox(50.0F, 16.0F, -7.0F, 11.0F, 10.0F, 31.0F, new CubeDeformation(0.0F))
                .texOffs(127, 0).addBox(7.0F, 15.0F, 14.0F, 32.0F, 11.0F, 31.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 11.0F, 3.1416F, 3.1416F, -0.2182F));

        PartDefinition RightFin = Whale.addOrReplaceChild("RightFin", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -33.0F));

        PartDefinition cube_r2 = RightFin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(145, 337).addBox(39.0F, -26.0F, 5.0F, 11.0F, 10.0F, 31.0F, new CubeDeformation(0.0F))
                .texOffs(127, 0).addBox(7.0F, -26.0F, 14.0F, 32.0F, 11.0F, 31.0F, new CubeDeformation(0.0F))
                .texOffs(321, 95).addBox(50.0F, -26.0F, -7.0F, 11.0F, 10.0F, 31.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 11.0F, 0.0F, 0.0F, 0.2182F));

        return LayerDefinition.create(meshdefinition, 512, 512);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        Whale.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void setupAnim(Type p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
        float tailSpeed = 0.1F;
        float finSpeed = 0.08F;
        if (p_102618_.getDeltaMovement().horizontalDistanceSqr() > 1.0E-7D) {
            //this.Body.xRot += -0.05F - 0.05F * Mth.cos(p_102621_ * 0.3F);
            this.Tail.xRot = 0.05F * Mth.cos(p_102621_ * tailSpeed);
            this.LeftFin.zRot = 0.2F * Mth.cos(p_102621_ * finSpeed);
            this.RightFin.zRot = -0.2F * Mth.cos(p_102621_ * finSpeed);
        }
    }

}