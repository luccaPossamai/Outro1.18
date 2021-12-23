package com.lucca.mohard.gui.altar;

import com.lucca.mohard.block.altar.GUI.ModAltarContainer;
import com.lucca.mohard.setup.init.ModAttributes;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AltarScreen extends AbstractContainerScreen<ModAltarContainer> {

    private static final ResourceLocation CONTAINER_LOCATION = new ResourceLocation("mohard", "textures/gui/altar_gui.png");
    private Inventory playerInventory;
    private ModAltarContainer container;
    private List<Widget> buttons = new ArrayList<>();
    private boolean showingStatsDescription = false;
    private AltarStats lastAltarStatButtonPressed;

    public AltarScreen(ModAltarContainer p_i51093_1_, Inventory p_i51093_2_, Component p_i51093_3_) {
        super(p_i51093_1_, p_i51093_2_, p_i51093_3_);
        playerInventory = p_i51093_2_;
        container = p_i51093_1_;
    }

    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
        initButton();

    }

    public void render(PoseStack p_230430_1_, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
        this.renderBackground(p_230430_1_);
        super.render(p_230430_1_, p_230430_2_, p_230430_3_, p_230430_4_);
        this.renderTooltip(p_230430_1_, p_230430_2_, p_230430_3_);
    }

    protected void renderBg(PoseStack p_230450_1_, float p_230450_2_, int p_230450_3_, int p_230450_4_) {
        RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, CONTAINER_LOCATION);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        this.blit(p_230450_1_, i, j, 0, 0, this.imageWidth, this.imageHeight);

        if(this.showingStatsDescription){
            initStatsShow(p_230450_1_, i, j);
        }
    }

    public void initButton(){
        AltarButton button = new AltarButton(this, playerInventory.player);
        button.setPosition(((this.width - this.imageWidth) / 2) + 5, ((this.height - this.imageHeight) / 2 ) + 5);
        this.addRenderableWidget(button);
    }

    public void initStatsShow(PoseStack pose, int i, int j){
        this.blit(pose, i + this.imageWidth, j, 176, 1, 80, 162);
        Component name = new TranslatableComponent(this.lastAltarStatButtonPressed.getAttribute().getDescriptionId());
        Font fontrenderer = minecraft.font;
        String[] names = name.getString().split(" ");
        fontrenderer.draw(pose, names[0], i + this.imageWidth + 40 - (font.width(names[0])) / 2, j + 6, 4210752);
        if(names.length > 1){
            fontrenderer.draw(pose, names[1], i + this.imageWidth + 40 - (font.width(names[1])) / 2, j + 14, 4210752);
        }

        String attDescription = this.lastAltarStatButtonPressed.getAttributeDescription(1);
        String aditionalDescription = "";

        if(this.lastAltarStatButtonPressed.getAttributeDescription(2).length() > 0){
            aditionalDescription = aditionalDescription +" "+this.lastAltarStatButtonPressed.getAttributeDescription(2);
            aditionalDescription = aditionalDescription + this.lastAltarStatButtonPressed.getAttributeValue(minecraft.player);
        }
        if(this.lastAltarStatButtonPressed.getAttributeDescription(3).length() > 0){
            double agilityValue = minecraft.player.getAttributeBaseValue(ModAttributes.AGILITY.get());
            String positive = agilityValue > 0 ? "+" : "";
            aditionalDescription = aditionalDescription +" "+ this.lastAltarStatButtonPressed.getAttributeDescription(3);
            aditionalDescription = aditionalDescription + positive +agilityValue+"%";
        }


        formatDescription(pose, attDescription, i, j, 0);

        formatDescription(pose, aditionalDescription, i, j, 7 + 5);

    }

    public void formatDescription(PoseStack pose, String attDescription, int i, int j, int startHeight){
        String[] phraseWords = attDescription.split(" ");
        int lenght = phraseWords.length;
        int width = 0;
        int height = startHeight;
        int count = 0;
        while(count < lenght){

            int xPos = i + this.imageWidth + 4 + width + (2 * (count == 0 ? 0 : 1));
            if(xPos + font.width(phraseWords[count]) > i + this.imageWidth + 76){
                width = 0;
                height++;
                xPos = i + this.imageWidth + 4;
            }
            font.draw(pose, phraseWords[count], xPos, j + 25 + (8 * height), 4210752);

            width = width + font.width(phraseWords[count]);
            count++;

        }
    }

    public void initButtonStats(){
        for(int i = 0; i < 8; i++) {
            AltarStats button = new AltarStats(playerInventory.player, this.container, i, this);
            button.setPosition(((this.width - this.imageWidth) / 2) - 40, ((this.height - this.imageHeight) / 2 ) + 1 + (i * 20));
            double stat = button.getStats();
            button.setText(""+(int)stat);
            this.addRenderableWidget(button);
            this.buttons.add(button);
        }
    }
    public List<Widget> getButtons(){
        return this.buttons;
    }

    public void removeButtons(Collection<AbstractWidget> botoes){
        for(AbstractWidget widget : botoes) {
            if (widget instanceof Widget) {
                this.renderables.remove((Widget) widget);
            }

            this.children().remove(widget);
        }
    }
    public boolean isShowingStatsDescription(){
        return this.showingStatsDescription;
    }

    public AltarStats getLastAltarStatButtonPressed() {
        return lastAltarStatButtonPressed;
    }

    public void setShowingStatsDescription(boolean bool){
        this.showingStatsDescription = bool;
    }

    public void setLastAltarStatButtonPressed(AltarStats statsButton){
        this.lastAltarStatButtonPressed = statsButton;
    }
}
//165