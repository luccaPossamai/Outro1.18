package com.lucca.mohard.evolution;

import com.lucca.mohard.help.Methods;
import com.lucca.mohard.itens.essence.EssenceData;
import com.lucca.mohard.itens.essence.EssenceDataHelper;
import com.lucca.mohard.itens.essence.EssenceItem;
import com.lucca.mohard.setup.init.ModAttributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.List;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;

@Mod.EventBusSubscriber
public class ModMobEvolution {


    @SubscribeEvent
    public static void evolucao(EntityJoinWorldEvent event){
        if(event.getEntity() instanceof LivingEntity) {
            LivingEntity entidadeViva = (LivingEntity) event.getEntity();
            if(!(entidadeViva instanceof Player)) {
                if (!hasTag(entidadeViva)) {
                    setNivel(entidadeViva);
                }
            }
        }
    }
    @SubscribeEvent
    public static void playerRespawn(PlayerEvent.PlayerRespawnEvent event){
        Player jogador = event.getPlayer();
        PlayerEvolution.setStats(PlayerEvolution.getItems(jogador), jogador);
        jogador.setHealth(jogador.getMaxHealth());
    }

    @SubscribeEvent
    public static void playerRespawn(PlayerEvent.PlayerLoggedInEvent event){
        Player jogador = event.getPlayer();
        PlayerEvolution.setStats(PlayerEvolution.getItems(jogador), jogador);
    }



    @SubscribeEvent
    public static void dropEssence(LivingDropsEvent event){
        if(event.getEntity() instanceof LivingEntity) {
            LivingEntity entidadeViva = (LivingEntity) event.getEntity();
            Level world = entidadeViva.level;
            EssenceItem essence = EssenceDataHelper.getEssenceItemByEntity(entidadeViva.getType());
            boolean hurtByPlayer = hurtByPlayer(entidadeViva);
            if (essence != null && !entidadeViva.isBaby()){
                essence = essence.getEssence(entidadeViva);

                if(essence.isBossEssence()) {
                    event.getDrops().add(Methods.createEssenceItemEntity(essence, world, entidadeViva.getX(), entidadeViva.getY(), entidadeViva.getZ()));
                } else {
                    TargetingConditions player = TargetingConditions.forCombat().range(10D);
                    AABB bb = new AABB(entidadeViva.getX() - 10, entidadeViva.getY() - 10, entidadeViva.getZ() - 10, entidadeViva.getX() + 10, entidadeViva.getY() + 10, entidadeViva.getZ() + 10);
                    int j = world.getNearbyEntities(Player.class, player, entidadeViva, bb).size();
                    if (j > 0 || hurtByPlayer){
                        int lootingLevel = event.getLootingLevel() + 1;
                        while(lootingLevel > 0){
                            lootingLevel--;
                            double chanceDeDrop = Math.random() * 100;
                            if(chanceDeDrop <= 30){
                                event.getDrops().add(Methods.createEssenceItemEntity(essence, world, entidadeViva.getX(), entidadeViva.getY(), entidadeViva.getZ()));
                            }
                        }

                    }
                }

            }

        }

    }






