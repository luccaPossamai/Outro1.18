package com.lucca.mohard.gui.screen;

import com.lucca.mohard.capabilities.ModCapabilities;
import com.lucca.mohard.evolution.PlayerEvolution;
import com.lucca.mohard.itens.essence.essenceHabilities.EssenceHabilities;
import com.lucca.mohard.mechanics.adventure.PlayerWalk;
import com.lucca.mohard.setup.init.ModEffects;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraft.client.gui.GuiComponent.GUI_ICONS_LOCATION;


@Mod.EventBusSubscriber
public class PlayerGUI{

    private static final ResourceLocation ICON_LOCATION = new ResourceLocation("mohard", "textures/gui/mod_icons.png");


    public static final IIngameOverlay MODDED_PLAYER_HEALTH_ELEMENT = OverlayRegistry.registerOverlayTop("Player Mod Health", (gui, mStack, partialTicks, screenWidth, screenHeight) -> {
        if (!Minecraft.getInstance().options.hideGui && gui.shouldDrawSurvivalElements())
        {
            gui.setupOverlayRenderState(true, false);
            new RenderHealth().renderModHealth(gui, mStack, screenWidth, screenHeight, Minecraft.getInstance().player);
        }
    });

    public static final IIngameOverlay MODDED_PLAYER_MANA_ELEMENT = OverlayRegistry.registerOverlayTop("Player Mod Mana", (gui, mStack, partialTicks, screenWidth, screenHeight) -> {
        if (!Minecraft.getInstance().options.hideGui && gui.shouldDrawSurvivalElements() && Minecraft.getInstance().player.getCapability(ModCapabilities.MANA_CAPABILITY) != null)
        {
            gui.setupOverlayRenderState(true, false);
            new RenderMana().renderModMana(gui, mStack, screenWidth, screenHeight, Minecraft.getInstance().player);
        }
    });

    public static final IIngameOverlay ZONE_LEVEL_ELEMENT = OverlayRegistry.registerOverlayTop("Level Zone", (gui, mStack, partialTicks, screenWidth, screenHeight) -> {
        if (!Minecraft.getInstance().options.hideGui && PlayerWalk.shouldShowLevel.containsKey(Minecraft.getInstance().player))
        {
            Player player = Minecraft.getInstance().player;
            gui.setupOverlayRenderState(true, false);
            if(PlayerWalk.shouldShowLevel.get(player) == null){
                PlayerWalk.shouldShowLevel.remove(player);
                PlayerWalk.shouldShowLevel.put(player, new RenderLevelArea(gui.getGuiTicks()));
            }
            RenderLevelArea render = PlayerWalk.shouldShowLevel.get(player);
            render.renderLevelArea(gui, mStack, screenWidth, screenHeight, player);
        }
    });

    public static final IIngameOverlay MODDED_ARMOR_ELEMENT = OverlayRegistry.registerOverlayTop("Player Mod Armor", (gui, mStack, partialTicks, screenWidth, screenHeight) -> {
        if (!Minecraft.getInstance().options.hideGui && gui.shouldDrawSurvivalElements())
        {
            gui.setupOverlayRenderState(true, false);
            new PlayerGUI().renderArmor(gui, mStack, screenWidth, screenHeight, Minecraft.getInstance().player);
            new PlayerGUI().renderMagicArmor(gui, mStack, screenWidth, screenHeight, Minecraft.getInstance().player);
        }
    });

    public static final IIngameOverlay ESSENCE_HABILITY_DURATION = OverlayRegistry.registerOverlayTop("Player Mod Hability", (gui, mStack, partialTicks, screenWidth, screenHeight) -> {
        if (!Minecraft.getInstance().options.hideGui && gui.shouldDrawSurvivalElements())
        {
            if(PlayerEvolution.onEffect.containsKey(Minecraft.getInstance().player)) {
                gui.setupOverlayRenderState(true, false);
                new PlayerGUI().renderPlayerHability(gui, mStack, screenWidth, screenHeight, Minecraft.getInstance().player) ;
            }
        }
    });

    public static final IIngameOverlay CORRUPTION_OVERLAY = OverlayRegistry.registerOverlayTop("Corruption", (gui, mStack, partialTicks, screenWidth, screenHeight) -> {
        gui.setupOverlayRenderState(true, false);
        new PlayerGUI().renderCorruption(gui, mStack, screenHeight, screenWidth);
    });

