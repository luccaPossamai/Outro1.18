package com.lucca.mohard.gui.screen;

import com.lucca.mohard.capabilities.ModCapabilities;
import com.lucca.mohard.capabilities.mana.ManaCapability;
import com.lucca.mohard.help.Methods;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderMana {

    private static final ResourceLocation ICON_LOCATION = new ResourceLocation("mohard", "textures/gui/mod_icons.png");
    private static int lastMana;
    private static int ticks;
    private static int lastTicks;
    private static int count = 0;

    public void renderModMana(Gui gui, PoseStack matrixStack, int screenWidth, int screenHeight, Player jogador){

        ManaCapability manaCapability = jogador.getCapability(ModCapabilities.MANA_CAPABILITY).orElse(null);
        int mana = jogador.isAlive() ? manaCapability.getMana() : 0;
        int maxMana = jogador.isAlive() ? manaCapability.getMaxMana() : 600 ;
        ticks = Minecraft.getInstance().gui.getGuiTicks() + 20;
        if(lastMana == 0) {
            lastMana = mana;
        }
        int x = (screenWidth / 2) + 91;
        int y = (screenHeight) - 49;
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, ICON_LOCATION);
        int percentual = (int) Math.ceil(((float) mana / maxMana) * 10);
        if(percentual > 10){
            percentual = 10;
        }

        Component manaString = Methods.stringToText((int) Math.ceil((mana))+"");

        //templates
        for(int u = 0; u < 10; u++) {
            if (u == percentual - 1) {
                gui.blit(matrixStack, x - (u * 8) - 9, y - 2, 1 + (getCount(mana) * 10), 55, 9, 9);
            } else {
                gui.blit(matrixStack, x - (u * 8) - 9, y, 1 + (getCount(mana) * 10), 55, 9, 9);
            }
        }

        //"hearts"
        for(int i = 0; i < percentual; i++) {
            int wave = (mana > lastMana && ((ticks % 35) - 1) == i)? 2 : 0;
            if (i == percentual - 1) {
                gui.blit(matrixStack, x - 8 - (i * 8), y - 1 - wave, 32 + (getCount(mana) * 9), 56, 7, 7);
            } else {
                gui.blit(matrixStack, x - 8 - (i * 8), y - wave + 1, 32 + (getCount(mana) * 9), 56, 7, 7);

            }
        }

        float xM = (float)(x - 43 - gui.getFont().width(manaString.getVisualOrderText()) / 2);
        float yM = (float) y - 1;

        RenderSystem.defaultBlendFunc();
        gui.getFont().draw(matrixStack, manaString.getVisualOrderText(), xM - 1, yM, 723723);
        gui.getFont().draw(matrixStack, manaString.getVisualOrderText(), xM + 1, yM, 723723);
        gui.getFont().draw(matrixStack, manaString.getVisualOrderText(), xM, yM - 1, 723723);
        gui.getFont().draw(matrixStack, manaString.getVisualOrderText(), xM, yM + 1, 723723);
        gui.getFont().draw(matrixStack, manaString.getVisualOrderText(), xM, yM, 865636);



        if(lastMana != mana && count < 1) {
            count = 1;
        }

        if(ticks >= lastTicks + 1) {
            lastTicks = ticks;
            if (count >= 1) {
                count++;
            }
            if(count > 20){
                count = 0;
                lastMana = mana;
            }
        }


    }

    private int getCount(int mana){
        int countage = (int) Math.ceil(count % 40 / 5) - 1;
        if(countage < 0) countage = 0;
        if(mana == lastMana) return 1;
        if(countage > 1){
            countage = countage - 2;
        }
        return mana > lastMana ? countage + 1
        : countage;
    }
}
