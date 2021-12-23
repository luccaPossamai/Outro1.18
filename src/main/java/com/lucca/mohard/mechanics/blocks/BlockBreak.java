package com.lucca.mohard.mechanics.blocks;

import com.lucca.mohard.setup.init.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class BlockBreak {

    public static List<BlockPos> protectedBlocks = new ArrayList<>();

    @SubscribeEvent
    public static void onTryToHarvestBlock(PlayerEvent.BreakSpeed event){
        if(isProtected(event.getPos())){
            ParticleUtils.spawnParticlesOnBlockFaces(event.getPlayer().level, event.getPos(), ModParticles.BLOCK_OFF_SIDE_PARTICLE.get(), UniformInt.of(3, 5));
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onTryToBreakBlock(BlockEvent.BreakEvent event){
        if(event.getPlayer().isCreative()) return;

        if(isProtected(event.getPos())){
            ParticleUtils.spawnParticlesOnBlockFaces(event.getPlayer().level, event.getPos(), ModParticles.BLOCK_OFF_SIDE_PARTICLE.get(), UniformInt.of(3, 5));
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onTryToPlaceBlock(BlockEvent.EntityPlaceEvent event){
        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            if(player.isCreative()) return;
        }
        if(isProtected(event.getPos())){
            ParticleUtils.spawnParticlesOnBlockFaces(event.getEntity().level, event.getPos(), ModParticles.BLOCK_OFF_SIDE_PARTICLE.get(), UniformInt.of(3, 5));
            event.getWorld().addFreshEntity(new ItemEntity(event.getEntity().level, event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(event.getPlacedBlock().getBlock(), 1)));
            event.getWorld().removeBlock(event.getPos(), true);
        }
    }

    @SubscribeEvent
    public static void onTryToPlaceDoubleBlock(BlockEvent.EntityMultiPlaceEvent event){
        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            if(player.isCreative()) return;

        }
        if(isProtected(event.getPos())){
            event.getWorld().addFreshEntity(new ItemEntity(event.getEntity().level, event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(event.getPlacedBlock().getBlock(), 1)));
            event.getWorld().removeBlock(event.getPos(), true);
        }
    }


    private static boolean isProtected(BlockPos pos){
        return protectedBlocks.contains(pos);

    }

}
