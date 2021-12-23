package com.lucca.mohard.entities.models;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.util.Mth;

public class ArmouredVindicatorModel<T extends AbstractIllager> extends HierarchicalModel<T> implements ArmedModel, HeadedModel {
    private final ModelPart head;
    private final ModelPart hat;
    private final ModelPart root;
    private final ModelPart arms;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart horns;

    public ArmouredVindicatorModel(ModelPart p_170688_) {

        this.root = p_170688_;
        this.head = p_170688_.getChild("head");
        this.hat = this.head.getChild("hat");
        this.horns = this.head.getChild("horns");
        this.hat.visible = true;
        this.arms = p_170688_.getChild("arms");
        this.leftLeg = p_170688_.getChild("left_leg");
        this.rightLeg = p_170688_.getChild("right_leg");
        this.leftArm = p_170688_.getChild("left_arm");
        this.rightArm = p_170688_.getChild("right_arm");

        this.horns.xRot = 0.75F;
        /*
        this.head = (new ModelPart(this)).setTexSize(p_i47227_3_, p_i47227_4_);
        this.head.setPos(0.0F, 0.0F + p_i47227_2_, 0.0F);
        this.head.texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, p_i47227_1_);
        this.hat = (new ModelPart(this, 32, 0)).setTexSize(p_i47227_3_, p_i47227_4_);
        this.horns = (new ModelPart(this, 32, 0)).setTexSize(p_i47227_3_, p_i47227_4_);
        this.horns.texOffs(29, 44).addBox(-6.45F, -11.0F, 3.0F, 2, 4, 2, p_i47227_1_);
        this.horns.texOffs(29, 44).addBox(4.45F, -11.0F, 3.0F, 2, 4, 2, p_i47227_1_);
        this.horns.xRot = 0.75F;
        this.hat.addBox(-4.0F, -10.0F, -4.0F, 8.0F, 12.0F, 8.0F, p_i47227_1_ + 0.45F);
        this.hat.addChild(this.horns);
        this.head.addChild(this.hat);
        this.hat.visible = true;
        ModelPart modelrenderer = (new ModelPart(this)).setTexSize(p_i47227_3_, p_i47227_4_);
        modelrenderer.setPos(0.0F, p_i47227_2_ - 2.0F, 0.0F);
        modelrenderer.texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, p_i47227_1_);
        this.head.addChild(modelrenderer);
        this.body = (new ModelPart(this)).setTexSize(p_i47227_3_, p_i47227_4_);
        this.body.setPos(0.0F, 0.0F + p_i47227_2_, 0.0F);
        this.body.texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, p_i47227_1_);
        this.body.texOffs(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, p_i47227_1_ + 0.5F);
        this.arms = (new ModelPart(this)).setTexSize(p_i47227_3_, p_i47227_4_);
        this.arms.setPos(0.0F, 0.0F + p_i47227_2_ + 2.0F, 0.0F);
        this.arms.texOffs(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, p_i47227_1_);
        ModelPart modelrenderer1 = (new ModelPart(this, 44, 22)).setTexSize(p_i47227_3_, p_i47227_4_);
        modelrenderer1.mirror = true;
        modelrenderer1.addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, p_i47227_1_);
        this.arms.addChild(modelrenderer1);
        this.arms.texOffs(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F, p_i47227_1_);
        this.leftLeg = (new ModelPart(this, 0, 22)).setTexSize(p_i47227_3_, p_i47227_4_);
        this.leftLeg.setPos(-2.0F, 12.0F + p_i47227_2_, 0.0F);
        this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, p_i47227_1_);
        this.rightLeg = (new ModelPart(this, 0, 22)).setTexSize(p_i47227_3_, p_i47227_4_);
        this.rightLeg.mirror = true;
        this.rightLeg.setPos(2.0F, 12.0F + p_i47227_2_, 0.0F);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, p_i47227_1_);
        this.rightArm = (new ModelPart(this, 40, 46)).setTexSize(p_i47227_3_, p_i47227_4_);
        this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, p_i47227_1_);
        this.rightArm.setPos(-5.0F, 2.0F + p_i47227_2_, 0.0F);
        this.leftArm = (new ModelPart(this, 40, 46)).setTexSize(p_i47227_3_, p_i47227_4_);
        this.leftArm.mirror = true;
        this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, p_i47227_1_);
        this.leftArm.setPos(5.0F, 2.0F + p_i47227_2_, 0.0F);

         */
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F), PartPose.offset(0.0F, 0.0F, 0.0F));
        partdefinition1.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.45F)), PartPose.ZERO);
        partdefinition1.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F), PartPose.offset(0.0F, -2.0F, 0.0F));
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F).texOffs(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition partdefinition2 = partdefinition.addOrReplaceChild("arms", CubeListBuilder.create().texOffs(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F).texOffs(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F), PartPose.offsetAndRotation(0.0F, 3.0F, -1.0F, -0.75F, 0.0F, 0.0F));
        partdefinition2.addOrReplaceChild("left_shoulder", CubeListBuilder.create().texOffs(44, 22).mirror().addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(-2.0F, 12.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(2.0F, 12.0F, 0.0F));
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 46).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(-5.0F, 2.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 46).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(5.0F, 2.0F, 0.0F));
        partdefinition1.addOrReplaceChild("horns", CubeListBuilder.create().texOffs(29, 44).addBox(-6.45F, -11.0F, 3.0F, 2, 4, 2).addBox(4.45F, -11.0F, 3.0F, 2, 4, 2), PartPose.offset(0, 0 ,0));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        this.head.yRot = p_225597_5_ * ((float)Math.PI / 180F);
        this.head.xRot = p_225597_6_ * ((float)Math.PI / 180F);
        this.arms.y = 3.0F;
        this.arms.z = -1.0F;
        this.arms.xRot = -0.75F;
        if (this.riding) {
            this.rightArm.xRot = (-(float)Math.PI / 5F);
            this.rightArm.yRot = 0.0F;
            this.rightArm.zRot = 0.0F;
            this.leftArm.xRot = (-(float)Math.PI / 5F);
            this.leftArm.yRot = 0.0F;
            this.leftArm.zRot = 0.0F;
            this.leftLeg.xRot = -1.4137167F;
            this.leftLeg.yRot = ((float)Math.PI / 10F);
            this.leftLeg.zRot = 0.07853982F;
            this.rightLeg.xRot = -1.4137167F;
            this.rightLeg.yRot = (-(float)Math.PI / 10F);
            this.rightLeg.zRot = -0.07853982F;
        } else {
            this.rightArm.xRot = Mth.cos(p_225597_2_ * 0.6662F + (float)Math.PI) * 2.0F * p_225597_3_ * 0.5F;
            this.rightArm.yRot = 0.0F;
            this.rightArm.zRot = 0.0F;
            this.leftArm.xRot = Mth.cos(p_225597_2_ * 0.6662F) * 2.0F * p_225597_3_ * 0.5F;
            this.leftArm.yRot = 0.0F;
            this.leftArm.zRot = 0.0F;
            this.leftLeg.xRot = Mth.cos(p_225597_2_ * 0.6662F) * 1.4F * p_225597_3_ * 0.5F;
            this.leftLeg.yRot = 0.0F;
            this.leftLeg.zRot = 0.0F;
            this.rightLeg.xRot = Mth.cos(p_225597_2_ * 0.6662F + (float)Math.PI) * 1.4F * p_225597_3_ * 0.5F;
            this.rightLeg.yRot = 0.0F;
            this.rightLeg.zRot = 0.0F;
        }

        AbstractIllager.IllagerArmPose abstractillagerentity$armpose = p_225597_1_.getArmPose();
        if (abstractillagerentity$armpose == AbstractIllager.IllagerArmPose.ATTACKING) {
            if (p_225597_1_.getMainHandItem().isEmpty()) {
                AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, true, this.attackTime, p_225597_4_);
            } else {
                AnimationUtils.swingWeaponDown(this.rightArm, this.leftArm, p_225597_1_, this.attackTime, p_225597_4_);
            }
        } else if (abstractillagerentity$armpose == AbstractIllager.IllagerArmPose.SPELLCASTING) {
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
        } else if (abstractillagerentity$armpose == AbstractIllager.IllagerArmPose.BOW_AND_ARROW) {
            this.rightArm.yRot = -0.1F + this.head.yRot;
            this.rightArm.xRot = (-(float)Math.PI / 2F) + this.head.xRot;
            this.leftArm.xRot = -0.9424779F + this.head.xRot;
            this.leftArm.yRot = this.head.yRot - 0.4F;
            this.leftArm.zRot = ((float)Math.PI / 2F);
        } else if (abstractillagerentity$armpose == AbstractIllager.IllagerArmPose.CROSSBOW_HOLD) {
            AnimationUtils.animateCrossbowHold(this.rightArm, this.leftArm, this.head, true);
        } else if (abstractillagerentity$armpose == AbstractIllager.IllagerArmPose.CROSSBOW_CHARGE) {
            AnimationUtils.animateCrossbowCharge(this.rightArm, this.leftArm, p_225597_1_, true);
        } else if (abstractillagerentity$armpose == AbstractIllager.IllagerArmPose.CELEBRATING) {
            this.rightArm.z = 0.0F;
            this.rightArm.x = -5.0F;
            this.rightArm.xRot = Mth.cos(p_225597_4_ * 0.6662F) * 0.05F;
            this.rightArm.zRot = 2.670354F;
            this.rightArm.yRot = 0.0F;
            this.leftArm.z = 0.0F;
            this.leftArm.x = 5.0F;
            this.leftArm.xRot = Mth.cos(p_225597_4_ * 0.6662F) * 0.05F;
            this.leftArm.zRot = -2.3561945F;
            this.leftArm.yRot = 0.0F;
        }

        boolean flag = abstractillagerentity$armpose == AbstractIllager.IllagerArmPose.CROSSED;
        this.arms.visible = flag;
        this.leftArm.visible = !flag;
        this.rightArm.visible = !flag;
    }

    private ModelPart getArm(HumanoidArm p_191216_1_) {
        return p_191216_1_ == HumanoidArm.LEFT ? this.leftArm : this.rightArm;
    }

    public ModelPart getHat() {
        return this.hat;
    }

    public ModelPart getHead() {
        return this.head;
    }

    public void translateToHand(HumanoidArm p_225599_1_, PoseStack p_225599_2_) {
        this.getArm(p_225599_1_).translateAndRotate(p_225599_2_);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}
