package com.lucca.mohard.gui.screen;

import com.lucca.mohard.help.Methods;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderHealth {

    private static final ResourceLocation ICON_LOCATION = new ResourceLocation("mohard", "textures/gui/mod_icons.png");
    private static float lastHealth;
    private static int ticks;
    private static int lastTicks;
    private static int count = 0;

    public void renderModHealth(Gui gui, PoseStack matrixStack, int screenWidth, int screenHeight, Player jogador){

        float health = jogador.getHealth();
        ticks = Minecraft.getInstance().gui.getGuiTicks() + 20;
        if(lastHealth == 0) {
            lastHealth = health;
        }
        int x = (screenWidth / 2) - 91;
        int y = (screenHeight) - 39;
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, ICON_LOCATION);
        int percentual = (int) Math.ceil((health / jogador.getMaxHealth()) * 14);
        if(percentual > 14){
            percentual = 14;
        }

        Component healthString = Methods.stringToText((int) Math.ceil((jogador.getHealth()))+"");

        if(jogador.hasEffect(MobEffects.ABSORPTION)) {
            healthString = Methods.stringToText((int) Math.ceil((jogador.getHealth()))+" + "+(int) Math.ceil((jogador.getAbsorptionAmount())));
        }
        int t = getHealthType(jogador);

        //templates
        for(int u = 0; u < 14; u++) {
            if (u == percentual - 1) {
                gui.blit(matrixStack, x + (u * 6), y - 2, 1 + (getCount(jogador) * 8), 0 + (t * 9), 7, 9);
            } else {
                gui.blit(matrixStack, x + (u * 6), y, 1 + (getCount(jogador) * 8), 0 + (t * 9), 7, 9);
            }
        }

        //"hearts"
        for(int i = 0; i < percentual; i++) {
            int wave = (jogador.getHealth() > lastHealth && ((ticks % 35) - 1) == i)? 2 : 0;
            if (i == percentual - 1) {


                gui.blit(matrixStack, x + 1 + (i * 6), y - 1 - wave, 25 + (getCount(jogador) * 6), 1 + (t * 9), 5, 7);


            } else {
                gui.blit(matrixStack, x + 1 + (i * 6), y + 1 - wave, 25 + (getCount(jogador) * 6), 1 + (t * 9), 5, 7);

            }
        }

        float xM = (float)(x + 43 - gui.getFont().width(healthString.getVisualOrderText()) / 2);
        float yM = (float) y - 1;

        RenderSystem.defaultBlendFunc();
        gui.getFont().draw(matrixStack, healthString.getVisualOrderText(), xM - 1, yM, 723723);
        gui.getFont().draw(matrixStack, healthString.getVisualOrderText(), xM + 1, yM, 723723);
        gui.getFont().draw(matrixStack, healthString.getVisualOrderText(), xM, yM - 1, 723723);
        gui.getFont().draw(matrixStack, healthString.getVisualOrderText(), xM, yM + 1, 723723);
        gui.getFont().draw(matrixStack, healthString.getVisualOrderText(), xM, yM, getHealthTypeColor(jogador));



        if(lastHealth != health && count < 1) {
            count = 1;
        }

        if(ticks >= lastTicks + 1) {
            lastTicks = ticks;
            if (count >= 1) {
                count++;
            }
            if(count > 20){
                count = 0;
                lastHealth = health;
            }
        }


    }

    private int getCount(Player jogador){
        int countage = (int) Math.ceil(count % 40 / 5) - 1;
        if(countage < 0) countage = 0;
        if(jogador.getHealth() == lastHealth) return 1;
        if(countage > 1){
            countage = countage - 2;
        }
        return jogador.getHealth() > lastHealth ? countage + 1
        : countage;
    }

    private int getHealthType(Player jogador){
        return  jogador.isOnFire() ? 5 :
                jogador.isFullyFrozen() ? 4 :
                jogador.hasEffect(MobEffects.WITHER) ? 3 :
                jogador.hasEffect(MobEffects.POISON) ? 1 :
                        jogador.hasEffect(MobEffects.ABSORPTION) ? 2 :
                                0;
    }

    private int getHealthTypeColor(Player jogador){
        int count = getHealthType(jogador);
        switch (count){
            case 0:
                return 16716563;
            case 1:
                return 9144082;
            case 2:
                return 14596157;
            case 3:
                return 3871507;
            case 4:
                return 4571118;
            case 5:
                return 12077327;
        }
        return 16716563;
    }


}
