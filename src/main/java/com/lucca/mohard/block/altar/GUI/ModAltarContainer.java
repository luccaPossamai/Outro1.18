package com.lucca.mohard.block.altar.GUI;

import com.lucca.mohard.block.altar.capability.AltarCapability;
import com.lucca.mohard.evolution.PlayerEvolution;
import com.lucca.mohard.gui.EssenceSlot;
import com.lucca.mohard.itens.essence.EssenceItem;
import com.lucca.mohard.setup.init.ModContainers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.core.NonNullList;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ModAltarContainer extends AbstractContainerMenu {

    private MenuType menuType;
    private AltarCapability altarCapability;
    private NonNullList<Slot> altarSlots = NonNullList.withSize(45, new Slot(new SimpleContainer(45), 0, 0, 0));



    public ModAltarContainer(int i, Inventory playerInventory) {
        this(ModContainers.ALTAR_TYPE.get(), i, playerInventory, new AltarCapability());
    }



    public ModAltarContainer(@Nullable MenuType<?> containerType,
                             int containerId,
                             Inventory invPlayer,
                             AltarCapability inventory) {

        super(containerType, containerId);

        altarCapability = inventory;
        altarSlots.clear();
        menuType = this.getType();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                Slot slot = new SlotItemHandler(inventory, j + i * 3, 62 + j * 18, 17 + i * 18);

                //Slot slot = new Slot(altar, j + i * 3, 62 + j * 18, 17 + i * 18);
                altarSlots.set(j + i * 3, slot);
                this.addSlot(slot);
            }
        }


        for (int k = 0; k < 3; ++k) {
            for (int i1 = 0; i1 < 9; ++i1) {
                Slot slot = new Slot(invPlayer, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18);
                altarSlots.set(i1 + k * 9 + 9, slot);
                this.addSlot(slot);
            }
        }

        for (int l = 0; l < 9; ++l) {
            Slot slot = new Slot(invPlayer, l, 8 + l * 18, 142);
            altarSlots.set(36 + l, slot);
            this.addSlot(slot);
        }
    }






    @Override
    public boolean stillValid(Player jogador) {
        return true;
    }



    @SubscribeEvent
    public static void saveAltarItems(PlayerContainerEvent.Close event){
        Player jogador = event.getPlayer();
        if(event.getContainer() instanceof ModAltarContainer) {
            NonNullList<ItemStack> lista = NonNullList.withSize(9, ItemStack.EMPTY);
            for(int i = 0; i < 9; i++){
                lista.set(i, event.getContainer().slots.get(i).getItem());
            }

            PlayerEvolution.setStats(lista, jogador);
        }
    }

    public NonNullList<ItemStack> getItems(Player playerEntity) {
        NonNullList<ItemStack> lista = NonNullList.withSize(9, ItemStack.EMPTY);
        for(int i = 0; i < 9; i++){
            lista.set(i, this.slots.get(i).getItem());
        }
        return lista;
    }

    @Override
    public void clicked(int slot, int slotMouse, ClickType click, Player player) {
        if(!(this.getCarried().getItem() instanceof EssenceItem) &&
                !(this.getCarried().getItem().equals(Items.AIR))){
            if(slot < 9){
                return;
            }
        }
        super.clicked(slot, slotMouse, click, player);

    }




    @Override
    public ItemStack quickMoveStack(Player p_82846_1_, int p_82846_2_) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(p_82846_2_);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if(EssenceSlot.mayPlaceItem(itemstack)) {
                if (p_82846_2_ < 9) {
                    if (!this.moveItemStackTo(itemstack1, 9, this.slots.size(), true)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    if (!this.moveItemStackTo(itemstack1, 0, 9, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(p_82846_1_, itemstack1);
        }


        return itemstack;
    }





}
