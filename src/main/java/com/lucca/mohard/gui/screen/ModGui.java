package com.lucca.mohard.gui.screen;

import com.lucca.mohard.help.Methods;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import java.util.Random;

public class ModGui {

    public static final ResourceLocation GUI_ICONS_LOCATION = new ResourceLocation("textures/gui/icons.png");

    protected long lastHealthTime;
    protected long healthBlinkTime;
    protected int tickCount;
    protected int lastHealth;
    protected final Random random = new Random();
    protected int displayHealth;
    public int left_height = 39;
    public int right_height = 39;



    public void renderHealth(Gui gui, int width, int height, PoseStack pStack)
    {
        Minecraft minecraft = Minecraft.getInstance();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_ICONS_LOCATION);
        minecraft.getProfiler().push("health");
        RenderSystem.enableBlend();

        Player player = (Player)minecraft.getCameraEntity();
        double healthPlayer = player.getHealth();
        int health = (int) Math.ceil((healthPlayer / player.getMaxHealth()) * 20);
        boolean highlight = healthBlinkTime > (long)tickCount && (healthBlinkTime - (long)tickCount) / 3L %2L == 1L;

        if (health < this.lastHealth && player.invulnerableTime > 0)
        {
            this.lastHealthTime = Util.getMillis();
            this.healthBlinkTime = (long)(this.tickCount + 20);
        }
        else if (health > this.lastHealth && player.invulnerableTime > 0)
        {
            this.lastHealthTime = Util.getMillis();
            this.healthBlinkTime = (long)(this.tickCount + 10);
        }

        if (Util.getMillis() - this.lastHealthTime > 1000L)
        {
            this.lastHealth = health;
            this.displayHealth = health;
            this.lastHealthTime = Util.getMillis();
        }

        this.lastHealth = health;
        int healthLast = this.displayHealth;

        AttributeInstance attrMaxHealth = player.getAttribute(Attributes.MAX_HEALTH);
        float healthMax = Math.max((float)attrMaxHealth.getValue(), Math.max(healthLast, health));
        int absorb = Mth.ceil(player.getAbsorptionAmount());

        int healthRows = Mth.ceil((healthMax + absorb) / 2.0F / 10.0F);
        int rowHeight = Math.max(10 - (healthRows - 2), 3);

        this.random.setSeed((long)(tickCount * 312871));

        int left = width / 2 - 91;
        int top = height - left_height;
        left_height += (healthRows * rowHeight);
        if (rowHeight != 10) left_height += 10 - rowHeight;

        int regen = -1;
        if (player.hasEffect(MobEffects.REGENERATION))
        {
            regen = this.tickCount % Mth.ceil(healthMax + 5.0F);
        }

        renderHearts(gui, pStack, player, left, top, rowHeight, regen, healthMax, health, healthLast, absorb, highlight);

        Component healthString = Methods.stringToText((int) Math.ceil((player.getHealth()))+"");
        int x = (width / 2) - 91;
        int y = (height) - 39;
        float xM = (float)(x + 41 - gui.getFont().width(healthString.getVisualOrderText()) / 2);
        float yM = (float) y - 5;

        gui.getFont().draw(pStack, healthString.getVisualOrderText(), xM - 1, yM, 0);
        gui.getFont().draw(pStack, healthString.getVisualOrderText(), xM + 1, yM, 0);
        gui.getFont().draw(pStack, healthString.getVisualOrderText(), xM, yM - 1, 0);
        gui.getFont().draw(pStack, healthString.getVisualOrderText(), xM, yM + 1, 0);
        gui.getFont().draw(pStack, healthString.getVisualOrderText(), xM, yM, 8453920);
        RenderSystem.disableBlend();
        minecraft.getProfiler().pop();
    }
    protected void renderHearts(Gui gui, PoseStack p_168689_, Player p_168690_, int p_168691_, int p_168692_, int p_168693_, int p_168694_, float p_168695_, int p_168696_, int p_168697_, int p_168698_, boolean p_168699_) {
        Minecraft minecraft = Minecraft.getInstance();

        String gui$hearttype = forPlayer(p_168690_);

        int i = 9 * (p_168690_.level.getLevelData().isHardcore() ? 5 : 0);
        int j = Mth.ceil((double)p_168695_ / 2.0D);
        int k = Mth.ceil((double)p_168698_ / 2.0D);
        int l = j * 2;

        for(int i1 = j + k - 1; i1 >= 0; --i1) {
            int j1 = i1 / 10;
            int k1 = i1 % 10;
            int l1 = p_168691_ + k1 * 8;
            int i2 = p_168692_ - j1 * p_168693_;
            if (p_168696_ + p_168698_ <= 4) {
                i2 += this.random.nextInt(2);
            }

            if (i1 < j && i1 == p_168694_) {
                i2 -= 2;
            }

            this.renderHeart(gui, p_168689_, "CONTAINER", l1, i2, i, p_168699_, false);
            int j2 = i1 * 2;
            boolean flag = i1 >= j;
            if (flag) {
                int k2 = j2 - l;
                if (k2 < p_168698_) {
                    boolean flag1 = k2 + 1 == p_168698_;
                    this.renderHeart(gui, p_168689_, gui$hearttype == "WITHERED" ? gui$hearttype : "ABSORBING", l1, i2, i, false, flag1);
                }
            }

            if (p_168699_ && j2 < p_168697_) {
                boolean flag2 = j2 + 1 == p_168697_;
                this.renderHeart(gui, p_168689_, gui$hearttype, l1, i2, i, true, flag2);
            }

            if (j2 < p_168696_) {
                boolean flag3 = j2 + 1 == p_168696_;
                this.renderHeart(gui, p_168689_, gui$hearttype, l1, i2, i, false, flag3);
            }
        }




    }

    private void renderHeart(Gui gui, PoseStack p_168701_, String heartType, int p_168703_, int p_168704_, int p_168705_, boolean p_168706_, boolean p_168707_) {
        gui.blit(p_168701_, p_168703_, p_168704_, getX(heartType, p_168707_, p_168706_), p_168705_, 9, 9);
    }

    public int getX(String heartTypeString, boolean p_168735_, boolean p_168736_) {
        int i;
        if (heartTypeString == "CONTAINER") {
            i = p_168736_ ? 1 : 0;
        } else {
            int j = p_168735_ ? 1 : 0;
            int k = canBlink(heartTypeString) && p_168736_ ? 2 : 0;
            i = j + k;
        }

        return 16 + (getIndex(heartTypeString) * 2 + i) * 9;
    }

    static String forPlayer(Player p_168733_) {
        String gui$hearttype;
        if (p_168733_.hasEffect(MobEffects.POISON)) {
            gui$hearttype = "POISONED";
        } else if (p_168733_.hasEffect(MobEffects.WITHER)) {
            gui$hearttype = "WITHERED";
        } else if (p_168733_.isFullyFrozen()) {
            gui$hearttype = "FROZEN";
        } else {
            gui$hearttype = "NORMAL";
        }

        return gui$hearttype;
    }
    static boolean canBlink(String heartTypeString){
        return (heartTypeString == "POISONED" ||
                heartTypeString == "WITHERED" ||
                heartTypeString == "NORMAL");
    }
    static int getIndex(String heartTypeString){
        int i = 0;
        if(heartTypeString == "NORMAL") i = 2;
        if(heartTypeString == "POISONED") i = 4;
        if(heartTypeString == "WITHERED") i = 6;
        if(heartTypeString == "ABSORBING") i = 8;
        if(heartTypeString == "FROZEN") i = 9;
        return i;
    }
}