    @SubscribeEvent
    public static void cancelRender(RenderGameOverlayEvent.PreLayer event){

        Player jogador = Minecraft.getInstance().player;


        if(event.getOverlay().equals(ForgeIngameGui.PLAYER_HEALTH_ELEMENT)){
            event.setCanceled(true);
        }


        if(event.getOverlay().equals(ForgeIngameGui.ARMOR_LEVEL_ELEMENT)){
            event.setCanceled(true);
        }

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_ICONS_LOCATION);


    }

    private void renderArmor(Gui gui, PoseStack matrixStack, int screenWidth, int screenHeight, Player jogador){
        int x = (screenWidth / 2) - 91;
        int y = (screenHeight) - 51;

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, ICON_LOCATION);
        if(jogador.getArmorValue() != 0){
            int value = jogador.getArmorValue();
            boolean negative = false;
            if(value < 0) {
                value = value * (-1);
                negative = true;
            }
            int contagem = 0;
            while(contagem < 15){
                if(value >= 2){
                    value = value - 2;
                    gui.blit(matrixStack, x + (contagem * 9), y,61 + (negative ? 18 : 0), 32, 9, 9);
                } else if(value == 1){
                    value = 0;
                    gui.blit(matrixStack, x + (contagem * 9), y,52 + (negative ? 18 : 0), 32, 9, 9);
                } else if(value == 0){
                    gui.blit(matrixStack, x + (contagem * 9), y,43, 32, 9, 9);
                }
                contagem++;
            }
        }

    }

    private void renderMagicArmor(Gui gui, PoseStack matrixStack, int screenWidth, int screenHeight, Player jogador){
        int x = (screenWidth / 2) - 91;
        int y = (screenHeight) - 61;

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, ICON_LOCATION);
        if(jogador.getArmorValue() != 0){
            int value = (int) Math.round(PlayerEvolution.magicImunne(jogador));
            boolean negative = false;
            if(value < 0) {
                value = value * (-1);
                negative = true;
            }
            int contagem = 0;
            while(contagem < 5){
                if(value >= 2){
                    value = value - 2;
                    gui.blit(matrixStack, x + (contagem * 9), y, 61 + (negative ? 18 : 0), 42, 9, 9);
                } else if(value == 1){
                    value = 0;
                    gui.blit(matrixStack, x + (contagem * 9), y, 52 + (negative ? 18 : 0), 42, 9, 9);
                } else if(value == 0){
                    gui.blit(matrixStack, x + (contagem * 9), y, 43, 42, 9, 9);
                }
                contagem++;
            }
        }

    }


    private void renderPlayerHability(Gui gui, PoseStack matrixStack, int screenWidth, int screenHeight, Player jogador){
        int x = (screenWidth / 2) - 91;
        int y = screenHeight - 47 - (20 * (jogador.getArmorValue() > 0 ? 1 : 0));
        Minecraft.getInstance().getProfiler().pop();
        Minecraft.getInstance().getProfiler().push("expBar");
        RenderSystem.setShaderTexture(0, ICON_LOCATION);
        EssenceHabilities habilities = PlayerEvolution.getPlayerHability(jogador);
        int typeBar = habilities == null ? 0 : habilities.getCategory().getCategoryId();
        int maxDuration = PlayerEvolution.onEffect.containsKey(jogador) ? PlayerEvolution.onEffect.get(jogador).getMaxDuration() : 0;
        int duration = PlayerEvolution.onEffect.containsKey(jogador) ? PlayerEvolution.onEffect.get(jogador).getDuration() : 0;

        int i = (int) Math.round((( (double) duration / (double) maxDuration) * 102D));
        gui.blit(matrixStack, x, y, 43,6 + (typeBar * 10), 102, 5);
        gui.blit(matrixStack, x, y, 43,1 + (typeBar * 10), i, 5);

        Minecraft.getInstance().getProfiler().pop();
        RenderSystem.enableBlend();
    }

    private static void renderCorruption(Gui gui, PoseStack stack, int height, int width){
        Player player = Minecraft.getInstance().player;
        if(player.hasEffect(ModEffects.CORRUPTION.get())){
            float count = 0.2f;
            renderTextureOverlay(new ResourceLocation("mohard:textures/misc/corruption_overlay.png"), count, height, width);

        }
    }

    private static void renderTextureOverlay(ResourceLocation p_168709_, float p_168710_, int height, int width) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, p_168710_);
        RenderSystem.setShaderTexture(0, p_168709_);
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(0.0D, (double)height, -90.0D).uv(0.0F, 1.0F).endVertex();
        bufferbuilder.vertex((double)width, (double)height, -90.0D).uv(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex((double)width, 0.0D, -90.0D).uv(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(0.0F, 0.0F).endVertex();
        tesselator.end();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }

}
