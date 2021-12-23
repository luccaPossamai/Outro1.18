package com.lucca.mohard.evolution;

import com.lucca.mohard.capabilities.ModCapabilities;
import com.lucca.mohard.enchantments.essences.EssenceEnchantment;
import com.lucca.mohard.itens.essence.EssenceDataHelper;
import com.lucca.mohard.itens.essence.essenceHabilities.EssenceHabilities;
import com.lucca.mohard.itens.essence.EssenceItem;
import com.lucca.mohard.itens.essence.essenceHabilities.GenericEssenceHability;
import com.lucca.mohard.itens.essence.essenceSyntonizer.EssenceSyntonizerTier;
import com.lucca.mohard.setup.init.ModAttributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.*;

@Mod.EventBusSubscriber
public class PlayerEvolution {


    private static Map<Player, EssenceHabilities> playerHability = new HashMap<>();
    public static Map<Player, GenericEssenceHability> onEffect = new HashMap<>();
    public static Map<Player, EssenceSyntonizerTier> lastSyntonizerTierUsed = new HashMap<>();
    private static Map<Player, NonNullList<ItemStack>> insideItems = new HashMap<>();

    public static void setStats(NonNullList<ItemStack> lista,
                                Player jogador){

        updateInsideItems(lista, jogador);
        updateHability(lista, jogador);

        double vida = 20;
        double danoF = 1;
        double danoR = 1;
        double danoM = 1;
        double armor = 0;
        double agilidade = 0;
        double pen = 0;
        double intellect = 0;
        double velocidade = 0.10000000149011612;

        for(ItemStack item : lista){
            if(item.getItem() instanceof EssenceItem){
                EssenceItem essence = (EssenceItem) item.getItem();
                int upgradeLevel = EssenceDataHelper.getEssenceLevel(item);
                int negativeUpgradeLevel = EssenceDataHelper.getEssenceNegativeLevel(item);
                List<Double> valores = EssenceDataHelper.getEssenceDataByEssence(essence).getStats(upgradeLevel, negativeUpgradeLevel);
                for(int i = 0; i < valores.size(); i++){
                    double valor = valores.get(i);
                    double valorNum = valor;
                    switch (i){
                        case 0:
                            vida = vida + valorNum;

                            break;
                        case 1:
                            danoF = danoF + valorNum;

                            break;
                        case 2:
                            armor = armor + valorNum;
                            break;

                        case 3:
                            agilidade = agilidade + valorNum;

                            break;
                        case 4:
                            danoR = danoR + valorNum;

                            break;
                        case 5:
                            pen = pen + valorNum;

                            break;
                        case 6:
                            intellect = intellect + valorNum;


                            break;
                        case 7:
                            danoM = danoM + valorNum;

                            break;

                    }

                }
                if(vida >= 1024){
                    vida = 1024;
                }
                if(danoF >= 2048){
                    danoF = 2048;
                }
                if(danoR >= 2048){
                    danoR = 2048;
                }
                if(armor >= 2048){
                    armor = 2048;
                }
                if(danoM >= 2048){
                    danoM = 2048;
                }
                if(intellect <= -100){
                    intellect = -100;
                }
                if(intellect >= 100){
                    intellect = 100;
                }
                if(agilidade <= -100){
                    agilidade = -100;
                }
                if(agilidade >= 100){
                    agilidade = 100;
                }
                if(pen >= 100){
                    pen = 100;
                }

            }
        }

    /*
        jogador.sendMessage(Methods.stringToText(jogador.getMaxHealth()+""), jogador.getUUID());
        jogador.sendMessage(Methods.stringToText(jogador.getAttributeValue(ModAttributes.PHYSICAL_DAMAGE.get())+""), jogador.getUUID());
        jogador.sendMessage(Methods.stringToText(jogador.getAttributeValue(ModAttributes.RAW_ARMOR.get())+""), jogador.getUUID());
        jogador.sendMessage(Methods.stringToText(jogador.getAttributeValue(ModAttributes.AGILITY.get())+""), jogador.getUUID());
        jogador.sendMessage(Methods.stringToText(jogador.getAttributeValue(ModAttributes.ARMOR_PENETRATION.get())+""), jogador.getUUID());
        jogador.sendMessage(Methods.stringToText(jogador.getAttributeValue(ModAttributes.MAGIC_DAMAGE.get())+""), jogador.getUUID());
        jogador.sendMessage(Methods.stringToText(jogador.getAttributeValue(ModAttributes.PROJECTILE_DAMAGE.get())+""), jogador.getUUID());
        jogador.sendMessage(Methods.stringToText(jogador.getAttributeValue(ModAttributes.INTELLECT.get())+""), jogador.getUUID());



     */

        if(!onEffect.containsKey(jogador)) {

            jogador.getAttribute(Attributes.MAX_HEALTH).setBaseValue(vida);
            jogador.getAttribute(ModAttributes.PHYSICAL_DAMAGE.get()).setBaseValue(danoF);
            jogador.getAttribute(ModAttributes.RAW_ARMOR.get()).setBaseValue(armor);
            jogador.getAttribute(ModAttributes.AGILITY.get()).setBaseValue(agilidade);

            jogador.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(velocidade + (velocidade * agilidade / 125));

            jogador.getAttribute(ModAttributes.ARMOR_PENETRATION.get()).setBaseValue(pen);
            jogador.getAttribute(ModAttributes.MAGIC_DAMAGE.get()).setBaseValue(danoM);
            jogador.getAttribute(ModAttributes.PROJECTILE_DAMAGE.get()).setBaseValue(danoR);
            jogador.getAttribute(ModAttributes.INTELLECT).setBaseValue(intellect);
        }

        GameLevel.updateserverLevel(jogador.level);

    }


