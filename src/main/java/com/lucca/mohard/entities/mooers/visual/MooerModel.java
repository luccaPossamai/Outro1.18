package com.lucca.mohard.entities.mooers.visual;

import com.lucca.mohard.entities.mooers.AbstractMooerEntity;
import com.lucca.mohard.entities.mooers.AI.MooerAction;
import com.lucca.mohard.entities.mooers.MooerEntity;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.Mob;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.npc.AbstractVillager;

public class MooerModel<T extends Mob> extends PlayerModel<T> {

    public final ModelPart rightEar = this.head.getChild("right_ear");
    private final ModelPart leftEar = this.head.getChild("left_ear");
    private final PartPose bodyDefault = this.body.storePose();
    private final PartPose headDefault = this.head.storePose();
    private final PartPose leftArmDefault = this.leftArm.storePose();
    private final PartPose rightArmDefault = this.rightArm.storePose();



    public MooerModel(ModelPart modelPart) {
        super(modelPart, false);
    }

    public static MeshDefinition createMesh(CubeDeformation p_170812_) {
        MeshDefinition meshdefinition = PlayerModel.createMesh(p_170812_, false);
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, p_170812_), PartPose.ZERO);
        PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -8.0F, -4.0F, 10.0F, 8.0F, 8.0F, p_170812_), PartPose.ZERO);
        partdefinition1.addOrReplaceChild("focinho", CubeListBuilder.create().texOffs(31, 1).addBox(-3.0F, -3.0F, -5.0F, 6.0F, 3.0F, 1.0F, p_170812_), PartPose.ZERO);
        partdefinition1.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(51, 6).addBox(0.0F, 0.0F, -2.0F, 1.0F, 5.0F, 4.0F, p_170812_), PartPose.offsetAndRotation(4.5F, -6.0F, 0.0F, 0.0F, 0.0F, (-(float)Math.PI / 6F)));
        partdefinition1.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(39, 6).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 5.0F, 4.0F, p_170812_), PartPose.offsetAndRotation(-4.5F, -6.0F, 0.0F, 0.0F, 0.0F, ((float)Math.PI / 6F)));
        partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        return meshdefinition;
    }

    public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        this.body.loadPose(this.bodyDefault);
        this.head.loadPose(this.headDefault);
        boolean flag = false;
        if (p_225597_1_ instanceof MooerEntity) {
            flag = ((MooerEntity)p_225597_1_).getUnhappyCounter() > 0;
        }
        this.leftArm.loadPose(this.leftArmDefault);
        this.rightArm.loadPose(this.rightArmDefault);
        super.setupAnim(p_225597_1_, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);
        float f = ((float)Math.PI / 6F);
        float f1 = p_225597_4_ * 0.1F + p_225597_2_ * 0.5F;
        float f2 = 0.08F + p_225597_3_ * 0.4F;
        this.rightEar.zRot = (-(float)Math.PI / 6F) - Mth.cos(f1 * 1.2F) * f2;
        this.leftEar.zRot = ((float)Math.PI / 6F) + Mth.cos(f1) * f2;
        if (p_225597_1_ instanceof AbstractMooerEntity) {
            AbstractMooerEntity abstractpiglinentity = (AbstractMooerEntity) p_225597_1_;
            MooerAction piglinaction = abstractpiglinentity.getArmPose();
            if (piglinaction == MooerAction.DANCING) {
                float f3 = p_225597_4_ / 60.0F;
                this.leftEar.zRot = ((float) Math.PI / 6F) + ((float) Math.PI / 180F) * Mth.sin(f3 * 30.0F) * 10.0F;
                this.rightEar.zRot = (-(float) Math.PI / 6F) - ((float) Math.PI / 180F) * Mth.cos(f3 * 30.0F) * 10.0F;
                this.head.x = Mth.sin(f3 * 10.0F);
                this.head.y = Mth.sin(f3 * 40.0F) + 0.4F;
                this.leftLeg.z = 2;
                this.rightLeg.z = 2;
                this.rightLeg.xRot = ((float) Math.PI / 16F) + 0.3F + Mth.sin(f3 * 20F) / 2;
                this.leftLeg.xRot = ((float) Math.PI / 16F) + 0.3F + Mth.sin(f3 * 20F) / -2;

                this.rightArm.xRot = (-1) * ((float) Math.PI / 180F) * (70.0F + Mth.cos(f3 * 20.0F) * 40.0F);
                this.leftArm.xRot = (-1) * ((float) Math.PI / 180F) * (70.0F + Mth.cos(f3 * 20.0F) * -40.0F);
                this.rightArm.y = Mth.sin(f3 * 40.0F) * 0.5F + 1.5F;
                this.leftArm.y = Mth.cos(f3 * 40.0F) * 0.5F + 1.5F;
                this.body.xRot = (float) (Math.PI / 16D);
                this.body.y = Mth.sin(f3 * 40.0F) * 0.35F;
            } else if (piglinaction == MooerAction.READING) {
                this.head.xRot = 0.5F;
                this.head.yRot = 0.0F;

                this.rightArm.xRot = -0.9F;

                this.leftArm.xRot = -0.9F;

            }
            if (flag) {
                this.head.zRot = 0.3F * Mth.sin(0.45F * p_225597_4_);
                this.head.xRot = 0.4F;
            }

        }

        this.leftPants.copyFrom(this.leftLeg);
        this.rightPants.copyFrom(this.rightLeg);
        this.leftSleeve.copyFrom(this.leftArm);
        this.rightSleeve.copyFrom(this.rightArm);
        this.jacket.copyFrom(this.body);
        this.hat.copyFrom(this.head);
    }



}
