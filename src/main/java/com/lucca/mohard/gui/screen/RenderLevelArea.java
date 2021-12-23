package com.lucca.mohard.gui.screen;

import com.lucca.mohard.evolution.GameLevel;
import com.lucca.mohard.help.Methods;
import com.lucca.mohard.mechanics.adventure.PlayerWalk;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Gui;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class RenderLevelArea {

    private int ticks;
    private final int actualTick;

    public RenderLevelArea(int actualTick){
        this.actualTick = actualTick + 100;
    }

    public void renderLevelArea(Gui gui, PoseStack matrixStack, int screenWidth, int screenHeight, Player jogador){

        ticks = this.actualTick - gui.getGuiTicks();

        int l = (int) ((float) ticks * 256.0F / 10.0F);
        if (l > 255) {
            l = 255;
        }

        int x = (screenWidth / 2) - 91;
        int y = (screenHeight) - 60;
        int level = (int) GameLevel.getLevel(jogador.getX(), jogador.getZ(), jogador.getLevel());
        Component levelString = Methods.stringToText("Level "+level+" zone");


        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        float xM = (float)(x + 43 - gui.getFont().width(levelString.getVisualOrderText()) / 2);
        float yM = (float) y - 1;
        if(ticks > 0) {
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            gui.getFont().drawShadow(matrixStack, levelString.getVisualOrderText(), xM, yM, 16777215 + (l << 24));

            //gui.getFont().draw(matrixStack, levelString.getVisualOrderText(), xM - 1, yM, 723723);
            //gui.getFont().draw(matrixStack, levelString.getVisualOrderText(), xM + 1, yM, 723723);
           // gui.getFont().draw(matrixStack, levelString.getVisualOrderText(), xM, yM - 1, 723723);
            //gui.getFont().draw(matrixStack, levelString.getVisualOrderText(), xM, yM + 1, 723723);
            //gui.getFont().draw(matrixStack, levelString.getVisualOrderText(), xM, yM, 16716563);
            RenderSystem.disableBlend();
        } else {
            PlayerWalk.shouldShowLevel.remove(jogador);
        }

    }


}
