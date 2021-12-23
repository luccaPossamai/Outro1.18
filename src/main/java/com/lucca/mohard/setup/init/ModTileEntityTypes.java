package com.lucca.mohard.setup.init;

import com.lucca.mohard.block.altar.AltarTileEntity;
import com.lucca.mohard.block.essenceExchanger.EssenceExchangerTileEntity;
import com.lucca.mohard.block.protector.ProtectorTileEntity;
import com.lucca.mohard.setup.Registration;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;


public class ModTileEntityTypes {

    public static final RegistryObject<BlockEntityType<AltarTileEntity>> ALTAR = Registration.TILE_ENTIDADES.register("altar", () ->
            BlockEntityType.Builder.of(AltarTileEntity::new, ModBlocks.ALTAR_BLOCK).build(null)
    );

    public static final RegistryObject<BlockEntityType<EssenceExchangerTileEntity>> ESSENCE_EXCHANGER = Registration.TILE_ENTIDADES.register("essence_exchanger", () ->
            BlockEntityType.Builder.of(EssenceExchangerTileEntity::new, ModBlocks.ESSENCE_EXHANGER_BLOCK).build(null)
    );

    public static final RegistryObject<BlockEntityType<ProtectorTileEntity>> PROTECTOR = Registration.TILE_ENTIDADES.register("protector", () ->
            BlockEntityType.Builder.of(ProtectorTileEntity::new, ModBlocks.COPPERED_DEEPSLATE_BLOCK).build(null)
    );




    public static void register(){}


}
