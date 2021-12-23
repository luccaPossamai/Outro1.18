package com.lucca.mohard.setup;

import com.lucca.mohard.ExampleMod;
import com.lucca.mohard.setup.init.*;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class Registration {

    public static final DeferredRegister<Block> BLOCOS = create(ForgeRegistries.BLOCKS);
    public static final DeferredRegister<EntityType<?>> ENTITIES = create(ForgeRegistries.ENTITIES);
    public static final DeferredRegister<Item> ITENS = create(ForgeRegistries.ITEMS);
    public static final DeferredRegister<BlockEntityType<?>> TILE_ENTIDADES = create(ForgeRegistries.BLOCK_ENTITIES);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPIES = create(ForgeRegistries.RECIPE_SERIALIZERS);
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = create(ForgeRegistries.ENCHANTMENTS);
    public static final DeferredRegister<MobEffect> EFFECTS = create(ForgeRegistries.MOB_EFFECTS);
    public static final DeferredRegister<Potion> POTIONS = create(ForgeRegistries.POTIONS);
    public static final DeferredRegister<Attribute> ATTRIBUTES = create(ForgeRegistries.ATTRIBUTES);
    public static final DeferredRegister<MenuType<?>> CONTAINERS = create(ForgeRegistries.CONTAINERS);
    public static final DeferredRegister<ParticleType<?>> PARTICLES = create(ForgeRegistries.PARTICLE_TYPES);

    public static final DeferredRegister<Biome> BIOMES = create(ForgeRegistries.BIOMES);
    public static final DeferredRegister<Feature<?>> FEATURES = create(ForgeRegistries.FEATURES);

    public static final DeferredRegister<MemoryModuleType<?>> MEMORIES_MODULE = create(ForgeRegistries.MEMORY_MODULE_TYPES);
    public static final DeferredRegister<SensorType<?>> SENSORS = create(ForgeRegistries.SENSOR_TYPES);
    public static final DeferredRegister<Activity> ACTIVITIES = create(ForgeRegistries.ACTIVITIES);

    public static final DeferredRegister<PoiType> POINT_OF_INTEREST_TYPE = create(ForgeRegistries.POI_TYPES);
    public static final DeferredRegister<VillagerProfession> PROFESSIONS = create(ForgeRegistries.PROFESSIONS);

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = create(ForgeRegistries.SOUND_EVENTS);



    public static void register(){
        IEventBus fml = FMLJavaModLoadingContext.get().getModEventBus();
        ENTITIES.register(fml);
        BLOCOS.register(fml);
        ITENS.register(fml);
        TILE_ENTIDADES.register(fml);
        RECIPIES.register(fml);
        ENCHANTMENTS.register(fml);
        EFFECTS.register(fml);
        ATTRIBUTES.register(fml);
        CONTAINERS.register(fml);
        PARTICLES.register(fml);
        MEMORIES_MODULE.register(fml);
        SENSORS.register(fml);
        ACTIVITIES.register(fml);
        PROFESSIONS.register(fml);
        POINT_OF_INTEREST_TYPE.register(fml);
        BIOMES.register(fml);
        FEATURES.register(fml);
        SOUND_EVENTS.register(fml);
        POTIONS.register(fml);

        ModEntityTypes.register();
        ModBlockStateProperties.register();


        ModPotions.register();
        ModSounds.register();
        ModModelLayers.register();
        ModFeatures.register();
        ModBiomes.register();
        ModMemoriesModule.register();
        ModActivities.register();
        ModSensors.register();
        ModEntities.register();
        ModParticles.register();
        ModContainers.register();
        ModAttributes.register();
        ModEffects.register();
        ModEnchantments.register();
        ModBlocks.register();
        ModBlocksRegistration.register();
        ModItens.register();
        ModTileEntityTypes.register();
        ModRecipeSerializers.register();
        //ModItens.registerEssence();
        ModEssences.register();
        ModItemGroups.register();
        ModProfessions.register();
        ModPOI.register();
    }

    private static <T extends IForgeRegistryEntry<T>> DeferredRegister<T> create(IForgeRegistry<T> registry){
        return DeferredRegister.create(registry, ExampleMod.MOD_ID);
    }

}
