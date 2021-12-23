package com.lucca.mohard.entities.etc.thox;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ColorableAgeableListModel;
import net.minecraft.client.model.WolfModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class ThoxModel<T extends Thox> extends ColorableAgeableListModel<T> {

    protected ModelPart head;
    protected ModelPart body;
    protected final ModelPart rightLeg;
    protected final ModelPart leftLeg;
    protected final ModelPart rightArm;
    protected final ModelPart leftArm;

    private boolean holdingItem = false;

    public ThoxModel(ModelPart modelPart) {
        this.body = modelPart.getChild("body");
        this.head = modelPart.getChild("head");
        this.rightLeg = modelPart.getChild("right_leg");
        this.leftLeg = modelPart.getChild("left_leg");
        this.rightArm = modelPart.getChild("right_arm");
        this.leftArm = modelPart.getChild("left_arm");


    }

    public static MeshDefinition createBodyModel() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        float f = 0.5F;

        PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -5.0F, -3.0F, 12.0F, 4.0F, 6.0F), PartPose.offset(0 ,13F, 0));
        PartDefinition partdefinition3 = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 11).addBox(-5.0F, 12.0F, -2.0F, 10.0F, 10.0F, 4.0F), PartPose.ZERO);

        partdefinition1.addOrReplaceChild("head_anthem1", CubeListBuilder.create().texOffs(38, 4).addBox(-4.0F, -7.0F, -1.0F, 1, 2, 3), PartPose.offset(0 ,0F, 0));
        partdefinition1.addOrReplaceChild("head_anthem2", CubeListBuilder.create().texOffs(38, 4).addBox(4.0F, -7.0F, -1.0F, 1, 2, 3), PartPose.offset(0 ,0F, 0));

        partdefinition3.addOrReplaceChild("body_anthem1", CubeListBuilder.create().texOffs(30, 13).addBox(-1.0F, 15.0F, 2.0F, 1, 5, 3), PartPose.offset(0 ,0F, 0));
        partdefinition3.addOrReplaceChild("body_anthem2", CubeListBuilder.create().texOffs(30, 13).addBox(1.0F, 15.0F, 2.0F, 1, 5, 3), PartPose.offset(0 ,0F, 0));

        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(9, 26).addBox(-4.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F), PartPose.offset(-3.0F, 21.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(9, 26).mirror().addBox(0.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F), PartPose.offset(5.0F, 21.0F, 0.0F));
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 26).addBox(-4.0F, -1.0F , -1.0F, 2.0F, 4.0F, 2.0F), PartPose.offset(-3.0F, 14.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 26).mirror().addBox(0.0F, -1.0F , -1.0F, 2.0F, 4.0F, 2.0F), PartPose.offset(5.0F, 14.0F, 0.0F));



        return meshdefinition;
    }

    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.head, this.body, this.leftArm, this.rightArm, this.rightLeg, this.leftLeg);
    }


    @Override
    public void setupAnim(T p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {
        this.head.yRot = p_102622_ * ((float) Math.PI / 180F);
        this.head.xRot = p_102623_ * ((float) Math.PI / 180F);
        this.head.zRot = 0.0F;


        if (p_102618_.isInSittingPose()) {
            this.rightLeg.xRot = (float) -Math.PI / 2;
            this.leftLeg.xRot = (float) -Math.PI / 2;
            this.leftArm.xRot = 0F;
            this.rightArm.xRot = 0F;

            deslocateHeight(2F);

        } else {
           deslocateHeight(0F);

            this.rightLeg.xRot = Mth.cos(p_102619_ * 0.6662F) * 1.4F * p_102620_ * 0.5F;
            this.leftLeg.xRot = Mth.cos(p_102619_ * 0.6662F + (float) Math.PI) * 1.4F * p_102620_ * 0.5F;
            this.rightLeg.yRot = 0.0F;
            this.leftLeg.yRot = 0.0F;

            this.rightArm.xRot = Mth.cos(p_102619_ * 0.6662F + (float) Math.PI) * 1.4F * p_102620_ * 0.5F;
            this.rightArm.yRot = 0.0F;
            this.rightArm.zRot = 0.0F;
            this.leftArm.xRot = Mth.cos(p_102619_ * 0.6662F) * 1.4F * p_102620_ * 0.5F;
            this.leftArm.yRot = 0.0F;
            this.leftArm.zRot = 0.0F;
            this.rightLeg.xRot = Mth.cos(p_102619_ * 0.6662F) * 1.4F * p_102620_ * 0.5F;
            this.rightLeg.yRot = 0.0F;
            this.rightLeg.zRot = 0.0F;
            this.leftLeg.xRot = Mth.cos(p_102619_ * 0.6662F + (float) Math.PI) * 1.4F * p_102620_ * 0.5F;
            this.leftLeg.yRot = 0.0F;
            this.leftLeg.zRot = 0.0F;
        }
    }

    private void deslocateHeight(float height){
        this.body.y = height ;
        this.leftArm.y = 14 + height ;
        this.rightArm.y = 14 + height ;
        this.leftLeg.y = 20 + height + ((height > 0) ? 1 : 0);
        this.rightLeg.y = 20 + height + ((height > 0) ? 1 : 0);
        this.head.y = 13 + height ;
    }
}
