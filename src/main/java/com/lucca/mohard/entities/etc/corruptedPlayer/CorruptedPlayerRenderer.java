package com.lucca.mohard.entities.etc.corruptedPlayer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.phys.Vec3;

public class CorruptedPlayerRenderer extends LivingEntityRenderer<CorruptedPlayer, PlayerModel<CorruptedPlayer>> {

    public CorruptedPlayerRenderer(EntityRendererProvider.Context p_174557_, boolean p_174558_) {
        super(p_174557_, new PlayerModel<>(p_174557_.bakeLayer(p_174558_ ? ModelLayers.PLAYER_SLIM : ModelLayers.PLAYER), p_174558_), 0.5F);
        this.addLayer(new CorruptedPlayerLayer<>(this, p_174557_.getModelSet()));
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel(p_174557_.bakeLayer(p_174558_ ? ModelLayers.PLAYER_SLIM_INNER_ARMOR : ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel(p_174557_.bakeLayer(p_174558_ ? ModelLayers.PLAYER_SLIM_OUTER_ARMOR : ModelLayers.PLAYER_OUTER_ARMOR))));
        this.addLayer(new PlayerItemInHandLayer(this));
        this.addLayer(new ArrowLayer<>(p_174557_, this));
        this.addLayer(new CustomHeadLayer<>(this, p_174557_.getModelSet()));
        this.addLayer(new ElytraLayer<>(this, p_174557_.getModelSet()));
        this.addLayer(new SpinAttackEffectLayer<>(this, p_174557_.getModelSet()));
        this.addLayer(new BeeStingerLayer<>(this));
    }

    public Vec3 getRenderOffset(CorruptedPlayer p_117785_, float p_117786_) {
        return p_117785_.isCrouching() ? new Vec3(0.0D, -0.125D, 0.0D) : super.getRenderOffset(p_117785_, p_117786_);
    }

    private void setModelProperties(CorruptedPlayer p_117819_) {
        PlayerModel<CorruptedPlayer> playermodel = this.getModel();
        if (p_117819_.isSpectator()) {
            playermodel.setAllVisible(false);
            playermodel.head.visible = true;
            playermodel.hat.visible = true;
        } else {
            playermodel.setAllVisible(true);
            playermodel.hat.visible = p_117819_.getRealPlayer().isModelPartShown(PlayerModelPart.HAT);
            playermodel.jacket.visible = p_117819_.getRealPlayer().isModelPartShown(PlayerModelPart.JACKET);
            playermodel.leftPants.visible = p_117819_.getRealPlayer().isModelPartShown(PlayerModelPart.LEFT_PANTS_LEG);
            playermodel.rightPants.visible = p_117819_.getRealPlayer().isModelPartShown(PlayerModelPart.RIGHT_PANTS_LEG);
            playermodel.leftSleeve.visible = p_117819_.getRealPlayer().isModelPartShown(PlayerModelPart.LEFT_SLEEVE);
            playermodel.rightSleeve.visible = p_117819_.getRealPlayer().isModelPartShown(PlayerModelPart.RIGHT_SLEEVE);
            playermodel.crouching = p_117819_.isCrouching();
            HumanoidModel.ArmPose humanoidmodel$armpose = getArmPose(p_117819_, InteractionHand.MAIN_HAND);
            HumanoidModel.ArmPose humanoidmodel$armpose1 = getArmPose(p_117819_, InteractionHand.OFF_HAND);
            if (humanoidmodel$armpose.isTwoHanded()) {
                humanoidmodel$armpose1 = p_117819_.getOffhandItem().isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;
            }

            if (p_117819_.getMainArm() == HumanoidArm.RIGHT) {
                playermodel.rightArmPose = humanoidmodel$armpose;
                playermodel.leftArmPose = humanoidmodel$armpose1;
            } else {
                playermodel.rightArmPose = humanoidmodel$armpose1;
                playermodel.leftArmPose = humanoidmodel$armpose;
            }
        }

    }

    private static HumanoidModel.ArmPose getArmPose(CorruptedPlayer p_117795_, InteractionHand p_117796_) {
        ItemStack itemstack = p_117795_.getItemInHand(p_117796_);
        if (itemstack.isEmpty()) {
            return HumanoidModel.ArmPose.EMPTY;
        } else {
            if (p_117795_.getUsedItemHand() == p_117796_ && p_117795_.getUseItemRemainingTicks() > 0) {
                UseAnim useanim = itemstack.getUseAnimation();
                if (useanim == UseAnim.BLOCK) {
                    return HumanoidModel.ArmPose.BLOCK;
                }

                if (useanim == UseAnim.BOW) {
                    return HumanoidModel.ArmPose.BOW_AND_ARROW;
                }

                if (useanim == UseAnim.SPEAR) {
                    return HumanoidModel.ArmPose.THROW_SPEAR;
                }

                if (useanim == UseAnim.CROSSBOW && p_117796_ == p_117795_.getUsedItemHand()) {
                    return HumanoidModel.ArmPose.CROSSBOW_CHARGE;
                }

                if (useanim == UseAnim.SPYGLASS) {
                    return HumanoidModel.ArmPose.SPYGLASS;
                }
            } else if (!p_117795_.swinging && itemstack.is(Items.CROSSBOW) && CrossbowItem.isCharged(itemstack)) {
                return HumanoidModel.ArmPose.CROSSBOW_HOLD;
            }

            return HumanoidModel.ArmPose.ITEM;
        }
    }

