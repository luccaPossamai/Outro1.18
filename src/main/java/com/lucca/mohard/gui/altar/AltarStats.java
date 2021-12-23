package com.lucca.mohard.gui.altar;

import com.lucca.mohard.block.altar.GUI.ModAltarContainer;
import com.lucca.mohard.help.Methods;
import com.lucca.mohard.setup.init.ModAttributes;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.network.chat.Component;

public class AltarStats extends AbstractWidget {

    private final ResourceLocation ALTAR_GUI = new ResourceLocation("mohard", "textures/gui/altar_gui.png");
    private Player jogadorEntity;
    private Component message;
    private ModAltarContainer container;
    private int icone;
    private AltarScreen screen;

    public AltarStats(Player jogador, ModAltarContainer cont, int i, AltarScreen screen) {
        super(0, 0, 40, 21, Methods.stringToText(""));
        jogadorEntity = jogador;
        message = Methods.stringToText("");
        container = cont;
        icone = i;
        this.screen = screen;
    }
    public void setPosition(int xPos, int yPos){
        x = xPos;
        y = yPos;
    }

    @Override
    public void onClick(double p_230982_1_, double p_230982_3_) {
        if(this.screen.isShowingStatsDescription()){
            if(this.screen.getLastAltarStatButtonPressed() == this){
                this.screen.setShowingStatsDescription(false);
                this.screen.setLastAltarStatButtonPressed(this);
            } else {
                this.screen.setLastAltarStatButtonPressed(this);
            }
        } else {
            this.screen.setShowingStatsDescription(true);
            this.screen.setLastAltarStatButtonPressed(this);
        }
    }

    @Override
    public void renderButton(PoseStack p_230431_1_, int p_230431_2_, int p_230431_3_, float p_230431_4_) {
        Minecraft minecraft = Minecraft.getInstance();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, ALTAR_GUI);

        Font fontrenderer = minecraft.font;
        RenderSystem.clearColor(1.0F, 1.0F, 1.0F, this.alpha);
        int i = this.getYImage(this.isHovered);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        this.blit(p_230431_1_, this.x, this.y, 0, 166 + (i * 21), 40, 21);
        this.blit(p_230431_1_, this.x + 5, this.y + 6,41 + (getIcon(icone, true)*8), (166 + getIcon(icone, false) * 9), 7,8 );
        this.renderBg(p_230431_1_, minecraft, p_230431_2_, p_230431_3_);
        //drawCenteredString(p_230431_1_, fontrenderer, message, this.x + 12, this.y + 6, 4210752);
        //fontrenderer.draw(p_230431_1_, this.message, (float) this.x + 12, (float) this.y + 6, 4210752);
        fontrenderer.draw(p_230431_1_, message.getVisualOrderText(), (float)(this.x + 25 - fontrenderer.width(message.getVisualOrderText()) / 2), (float) this.y + 6, 4210752);

    }

    public void setText(String text){
        message = Methods.stringToText(text);
    }

    protected int getYImage(boolean p_230989_1_) {
        if (!this.active) {
            return 2;
        } else if (p_230989_1_) {
            return 1;
        }
        return 0;
    }

    public int getIcon(int i, boolean x){
        return x ? i % 3 : (int) Math.round(Math.floor(i / 3));
    }

    public Attribute getAttribute(){
        Attribute value = Attributes.MAX_HEALTH;

        switch (this.icone){

            case 0:
                value = Attributes.MAX_HEALTH;
                break;

            case 1:
                value = ModAttributes.PHYSICAL_DAMAGE.get();
                break;

            case 2:
                value = ModAttributes.RAW_ARMOR.get();
                break;

            case 3:
                value = ModAttributes.AGILITY.get();
                break;

            case 4:
                value = ModAttributes.PROJECTILE_DAMAGE.get();
                break;

            case 5:
                value = ModAttributes.ARMOR_PENETRATION.get();
                break;

            case 6:
                value = ModAttributes.INTELLECT;
                break;

            case 7:
                value = ModAttributes.MAGIC_DAMAGE.get();
                break;

        }
        return value;
    }

    public double getStats(){
        return jogadorEntity.getAttributeBaseValue(this.getAttribute());
    }

    @Override
    public void updateNarration(NarrationElementOutput p_169152_) {

    }

    public String getAttributeDescription(int i){
        if(this.getAttribute().equals(Attributes.MAX_HEALTH)){
            return i == 1 ? AttributesScreenText.HEALTH.description : "";
        }
        if(this.getAttribute().equals(ModAttributes.PHYSICAL_DAMAGE.get())){
            return i == 1 ? AttributesScreenText.MELEE_DAMAGE.description : "";
        }
        if(this.getAttribute().equals(ModAttributes.RAW_ARMOR.get())){
            return i == 1 ? AttributesScreenText.RAW_ARMOR.description : "";
        }
        if(this.getAttribute().equals(ModAttributes.AGILITY.get())){
            return i == 1 ? AttributesScreenText.AGILITY.description :
                    i == 2 ? AttributesScreenText.AGILITY.aditionalDescription :
                            AttributesScreenText.AGILITY.aditionalDescription2;
        }
        if(this.getAttribute().equals(ModAttributes.PROJECTILE_DAMAGE.get())){
            return i == 1 ? AttributesScreenText.PROJECTILE_DAMAGE.description : "";
        }
        if(this.getAttribute().equals(ModAttributes.ARMOR_PENETRATION.get())){
            return i == 1 ? AttributesScreenText.ARMOR_PENETRATION.description :
                    i == 2 ? AttributesScreenText.ARMOR_PENETRATION.aditionalDescription
                    : "";
        }
        if(this.getAttribute().equals(ModAttributes.INTELLECT)){
            return i == 1 ? AttributesScreenText.INTELLECT.description :
                    i == 2 ? AttributesScreenText.INTELLECT.aditionalDescription
                            : "";
        }
        if(this.getAttribute().equals(ModAttributes.MAGIC_DAMAGE.get())){
            return i == 1 ? AttributesScreenText.MAGIC_DAMAGE.description : "";
        }

        return i == 1 ? AttributesScreenText.HEALTH.description : "";
    }

    public String getAttributeValue(Player player){
        Attribute att = getAttribute();
        double attValue = player.getAttributeBaseValue(att);
        String positive = attValue > 0 ? "+" : "";
        if(att.equals(ModAttributes.AGILITY.get())){
            return (positive + (attValue * 0.8)+"%");
        }
        if(att.equals(ModAttributes.ARMOR_PENETRATION.get())){
            return (positive + attValue + positive + player.getAttributeBaseValue(att) * 0.8+"%");
        }
        if(att.equals(ModAttributes.INTELLECT)){
            return (positive + player.getAttributeBaseValue(att) * 0.8+"%");
        }

        return "";
    }
}
