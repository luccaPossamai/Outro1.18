package com.lucca.mohard.block.essenceExchanger;

import com.lucca.mohard.effects.corruption.CorruptionMechanics;
import com.lucca.mohard.itens.essence.EssenceItem;
import com.lucca.mohard.setup.init.ModBlockStateProperties;
import com.lucca.mohard.setup.init.ModTileEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;


public class EssenceExchangerTileEntity extends BaseContainerBlockEntity{

    private NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);
    public static final int BINDS_COUNT = 3;
    public static final int TIME_TO_EXCHANGE = 600; // 30min
    private int BINDS_LEFT;
    int TIME = 0;
    EntityType entityType;




    public EssenceExchangerTileEntity(BlockPos p_155077_, BlockState p_155078_) {
        super(ModTileEntityTypes.ESSENCE_EXCHANGER.get(), p_155077_, p_155078_);
        this.BINDS_LEFT = p_155078_.getValue(ModBlockStateProperties.ESSENCE_EXCHANGER_CHARGES);

    }
    protected final ContainerData dataAccess = new ContainerData() {
        public int get(int p_59038_) {
            switch(p_59038_) {
                case 0:
                    return EssenceExchangerTileEntity.this.TIME;
                case 1:
                    EssenceExchangerTileEntity.this.BINDS_LEFT = EssenceExchangerTileEntity.this.getBlockState().getValue(ModBlockStateProperties.ESSENCE_EXCHANGER_CHARGES);
                    return EssenceExchangerTileEntity.this.BINDS_LEFT;
                case 2:
                    return EssenceExchangerTileEntity.this.isActive();
                default:
                    return 0;
            }
        }

        public void set(int p_59040_, int p_59041_) {
            switch(p_59040_) {
                case 0:
                    EssenceExchangerTileEntity.this.TIME = p_59041_;
                    break;
                case 1:
                    //nao
                case 2:
                    //nao tem nada pra ver aqui
            }

        }

        public int getCount() {
            return 3;
        }
    };

    @Override
    protected Component getDefaultName() {
        return new TranslatableComponent("essence_exchanger");
    }

    @Override
    protected AbstractContainerMenu createMenu(int p_58627_, Inventory p_58628_) {
        return new EssenceExchangerContainer(p_58627_, p_58628_, this, this.dataAccess);
    }

    @Override
    public int getContainerSize() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public ItemStack getItem(int p_18941_) {
        return items.get(p_18941_);
    }

    @Override
    public ItemStack removeItem(int p_18942_, int p_18943_) {
        return ContainerHelper.removeItem(this.items, p_18942_, p_18943_);
    }


    @Override
    public ItemStack removeItemNoUpdate(int p_18951_) {
        return ContainerHelper.takeItem(this.items, p_18951_);
    }

    @Override
    public void setItem(int p_18944_, ItemStack p_18945_) {
        if (p_18944_ >= 0 && p_18944_ < this.items.size()) {
            this.items.set(p_18944_, p_18945_);
        }
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public boolean stillValid(Player p_18946_) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return !(p_18946_.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) > 64.0D);
        }
    }


    @Override
    public void clearContent() {
        this.items.clear();;
    }



    @Override
    public void load(CompoundTag p_155297_) {
        super.load(p_155297_);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(p_155297_, this.items);
        this.TIME = p_155297_.getInt("TimeLeft");
    }



    @Override
    public CompoundTag save(CompoundTag p_59012_) {
        super.save(p_59012_);
        ContainerHelper.saveAllItems(p_59012_, this.items);
        p_59012_.putInt("TimeLeft", this.TIME);
        return p_59012_;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, EssenceExchangerTileEntity essenceExchangerTileEntity) {

        ItemStack item = essenceExchangerTileEntity.getItem(0);
        EssenceItem essenceItem = isItemValid(item);
        if (essenceItem != null) {
            essenceExchangerTileEntity.entityType = essenceItem.getType(null);
        }

        if (essenceExchangerTileEntity.isActive() == 1) {
            essenceExchangerTileEntity.TIME++;
        } else {
            if((essenceExchangerTileEntity.getItem(0) == ItemStack.EMPTY ||
                    essenceExchangerTileEntity.dataAccess.get(1) <= 0) &&
                    essenceExchangerTileEntity.TIME > 0) {
                essenceExchangerTileEntity.TIME--;
            }
        }
        if(essenceExchangerTileEntity.TIME >= essenceExchangerTileEntity.getTimeToExchange() && essenceExchangerTileEntity.entityType != null){
            Entity entity = CorruptionMechanics.convertible(essenceExchangerTileEntity.entityType) ?
                    CorruptionMechanics.getCorruptedVariant(essenceExchangerTileEntity.entityType).create(level)
                    :
                    essenceExchangerTileEntity.entityType.create(level);

            entity.setPos(pos.getX(), pos.getY() + 1, pos.getZ());
            level.addFreshEntity(entity);
            EssenceExchangerBlock.uncharge(level, pos, state);
            essenceExchangerTileEntity.TIME = 0;
            essenceExchangerTileEntity.removeItem(0, 1);
        }

    }

    @Nullable
    private static EssenceItem isItemValid(ItemStack itemStack){
        return (itemStack != ItemStack.EMPTY && itemStack.getItem() instanceof EssenceItem) ?
                (EssenceItem) itemStack.getItem() :
                null;
    }

    private boolean hasBinds(){

        return this.dataAccess.get(1) > 0;
    }

    public int getBindsCount(){
        return this.dataAccess.get(1);
    }

    public int getTime(){

        return this.TIME;
    }


    public int getTimeToExchange(){
        return this.TIME_TO_EXCHANGE;
    }

    public int isActive(){
        return (this.getTime() >= 0 && this.getTime() <= this.getTimeToExchange()) && (isItemValid(this.getItem(0)) != null && this.hasBinds()) ? 1 : 0;
    }

}