    private static void setNivel(LivingEntity entidadeViva){
        double x = entidadeViva.getX();
        double z = entidadeViva.getZ();
        double dano;
        EssenceData essenceData = EssenceDataHelper.getEssenceDataByEssence(EssenceDataHelper.getEssenceItemByEntity(entidadeViva.getType()));
        double vida = entidadeViva.getAttributeBaseValue(Attributes.MAX_HEALTH);
        double armadura = entidadeViva.getAttributeBaseValue(ModAttributes.RAW_ARMOR.get());
        double agilidade = entidadeViva.getAttributeBaseValue(ModAttributes.AGILITY.get());
        double intelecto = entidadeViva.getAttributeBaseValue(ModAttributes.INTELLECT);
        double armorPen = entidadeViva.getAttributeBaseValue(ModAttributes.ARMOR_PENETRATION.get());
        double danoP = entidadeViva.getAttributeBaseValue(ModAttributes.PROJECTILE_DAMAGE.get());
        double danoM = entidadeViva.getAttributeBaseValue(ModAttributes.MAGIC_DAMAGE.get());
        double nivel = GameLevel.getLevel(x, z, entidadeViva.level);

        double velocidade = entidadeViva.getAttributeBaseValue(Attributes.MOVEMENT_SPEED);

        if(armadura == 0) armadura = entidadeViva.getAttributeBaseValue(Attributes.ARMOR);

        if(essenceData != null){
            vida = vida + essenceData.getStats(0,0).get(0);
            armadura = armadura + essenceData.getStats(0,0).get(2);
            agilidade = agilidade + essenceData.getStats(0,0).get(3);
            armorPen = armorPen + essenceData.getStats(0,0).get(5);
            danoP = danoP + essenceData.getStats(0,0).get(4);
            danoM = danoM + essenceData.getStats(0,0).get(7);
        }


        armadura = armadura * nivel + nivel / 2;
        vida = vida + (vida * (nivel / 100)) + 2 * nivel;
        agilidade = agilidade + (nivel / 600);
        armorPen = armorPen + (nivel / 10);
        danoP = danoP + (nivel / 2) + (danoP * (nivel / 50));
        danoM = danoM + (nivel / 2) + (danoM * (nivel / 50));



        entidadeViva.getAttribute(ModAttributes.RAW_ARMOR.get()).setBaseValue(armadura);
        entidadeViva.getAttribute(ModAttributes.PROJECTILE_DAMAGE.get()).setBaseValue(danoP);
        entidadeViva.getAttribute(ModAttributes.MAGIC_DAMAGE.get()).setBaseValue(danoM);
        entidadeViva.getAttribute(ModAttributes.ARMOR_PENETRATION.get()).setBaseValue(armorPen);
        entidadeViva.getAttribute(Attributes.MAX_HEALTH).setBaseValue(vida);
        entidadeViva.getAttribute(ModAttributes.INTELLECT).setBaseValue(intelecto);
        entidadeViva.getAttribute(ModAttributes.AGILITY.get()).setBaseValue(agilidade);
        entidadeViva.setHealth(entidadeViva.getMaxHealth());

        entidadeViva.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(velocidade + (velocidade * agilidade / 100));

        if(entidadeViva.getAttribute(ModAttributes.PHYSICAL_DAMAGE.get()) != null) {
            dano = entidadeViva.getAttributeBaseValue(ModAttributes.PHYSICAL_DAMAGE.get());
            if(dano == 0) dano = entidadeViva.getAttributeBaseValue(Attributes.ATTACK_DAMAGE);
            if(essenceData != null) dano = dano + essenceData.getStats(0,0).get(1);
            entidadeViva.getAttribute(ModAttributes.PHYSICAL_DAMAGE.get()).setBaseValue(dano + nivel / 2  + (dano * (nivel / 50)));
        }

    }

    private static boolean hasTag(LivingEntity livingEntity){
        if(livingEntity.getTags().contains("Evolved")){
            return true;
        } else {
            livingEntity.getTags().add("Evolved");
            return false;
        }
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class BusEvents{
        @SubscribeEvent
        public static void addAttributes(EntityAttributeModificationEvent event){
            for(EntityType<? extends LivingEntity> entityType : event.getTypes()) {
                for(int i = 0; i < getAttributes().size(); i++) {

                    if(!event.has(entityType, getAttributes().get(i))){
                        event.add(entityType, getAttributes().get(i), getAttributesValues().get(i));
                    }
                }
            }
        }
        private static List<Attribute> getAttributes(){
            return Arrays.asList(Attributes.MAX_HEALTH,
                    ModAttributes.PHYSICAL_DAMAGE.get(),
                    ModAttributes.AGILITY.get(),
                    ModAttributes.INTELLECT,
                    ModAttributes.ARMOR_PENETRATION.get(),
                    ModAttributes.MAGIC_DAMAGE.get(),
                    ModAttributes.RAW_ARMOR.get(),
                    ModAttributes.PROJECTILE_DAMAGE.get(),
                    ModAttributes.MAGIC_RESISTANCE);
        }
        private static List<Double> getAttributesValues(){
            return Arrays.asList(20.0,
                    1.0,
                    0.0,
                    0.0,
                    0.0,
                    1.0,
                    0.0,
                    1.0,
                    0.0);
        }

    }

    private static boolean hurtByPlayer(LivingEntity entidadeViva){
        return (entidadeViva.getKillCredit() instanceof Player);
    }




}