    public ResourceLocation getTextureLocation(CorruptedPlayer p_117783_) {
        return this.getPlayerSkin(p_117783_);
    }

    protected void scale(CorruptedPlayer p_117798_, PoseStack p_117799_, float p_117800_) {
        float f = 0.9375F;
        p_117799_.scale(0.9375F, 0.9375F, 0.9375F);
    }

    public void renderRightHand(PoseStack p_117771_, MultiBufferSource p_117772_, int p_117773_, CorruptedPlayer p_117774_) {
        this.renderHand(p_117771_, p_117772_, p_117773_, p_117774_, (this.model).rightArm, (this.model).rightSleeve);
    }

    public void renderLeftHand(PoseStack p_117814_, MultiBufferSource p_117815_, int p_117816_, CorruptedPlayer p_117817_) {
        this.renderHand(p_117814_, p_117815_, p_117816_, p_117817_, (this.model).leftArm, (this.model).leftSleeve);
    }

    private void renderHand(PoseStack p_117776_, MultiBufferSource p_117777_, int p_117778_, CorruptedPlayer p_117779_, ModelPart p_117780_, ModelPart p_117781_) {
        PlayerModel<CorruptedPlayer> playermodel = this.getModel();
        this.setModelProperties(p_117779_);
        playermodel.attackTime = 0.0F;
        playermodel.crouching = false;
        playermodel.swimAmount = 0.0F;
        playermodel.setupAnim(p_117779_, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        p_117780_.xRot = 0.0F;
        p_117780_.render(p_117776_, p_117777_.getBuffer(RenderType.entitySolid(this.getPlayerSkin(p_117779_))), p_117778_, OverlayTexture.NO_OVERLAY);
        p_117781_.xRot = 0.0F;
        p_117781_.render(p_117776_, p_117777_.getBuffer(RenderType.entityTranslucent(this.getPlayerSkin(p_117779_))), p_117778_, OverlayTexture.NO_OVERLAY);
    }

    protected void setupRotations(CorruptedPlayer p_117802_, PoseStack p_117803_, float p_117804_, float p_117805_, float p_117806_) {
        float f = p_117802_.getSwimAmount(p_117806_);
        if (p_117802_.isFallFlying()) {
            super.setupRotations(p_117802_, p_117803_, p_117804_, p_117805_, p_117806_);
            float f1 = (float) p_117802_.getFallFlyingTicks() + p_117806_;
            float f2 = Mth.clamp(f1 * f1 / 100.0F, 0.0F, 1.0F);
            if (!p_117802_.isAutoSpinAttack()) {
                p_117803_.mulPose(Vector3f.XP.rotationDegrees(f2 * (-90.0F - p_117802_.getXRot())));
            }

            Vec3 vec3 = p_117802_.getViewVector(p_117806_);
            Vec3 vec31 = p_117802_.getDeltaMovement();
            double d0 = vec31.horizontalDistanceSqr();
            double d1 = vec3.horizontalDistanceSqr();
            if (d0 > 0.0D && d1 > 0.0D) {
                double d2 = (vec31.x * vec3.x + vec31.z * vec3.z) / Math.sqrt(d0 * d1);
                double d3 = vec31.x * vec3.z - vec31.z * vec3.x;
                p_117803_.mulPose(Vector3f.YP.rotation((float) (Math.signum(d3) * Math.acos(d2))));
            }
        } else if (f > 0.0F) {
            super.setupRotations(p_117802_, p_117803_, p_117804_, p_117805_, p_117806_);
            float f3 = p_117802_.isInWater() ? -90.0F - p_117802_.getXRot() : -90.0F;
            float f4 = Mth.lerp(f, 0.0F, f3);
            p_117803_.mulPose(Vector3f.XP.rotationDegrees(f4));
            if (p_117802_.isVisuallySwimming()) {
                p_117803_.translate(0.0D, -1.0D, (double) 0.3F);
            }
        } else {
            super.setupRotations(p_117802_, p_117803_, p_117804_, p_117805_, p_117806_);
        }

    }

    private ResourceLocation getPlayerSkin(CorruptedPlayer p_117783_){
        if(p_117783_.getRealPlayer() != null) {
            if (p_117783_.getRealPlayer() instanceof AbstractClientPlayer) {
                AbstractClientPlayer abstractClientPlayer = (AbstractClientPlayer) p_117783_.getRealPlayer();
                return abstractClientPlayer.getSkinTextureLocation();
            }
        }
        return new ResourceLocation("textures/entity/steve.png");
    }

}
