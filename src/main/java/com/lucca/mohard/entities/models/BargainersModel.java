package com.lucca.mohard.entities.models;

import com.lucca.mohard.entities.bargainers.BargainersEntity;
import com.lucca.mohard.entities.villagers.nomad.NomadState;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.VillagerHeadModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class BargainersModel<T extends BargainersEntity> extends HierarchicalModel<T> implements HeadedModel, VillagerHeadModel, ArmedModel {

    protected ModelPart head;
    protected ModelPart hat;
    protected final ModelPart root;
    protected final ModelPart rightLeg;
    protected final ModelPart leftLeg;
    protected final ModelPart nose;
    protected final ModelPart rightArm;
    protected final ModelPart leftArm;

    public BargainersModel(ModelPart modelPart, boolean smallNose) {
        this.root = modelPart;
        this.head = modelPart.getChild("head");
        this.hat = this.head.getChild("hat");
        this.nose = smallNose ? this.head.getChild("small_nose") : this.head.getChild("nose");
        if(smallNose) this.head.getChild("nose").visible = false;
        if(!smallNose) this.head.getChild("small_nose").visible = false;
        this.rightLeg = modelPart.getChild("right_leg");
        this.leftLeg = modelPart.getChild("left_leg");
        this.rightArm = modelPart.getChild("right_arm");
        this.leftArm = modelPart.getChild("left_arm");

        /*
        float f = 0.5F;
        this.head = (new ModelPart(this)).setTexSize(p_i51059_2_, p_i51059_3_);
        this.head.setPos(0.0F, 0.0F , 0.0F);
        this.head.texOffs(0, 0).addBox(-4.0F, -10.0F - 2 * height, -4.0F, 8.0F, 10.0F, 8.0F, p_i51059_1_);
        this.hat = (new ModelPart(this)).setTexSize(p_i51059_2_, p_i51059_3_);
        this.hat.setPos(0.0F, 0.0F, 0.0F);
        this.hat.texOffs(32, 0).addBox(-4.0F, -10.0F - 2 * height, -4.0F, 8.0F, 10.0F, 8.0F, p_i51059_1_ + 0.5F);
        this.head.addChild(this.hat);
        if(smallNose) {
            this.nose = (new ModelPart(this)).setTexSize(p_i51059_2_, p_i51059_3_);
            this.nose.setPos(0.0F, -2.0F, 0.0F);
            this.nose.texOffs(24, 0).addBox(-1.0F, -1.0F - 2 * height, -5.0F, 2.0F, 1.0F, 1.0F, p_i51059_1_);
        } else {
            this.nose = (new ModelPart(this)).setTexSize(p_i51059_2_, p_i51059_3_);
            this.nose.setPos(0.0F, -2.0F, 0.0F);
            this.nose.texOffs(24, 0).addBox(-1.0F, -1.0F - 2 * height, -6.0F, 2.0F, 4.0F, 2.0F, p_i51059_1_);
        }
        this.head.addChild(this.nose);
        this.body = (new ModelPart(this)).setTexSize(p_i51059_2_, p_i51059_3_);
        this.body.setPos(0.0F, 0.0F, 0.0F);
        this.body.texOffs(29, 44).addBox(-4.0F, 0.0F - 2 * height, -3.0F, 8.0F, 12.0F + height, 6.0F, p_i51059_1_);
        this.jacket = (new ModelPart(this)).setTexSize(p_i51059_2_, p_i51059_3_);
        this.jacket.setPos(0.0F, 0.0F, 0.0F);
        this.jacket.texOffs(0, 38).addBox(-4.0F, 0.0F  - 2 * height, -3.0F, 8.0F, 18.0F + height, 6.0F, p_i51059_1_ + 0.5F);
        this.body.addChild(this.jacket);

        this.rightArm = (new ModelPart(this, 30, 19)).setTexSize(p_i51059_2_, p_i51059_3_);
        this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F + height, 4.0F, p_i51059_1_);
        this.rightArm.setPos(-5.0F, 2.0F - 2 * height, 0.0F);
        this.leftArm = (new ModelPart(this, 30, 19)).setTexSize(p_i51059_2_, p_i51059_3_);
        this.leftArm.mirror = true;
        this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F + height, 4.0F, p_i51059_1_);
        this.leftArm.setPos(5.0F, 2.0F - 2 * height, 0.0F);

        this.rightLeg = (new ModelPart(this, 48, 19)).setTexSize(p_i51059_2_, p_i51059_3_);
        this.rightLeg.setPos(-2.0F, 12.0F, 0.0F);
        this.rightLeg.addBox(-2.0F, 0.0F  - height, -2.0F, 4.0F, 12.0F + height, 4.0F, p_i51059_1_);
        this.leftLeg = (new ModelPart(this, 48, 19)).setTexSize(p_i51059_2_, p_i51059_3_);
        this.leftLeg.mirror = true;
        this.leftLeg.setPos(2.0F, 12.0F, 0.0F);
        this.leftLeg.addBox(-2.0F, 0.0F  - height, -2.0F, 4.0F, 12.0F + height, 4.0F, p_i51059_1_);

        this.leftArm.visible = false;
        this.rightArm.visible = false;

         */

    }

    public static MeshDefinition createBodyModel(int height) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        float f = 0.5F;
        PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F - 2 * height, -4.0F, 8.0F, 10.0F, 8.0F), PartPose.ZERO);
        PartDefinition partdefinition2 = partdefinition1.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -10.0F - 2 * height, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.ZERO);
        PartDefinition partdefinition3 = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(29, 44).addBox(-4.0F, 0.0F - 2 * height, -3.0F, 8.0F, 12.0F + height, 6.0F), PartPose.ZERO);

        partdefinition1.addOrReplaceChild("small_nose", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F - 2 * height, -5.0F, 2.0F, 1.0F, 1.0F), PartPose.offset(0.0F, -2.0F, 0.0F));
        partdefinition1.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F - 2 * height, -6.0F, 2.0F, 4.0F, 2.0F), PartPose.offset(0.0F, -2.0F, 0.0F));

        partdefinition3.addOrReplaceChild("jacket", CubeListBuilder.create().texOffs(0, 38).addBox(-4.0F, 0.0F - 2 * height, -3.0F, 8.0F, 18.0F + height, 6.0F, new CubeDeformation(0.5F)), PartPose.ZERO);
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(48, 19).addBox(-2.0F, 0.0F - height, -2.0F, 4.0F, 12.0F + height, 4.0F), PartPose.offset(-2.0F, 12.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(48, 19).mirror().addBox(-2.0F, 0.0F - height, -2.0F, 4.0F, 12.0F + height, 4.0F), PartPose.offset(2.0F, 12.0F, 0.0F));
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(30, 19).addBox(-3.0F, -2.0F - 2 * height, -2.0F, 4.0F, 12.0F + height, 4.0F), PartPose.offset(-5.0F, 2.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(30, 19).mirror().addBox(-1.0F, -2.0F - 2 * height, -2.0F, 4.0F, 12.0F + height, 4.0F), PartPose.offset(5.0F, 2.0F, 0.0F));



        return meshdefinition;
    }

    public void setupAnim(T entity, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        NomadState state = NomadState.NORMAL;
        boolean denying = false;

        this.head.yRot = p_225597_5_ * ((float) Math.PI / 180F);
        this.head.xRot = p_225597_6_ * ((float) Math.PI / 180F);
        this.head.zRot = 0.0F;


        this.rightLeg.xRot = Mth.cos(p_225597_2_ * 0.6662F) * 1.4F * p_225597_3_ * 0.5F;
        this.leftLeg.xRot = Mth.cos(p_225597_2_ * 0.6662F + (float) Math.PI) * 1.4F * p_225597_3_ * 0.5F;
        this.rightLeg.yRot = 0.0F;
        this.leftLeg.yRot = 0.0F;
        if (this.riding) {
            this.rightArm.xRot = (-(float)Math.PI / 5F);
            this.rightArm.yRot = 0.0F;
            this.rightArm.zRot = 0.0F;
            this.leftArm.xRot = (-(float)Math.PI / 5F);
            this.leftArm.yRot = 0.0F;
            this.leftArm.zRot = 0.0F;
            this.rightLeg.xRot = -1.4137167F;
            this.rightLeg.yRot = ((float)Math.PI / 10F);
            this.rightLeg.zRot = 0.07853982F;
            this.leftLeg.xRot = -1.4137167F;
            this.leftLeg.yRot = (-(float)Math.PI / 10F);
            this.leftLeg.zRot = -0.07853982F;
        } else {
            this.rightArm.xRot = Mth.cos(p_225597_2_ * 0.6662F + (float)Math.PI) * 2.0F * p_225597_3_ * 0.5F;
            this.rightArm.yRot = 0.0F;
            this.rightArm.zRot = 0.0F;
            this.leftArm.xRot = Mth.cos(p_225597_2_ * 0.6662F) * 2.0F * p_225597_3_ * 0.5F;
            this.leftArm.yRot = 0.0F;
            this.leftArm.zRot = 0.0F;
            this.rightLeg.xRot = Mth.cos(p_225597_2_ * 0.6662F) * 1.4F * p_225597_3_ * 0.5F;
            this.rightLeg.yRot = 0.0F;
            this.rightLeg.zRot = 0.0F;
            this.leftLeg.xRot = Mth.cos(p_225597_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_225597_3_ * 0.5F;
            this.leftLeg.yRot = 0.0F;
            this.leftLeg.zRot = 0.0F;
        }

        if (denying) {

            this.head.yRot = 0.3F * Mth.sin(0.45F * p_225597_4_ / 2);
            this.head.xRot = 0.4F;

        }
        if(state.isCasting()){

            this.rightArm.z = 0.0F;
            this.rightArm.x = -5.0F;
            this.leftArm.z = 0.0F;
            this.leftArm.x = 5.0F;
            this.rightArm.xRot = (float) (-(Math.PI / 12) + (Mth.cos(p_225597_4_ * 1 / 6) * -0.7894));
            this.leftArm.xRot = this.rightArm.xRot;
            this.rightArm.zRot = (float) ((2 * Math.PI / 3) - (Mth.sin(p_225597_4_ * 1 / 6) * -0.7894));
            this.leftArm.zRot = (float) (-(2 * Math.PI / 3) + (Mth.sin(p_225597_4_ * 1 / 6) * -0.7894));
            this.rightArm.yRot = 0.0F;
            this.leftArm.yRot = 0.0F;

        } else if(state.isVanishing()){

            this.rightArm.z = 0.0F;
            this.rightArm.x = -5.0F;
            this.leftArm.z = 0.0F;
            this.leftArm.x = 5.0F;
            this.rightArm.xRot = Mth.cos(p_225597_4_ * 0.6662F) * 0.25F;
            this.leftArm.xRot = Mth.cos(p_225597_4_ * 0.6662F) * 0.25F;
            this.rightArm.zRot = 2.3561945F;
            this.leftArm.zRot = -2.3561945F;
            this.rightArm.yRot = 0.0F;
            this.leftArm.yRot = 0.0F;
        }

        boolean flag = state.isCrossed();
        this.leftArm.visible = !flag;
        this.rightArm.visible = !flag;
    }

    public ModelPart getHead() {
        return this.head;
    }

    public void hatVisible(boolean p_217146_1_) {
        this.head.visible = p_217146_1_;
        this.hat.visible = p_217146_1_;
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    private ModelPart getArm(HumanoidArm p_102923_) {
        return p_102923_ == HumanoidArm.LEFT ? this.leftArm : this.rightArm;
    }

    @Override
    public void translateToHand(HumanoidArm p_102108_, PoseStack p_102109_) {
        this.getArm(p_102108_).translateAndRotate(p_102109_);
    }
}
