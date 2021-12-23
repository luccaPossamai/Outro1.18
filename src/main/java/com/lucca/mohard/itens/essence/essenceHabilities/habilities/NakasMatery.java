package com.lucca.mohard.itens.essence.essenceHabilities.habilities;

import com.lucca.mohard.itens.essence.essenceHabilities.GenericEssenceHability;
import com.lucca.mohard.particles.AttributeBuffParticle;
import com.lucca.mohard.setup.init.ModAttributes;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.function.DoublePredicate;
import java.util.stream.Collectors;

public class NakasMatery extends GenericEssenceHability {

    Map<Attribute, Double> attibuteValues = new HashMap<>();
    List<Attribute> validAttributes = Arrays.asList(
            Attributes.MAX_HEALTH,
            Attributes.MOVEMENT_SPEED,
            ModAttributes.PHYSICAL_DAMAGE.get(),
            ModAttributes.AGILITY.get(),
            ModAttributes.PROJECTILE_DAMAGE.get(),
            ModAttributes.MAGIC_DAMAGE.get(),
            ModAttributes.INTELLECT,
            ModAttributes.RAW_ARMOR.get(),
            ModAttributes.ARMOR_PENETRATION.get());

    public NakasMatery(Player player, int duration) {
        super(player, duration);
    }

    @Override
    public void startEffects() {
        super.startEffects();
        setupBaseAttributesValues();
    }

    @Override
    public void stopEffects() {
        super.stopEffects();
        resetAttributes();
    }

    @Override
    protected void playSound() {

    }

    @Override
    protected void showParticles() {

    }

    @Override
    public void onHit(LivingEntity livingEntity) {
        super.onHit(livingEntity);
        Attribute randomAtt = getRandomAttribute(livingEntity);
        if(livingEntity.getAttribute(randomAtt) != null && livingEntity.getAttributeValue(randomAtt) > 0) {
            double value = livingEntity.getAttributeValue(randomAtt) * 0.05D;
            addAttribute(this.player, randomAtt, value);
            addAttribute(livingEntity, randomAtt, -value);
            ((ServerLevel)player.level).sendParticles(AttributeBuffParticle.getAttributeParticle(randomAtt), livingEntity.getX(), livingEntity.getY(0.5D), livingEntity.getZ(), 1 + (int) value, 0.1D, 0.0D, 0.1D, 0.2D);
        }
    }

    private void addAttribute(LivingEntity entity, Attribute att, double value){
        Objects.requireNonNull(entity.getAttribute(att)).setBaseValue(player.getAttributeValue(att) + value);
    }

    private void resetAttributes(){
        for(Attribute att : this.attibuteValues.keySet()){
            Objects.requireNonNull(this.player.getAttribute(att)).setBaseValue(this.attibuteValues.get(att));
        }
    }

    private void setupBaseAttributesValues(){
        for(Attribute att : this.validAttributes){
            this.attibuteValues.put(att, this.player.getAttributeValue(att));
        }
    }

    private Attribute getRandomAttribute(LivingEntity entity){
        List<Attribute> highestAttributes = getHighestAttributes(entity);

        return highestAttributes.get(new Random().nextInt(highestAttributes.size() - 1));
    }

    private List<Attribute> getHighestAttributes(LivingEntity entity) {
        List<AttributeInstance> attributeInstances = this.validAttributes.stream().map(entity::getAttribute).toList();
        List<AttributeInstance> validAttributes = attributeInstances.stream().filter(attribute -> attribute.getValue() > 0).collect(Collectors.toList());
        for(int i = 0; i < validAttributes.size(); i++){
            AttributeInstance attributeInstance = validAttributes.get(i);
            Attribute att = attributeInstance.getAttribute();
            if(att.equals(ModAttributes.INTELLECT) ||
                    att.equals(ModAttributes.AGILITY.get()) ||
                    att.equals(ModAttributes.ARMOR_PENETRATION.get())){
                attributeInstance.setBaseValue(attributeInstance.getValue() * 20.48F);

            }
            validAttributes.set(i, attributeInstance);
        }

        List<Attribute> attributes = new ArrayList<>();

        List<Double> values = new ArrayList<>();
        validAttributes.stream().mapToDouble(AttributeInstance::getValue).forEach(values::add);
        for(int i = 0; i < 3; i++){
            double max = values.stream().mapToDouble(Double::doubleValue).max().getAsDouble();
            int index = ArrayUtils.indexOf(values.toArray(), max);
            attributes.add(validAttributes.get(index).getAttribute());
            values.remove(index);
        }
        return attributes;
    }
}