    @Nullable
    public static EssenceHabilities getPlayerHability(Player jogador){
        if(playerHability.containsKey(jogador)){
            return playerHability.get(jogador);
        }
        return null;
    }

    private static void updateHability(NonNullList<ItemStack> lista, Player jogador) {
        EssenceEnchantment essenceEnchantment = getHability(lista);
        if(essenceEnchantment != null){
            if(playerHability.containsKey(jogador)){
                if(!playerHability.get(jogador).equals(essenceEnchantment)){
                    playerHability.remove(jogador);
                }
            }
            playerHability.put(jogador, essenceEnchantment.getEssenceHability());
        }
    }

    @Nullable
    private static EssenceEnchantment getHability(NonNullList<ItemStack> lista){
        for(ItemStack itemStack : lista){
            if(itemStack.isEnchanted()){
                Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(itemStack);
                for (Enchantment enchantment : map.keySet()) {
                    if( enchantment instanceof EssenceEnchantment){
                        return (EssenceEnchantment) enchantment;
                    }
                    continue;
                }
            }
        }
        return null;
    }


    private static boolean updateInsideItems(NonNullList<ItemStack> lista, Player jogador) {
        if(insideItems.containsKey(jogador)){
            if(insideItems.get(jogador).equals(lista)){
                return false;
            } else {
                insideItems.remove(jogador);
            }
        }
        insideItems.put(jogador, lista);
        return true;
    }


    public static NonNullList<ItemStack> getItems(Player jogador){
        NonNullList<ItemStack> items = NonNullList.withSize(9, ItemStack.EMPTY);
        jogador.getCapability(ModCapabilities.ALTAR_CAPABILITY).ifPresent(inv->{
            for(int i = 0; i < 9 ; i++){
                items.set(i, inv.getStackInSlot(i));
            }
        });
        return items;
    }


    public static NonNullList<ItemStack> getInsideItems(Player playerEntity) {
        return insideItems.containsKey(playerEntity) ? insideItems.get(playerEntity) :
                NonNullList.withSize(9, ItemStack.EMPTY);
    }

    public static double magicImunne(LivingEntity playerEntity){
        double count = 0;
        if(playerEntity.getAttribute(ModAttributes.MAGIC_RESISTANCE) != null){
            count = playerEntity.getAttributeValue(ModAttributes.MAGIC_RESISTANCE);
        }
        return count / 10;
    }

    public static void tickingPlayersHability(){
        for(Player player : onEffect.keySet()){
            onEffect.get(player).tick();
        }
    }

    public static void playerHit(Entity entity, LivingEntity entityHurted){
        Player player = null;
        if(entity instanceof Projectile){
            Projectile proj = (Projectile) entity;
            if(proj.getOwner() instanceof Player){
                player = (Player) proj.getOwner();
            }
        } else if(entity instanceof Player){
            player = (Player) entity;
        }
        if(player != null){
            if(onEffect.containsKey(player)) {
                onEffect.get(player).onHit(entityHurted);
            }
        }

    }

    @SubscribeEvent
    public static void tick(TickEvent event){
        if(event.type.equals(TickEvent.Type.SERVER)){
            tickingPlayersHability();
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void hurt(LivingHurtEvent event){
        if(event.getSource() != null){
            if(event.getSource().getEntity() != null){
                playerHit(event.getSource().getEntity(), event.getEntityLiving());
            }
        }
    }
}
