package com.lucca.mohard.setup.init;

import com.lucca.mohard.entities.bargainers.BargainersEntity;
import com.lucca.mohard.entities.corrupter.Corrupter;
import com.lucca.mohard.entities.dummy.DummyEntity;
import com.lucca.mohard.entities.etc.corruptedPlayer.CorruptedPlayer;
import com.lucca.mohard.entities.etc.creepy.Creepy;
import com.lucca.mohard.entities.etc.skelly.Skelly;
import com.lucca.mohard.entities.etc.spidey.Spidey;
import com.lucca.mohard.entities.etc.thox.Thox;
import com.lucca.mohard.entities.etc.zomby.Zomby;
import com.lucca.mohard.entities.illagers.diamondPillager.DiamondArmoredPillager;
import com.lucca.mohard.entities.illagers.diamondVindicator.DiamondArmoredVindicator;
import com.lucca.mohard.entities.illagers.enchanterIllager.EnchanterIllager;
import com.lucca.mohard.entities.illagers.goldenPillager.GoldenArmoredPillager;
import com.lucca.mohard.entities.illagers.goldenVindicator.GoldenArmoredVindicator;
import com.lucca.mohard.entities.illagers.iceIsolator.IceIsolator;
import com.lucca.mohard.entities.mooers.MooerEntity;
import com.lucca.mohard.entities.villagers.nomad.NomadEntity;
import com.lucca.mohard.itens.artifacts.algidAxe.ThrownAlgidAxe;
import com.lucca.mohard.setup.Registration;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static void register(){
    }

    public static final RegistryObject<EntityType<GoldenArmoredPillager>> GOLDEN_ARMORED_PILLAGER = Registration.ENTITIES.register("golden_armored_pillager", () ->
            ModEntityTypes.GOLDEN_ARMORED_PILLAGER_ENTITY_TYPE);

    public static final RegistryObject<EntityType<DiamondArmoredPillager>> DIAMOND_ARMORED_PILLAGER = Registration.ENTITIES.register("diamond_armored_pillager", () ->
            ModEntityTypes.DIAMOND_ARMORED_PILLAGER_ENTITY_TYPE);

    public static final RegistryObject<EntityType<GoldenArmoredVindicator>> GOLDEN_ARMORED_VINDICATOR = Registration.ENTITIES.register("golden_armored_vindicator", () ->
            ModEntityTypes.GOLDEN_ARMORED_VINDICATOR_ENTITY_TYPE);

    public static final RegistryObject<EntityType<DiamondArmoredVindicator>> DIAMOND_ARMORED_VINDICATOR = Registration.ENTITIES.register("diamond_armored_vindicator", () ->
            ModEntityTypes.DIAMOND_ARMORED_VINDICATOR_ENTITY_TYPE);

    public static final RegistryObject<EntityType<IceIsolator>> ICE_ISOLATOR = Registration.ENTITIES.register("ice_isolator", () ->
            ModEntityTypes.ICE_ISOLATOR_ENTITY_TYPE);


    public static final RegistryObject<EntityType<EnchanterIllager>> ENCHANTER = Registration.ENTITIES.register("enchanter", () ->
            ModEntityTypes.ENCHANTER_ILLAGER_ENTITY_TYPE);

    public static final RegistryObject<EntityType<MooerEntity>> MOOER = Registration.ENTITIES.register("mooer", () ->
            ModEntityTypes.MOOER_ENTITY_TYPE);

    public static final RegistryObject<EntityType<MooerEntity>> GOLDEN_MOOER = Registration.ENTITIES.register("gilded_mooer", () ->
            ModEntityTypes.GOLDEN_MOOER_ENTITY_TYPE);

    public static final RegistryObject<EntityType<MooerEntity>> MOOSHROOM_MOOER = Registration.ENTITIES.register("mooshroom_mooer", () ->
            ModEntityTypes.MOOSHROOM_MOOER_ENTITY_TYPE);

    public static final RegistryObject<EntityType<NomadEntity>> NOMAD = Registration.ENTITIES.register("spiritual_nomad", () ->
            ModEntityTypes.NOMAD_ENTITY_TYPE);

    public static final RegistryObject<EntityType<BargainersEntity>> GASPI_ENTITY_TYPE = Registration.ENTITIES.register("gaspi_bargainer", () ->
            ModEntityTypes.GASPI_ENTITY_TYPE
            );

    public static final RegistryObject<EntityType<BargainersEntity>> CUSPILE_ENTITY_TYPE = Registration.ENTITIES.register("cuspile_bargainer", () ->
            ModEntityTypes.CUSPILE_ENTITY_TYPE
            );

    public static final RegistryObject<EntityType<BargainersEntity>> TUSJUS_ENTITY_TYPE = Registration.ENTITIES.register("tusjus_bargainer", () ->
            ModEntityTypes.TUSJUS_ENTITY_TYPE
            );

    public static final RegistryObject<EntityType<BargainersEntity>> OEL_ENTITY_TYPE = Registration.ENTITIES.register("oel_bargainer", () ->
            ModEntityTypes.OEL_ENTITY_TYPE
            );

    public static final RegistryObject<EntityType<BargainersEntity>> NIIPPA_ENTITY_TYPE = Registration.ENTITIES.register("nippa_bargainer", () ->
            ModEntityTypes.NIIPPA_ENTITY_TYPE
            );

    public static final RegistryObject<EntityType<BargainersEntity>> SAATPON_ENTITY_TYPE = Registration.ENTITIES.register("saatpon_bargainer", () ->
            ModEntityTypes.SAATPON_ENTITY_TYPE
            );

    public static final RegistryObject<EntityType<BargainersEntity>> PABITTAS_ENTITY_TYPE = Registration.ENTITIES.register("pabittas_bargainer", () ->
            ModEntityTypes.PABITTAS_ENTITY_TYPE
            );

    public static final RegistryObject<EntityType<BargainersEntity>> NOJAS_ENTITY_TYPE = Registration.ENTITIES.register("nojas_bargainer", () ->
            ModEntityTypes.NOJAS_ENTITY_TYPE
            );

    public static final RegistryObject<EntityType<BargainersEntity>> AAN_ENTITY_TYPE = Registration.ENTITIES.register("aan_bargainer", () ->
            ModEntityTypes.AAN_ENTITY_TYPE
            );

    public static final RegistryObject<EntityType<BargainersEntity>> HOCINZAL_ENTITY_TYPE = Registration.ENTITIES.register("ho_cinzal_bargainer", () ->
            ModEntityTypes.HOCINZAL_ENTITY_TYPE
            );

    public static final RegistryObject<EntityType<BargainersEntity>> CINVET_ENTITY_TYPE = Registration.ENTITIES.register("cinvet_bargainer", () ->
            ModEntityTypes.CINVET_ENTITY_TYPE
            );

    public static final RegistryObject<EntityType<BargainersEntity>> TASBEGO_ENTITY_TYPE = Registration.ENTITIES.register("tasbego_bargainer", () ->
            ModEntityTypes.TASBEGO_ENTITY_TYPE
            );

    public static final RegistryObject<EntityType<Creepy>> CREEPY = Registration.ENTITIES.register("creepy", () ->
            ModEntityTypes.CREEPY_ENTITY_TYPE
    );

    public static final RegistryObject<EntityType<Skelly>> SKELLY = Registration.ENTITIES.register("skelly", () ->
            ModEntityTypes.SKELLY_ENTITY_TYPE
    );

    public static final RegistryObject<EntityType<Zomby>> ZOMBY = Registration.ENTITIES.register("zomby", () ->
            ModEntityTypes.ZOMBY_ENTITY_TYPE
    );
    public static final RegistryObject<EntityType<CorruptedPlayer>> CORRUPTED_PLAYER = Registration.ENTITIES.register("corrupted_player", () ->
            ModEntityTypes.CORRUPTED_PLAYER_ENTITY_TYPE
    );

    public static final RegistryObject<EntityType<Spidey>> SPIDEY = Registration.ENTITIES.register("spidey", () ->
            ModEntityTypes.SPIDEY_ENTITY_TYPE
    );

    public static final RegistryObject<EntityType<Corrupter>> CORRUPTER = Registration.ENTITIES.register("corrupter", () ->
            ModEntityTypes.CORRUPTER_ENTITY_TYPE
    );

    public static final RegistryObject<EntityType<Thox>> THOX = Registration.ENTITIES.register("thox", () ->
            ModEntityTypes.THOX_ENTITY_TYPE
    );

    public static final RegistryObject<EntityType<DummyEntity>> DUMMY = Registration.ENTITIES.register("dummy", () ->
            ModEntityTypes.DUMMY_ENTITY_TYPE
    );

    public static final RegistryObject<EntityType<ThrownAlgidAxe>> THROWN_ALGID_AXE = Registration.ENTITIES.register("algid_axe", () ->
            ModEntityTypes.ALGID_AXE
    );




}
