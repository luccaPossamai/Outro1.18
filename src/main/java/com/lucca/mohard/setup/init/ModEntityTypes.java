package com.lucca.mohard.setup.init;

import com.lucca.mohard.entities.bargainers.BargainersEntity;
import com.lucca.mohard.entities.bargainers.especific.*;
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
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;

public class ModEntityTypes {

    public static void register(){}

    public static final EntityType<DiamondArmoredPillager> DIAMOND_ARMORED_PILLAGER_ENTITY_TYPE = EntityType.Builder.<DiamondArmoredPillager>of(
            DiamondArmoredPillager::new,
            MobCategory.CREATURE)
            .canSpawnFarFromPlayer()
            .sized(0.711F, 2.31075F)
            .clientTrackingRange(8).build("diamond_armored_pillager");

    public static final EntityType<GoldenArmoredPillager> GOLDEN_ARMORED_PILLAGER_ENTITY_TYPE = EntityType.Builder.<GoldenArmoredPillager>of(
            GoldenArmoredPillager::new,
            MobCategory.CREATURE)
            .canSpawnFarFromPlayer()
            .sized(0.6375F, 2.071875F)
            .clientTrackingRange(8).build("golden_armored_pillager");

    public static final EntityType<DiamondArmoredVindicator> DIAMOND_ARMORED_VINDICATOR_ENTITY_TYPE = EntityType.Builder.<DiamondArmoredVindicator>of(
            DiamondArmoredVindicator::new,
            MobCategory.CREATURE)
            .canSpawnFarFromPlayer()
            .sized(0.711F, 2.31075F)
            .clientTrackingRange(8).build("diamond_armored_vindicator");

    public static final EntityType<IceIsolator> ICE_ISOLATOR_ENTITY_TYPE = EntityType.Builder.<IceIsolator>of(
            IceIsolator::new,
            MobCategory.CREATURE)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 1.95F)
            .clientTrackingRange(8).build("ice_isolator");

    public static final EntityType<GoldenArmoredVindicator> GOLDEN_ARMORED_VINDICATOR_ENTITY_TYPE = EntityType.Builder.<GoldenArmoredVindicator>of(
            GoldenArmoredVindicator::new,
            MobCategory.CREATURE)
            .canSpawnFarFromPlayer()
            .sized(0.6375F, 2.071875F)
            .clientTrackingRange(8).build("golden_armored_vindicator");

    public static final EntityType<EnchanterIllager> ENCHANTER_ILLAGER_ENTITY_TYPE = EntityType.Builder.<EnchanterIllager>of(
            EnchanterIllager::new,
            MobCategory.CREATURE)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 1.95F)
            .clientTrackingRange(8).build("enchanter");

    public static final EntityType<MooerEntity> MOOER_ENTITY_TYPE = EntityType.Builder.<MooerEntity>of(
            MooerEntity::new,
            MobCategory.CREATURE)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 1.95F)
            .clientTrackingRange(8).build("mooer");

    public static final EntityType<MooerEntity> GOLDEN_MOOER_ENTITY_TYPE = EntityType.Builder.<MooerEntity>of(
            MooerEntity::new,
            MobCategory.CREATURE)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 1.95F)
            .clientTrackingRange(8).build("gilded_mooer");

    public static final EntityType<MooerEntity> MOOSHROOM_MOOER_ENTITY_TYPE = EntityType.Builder.<MooerEntity>of(
            MooerEntity::new,
            MobCategory.CREATURE)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 1.95F)
            .clientTrackingRange(8).build("mooshroom_mooer");


    public static final EntityType<NomadEntity> NOMAD_ENTITY_TYPE = EntityType.Builder.<NomadEntity>of(
            NomadEntity::new,
            MobCategory.MISC)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 1.95F)
            .clientTrackingRange(8).build("spiritual_nomad");

    public static final EntityType<BargainersEntity> GASPI_ENTITY_TYPE = EntityType.Builder.<BargainersEntity>of(
            GASPI::new,
            MobCategory.MISC)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 1.85F)
            .clientTrackingRange(8).build("gaspi_bargainer");

    public static final EntityType<BargainersEntity> CUSPILE_ENTITY_TYPE = EntityType.Builder.<BargainersEntity>of(
            CUSPILE::new,
            MobCategory.MISC)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 2.05F)
            .clientTrackingRange(8).build("cuspile_bargainer");

    public static final EntityType<BargainersEntity> TUSJUS_ENTITY_TYPE = EntityType.Builder.<BargainersEntity>of(
            TUSJUS::new,
            MobCategory.MISC)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 1.95F)
            .clientTrackingRange(8).build("tusjus_bargainer");

    public static final EntityType<BargainersEntity> OEL_ENTITY_TYPE = EntityType.Builder.<BargainersEntity>of(
            OEL::new,
            MobCategory.MISC)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 1.95F)
            .clientTrackingRange(8).build("oel_bargainer");

    public static final EntityType<BargainersEntity> NIIPPA_ENTITY_TYPE = EntityType.Builder.<BargainersEntity>of(
            NIIPPA::new,
            MobCategory.MISC)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 1.95F)
            .clientTrackingRange(8).build("nippa_bargainer");

    public static final EntityType<BargainersEntity> SAATPON_ENTITY_TYPE = EntityType.Builder.<BargainersEntity>of(
            SAATPON::new,
            MobCategory.MISC)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 1.85F)
            .clientTrackingRange(8).build("saatpon_bargainer");

    public static final EntityType<BargainersEntity> PABITTAS_ENTITY_TYPE = EntityType.Builder.<BargainersEntity>of(
            PABITTAS::new,
            MobCategory.MISC)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 2.05F)
            .clientTrackingRange(8).build("pabittas_bargainer");

    public static final EntityType<BargainersEntity> NOJAS_ENTITY_TYPE = EntityType.Builder.<BargainersEntity>of(
            NOJAS::new,
            MobCategory.MISC)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 1.85F)
            .clientTrackingRange(8).build("nojas_bargainer");

    public static final EntityType<BargainersEntity> AAN_ENTITY_TYPE = EntityType.Builder.<BargainersEntity>of(
            AAN::new,
            MobCategory.MISC)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 1.85F)
            .clientTrackingRange(8).build("aan_bargainer");

    public static final EntityType<BargainersEntity> HOCINZAL_ENTITY_TYPE = EntityType.Builder.<BargainersEntity>of(
            HO_CINZAL::new,
            MobCategory.MISC)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 2.15F)
            .clientTrackingRange(8).build("ho_cinzal_bargainer");

    public static final EntityType<BargainersEntity> CINVET_ENTITY_TYPE = EntityType.Builder.<BargainersEntity>of(
            CINVET::new,
            MobCategory.MISC)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 2.05F)
            .clientTrackingRange(8).build("cinvet_bargainer");

    public static final EntityType<BargainersEntity> TASBEGO_ENTITY_TYPE = EntityType.Builder.<BargainersEntity>of(
            TASBEGO::new,
            MobCategory.MISC)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 1.85F)
            .clientTrackingRange(8).build("tasbego_bargainer");

    public static final EntityType<Creepy> CREEPY_ENTITY_TYPE = EntityType.Builder.<Creepy>of(
            Creepy::new,
            MobCategory.MONSTER)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 1.05F)
            .clientTrackingRange(8).build("creepy");

    public static final EntityType<Skelly> SKELLY_ENTITY_TYPE = EntityType.Builder.<Skelly>of(
            Skelly::new,
            MobCategory.MONSTER)
            .canSpawnFarFromPlayer()
            .sized(0.7F, 2.4F)
            .clientTrackingRange(8).build("skelly");

    public static final EntityType<Zomby> ZOMBY_ENTITY_TYPE = EntityType.Builder.<Zomby>of(
            Zomby::new,
            MobCategory.MONSTER)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 1.95F)
            .clientTrackingRange(8).build("zomby");

    public static final EntityType<CorruptedPlayer> CORRUPTED_PLAYER_ENTITY_TYPE = EntityType.Builder.<CorruptedPlayer>of(
            CorruptedPlayer::new,
            MobCategory.MONSTER)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 1.95F)
            .clientTrackingRange(8).build("corrupted_player");

    public static final EntityType<Spidey> SPIDEY_ENTITY_TYPE = EntityType.Builder.<Spidey>of(
            Spidey::new,
            MobCategory.MONSTER)
            .canSpawnFarFromPlayer()
            .sized(0.3F, 0.1F)
            .clientTrackingRange(8).build("spidey");

    public static final EntityType<Corrupter> CORRUPTER_ENTITY_TYPE = EntityType.Builder.<Corrupter>of(
            Corrupter::new,
            MobCategory.CREATURE)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 1.05F)
            .clientTrackingRange(8).build("corrupter");

    public static final EntityType<Thox> THOX_ENTITY_TYPE = EntityType.Builder.<Thox>of(
            Thox::new,
            MobCategory.CREATURE)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 1.05F)
            .clientTrackingRange(8).build("thox");

    public static final EntityType<DummyEntity> DUMMY_ENTITY_TYPE = EntityType.Builder.<DummyEntity>of(
            DummyEntity::new,
            MobCategory.MISC)
            .canSpawnFarFromPlayer()
            .sized(0.6F, 1.95F)
            .clientTrackingRange(8).build("dummy");


    public static final EntityType<ThrownAlgidAxe> ALGID_AXE = EntityType.Builder.<ThrownAlgidAxe>of(
            ThrownAlgidAxe::new,
            MobCategory.MISC)
            .sized(0.5F, 0.5F)
            .clientTrackingRange(4)
            .updateInterval(20).build("algid_axe");




}
