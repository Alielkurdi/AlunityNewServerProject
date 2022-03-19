package io.alunity;

import io.alunity.annotate.Init;
import io.alunity.annotate.PostInit;
import io.alunity.content.boosts.Boosts;
import io.alunity.content.bosses.godwars.GodwarsEquipment;
import io.alunity.content.bosses.godwars.GodwarsNPCs;
import io.alunity.content.bosses.nightmare.NightmareStatusNPC;
import io.alunity.content.bosses.sarachnis.SarachnisNpc;
import io.alunity.content.collection_log.CollectionLog;
import io.alunity.content.combat.stats.TrackedMonster;
import io.alunity.content.commands.CommandManager;
import io.alunity.content.dailyrewards.DailyRewardContainer;
import io.alunity.content.dailyrewards.DailyRewardsRecords;
import io.alunity.content.donationrewards.DonationReward;
import io.alunity.content.event.eventcalendar.EventCalendar;
import io.alunity.content.event.eventcalendar.EventCalendarWinnerSelect;
import io.alunity.content.events.monsterhunt.MonsterHunt;
import io.alunity.content.fireofexchange.FireOfExchangeBurnPrice;
import io.alunity.content.polls.PollTab;
import io.alunity.content.preset.PresetManager;
import io.alunity.content.referral.ReferralCode;
import io.alunity.content.skills.runecrafting.ouriana.ZamorakGuardian;
import io.alunity.content.tournaments.TourneyManager;
import io.alunity.content.tradingpost.Listing;
import io.alunity.content.trails.TreasureTrailsRewards;
import io.alunity.content.vote_panel.VotePanelManager;
import io.alunity.content.wogw.Wogw;
import io.alunity.content.worldevent.WorldEventContainer;
import io.alunity.model.Npcs;
import io.alunity.model.collisionmap.ObjectDef;
import io.alunity.model.collisionmap.Region;
import io.alunity.model.collisionmap.doors.DoorDefinition;
import io.alunity.model.cycleevent.impl.BonusApplianceEvent;
import io.alunity.model.cycleevent.impl.DidYouKnowEvent;
import io.alunity.model.cycleevent.impl.LeaderboardUpdateEvent;
import io.alunity.model.cycleevent.impl.UpdateQuestTab;
import io.alunity.model.definitions.AnimationLength;
import io.alunity.model.definitions.ItemDef;
import io.alunity.model.definitions.ItemStats;
import io.alunity.model.definitions.NpcDef;
import io.alunity.model.definitions.NpcStats;
import io.alunity.model.definitions.ShopDef;
import io.alunity.model.entity.npc.NPCRelationship;
import io.alunity.model.entity.npc.NpcSpawnLoader;
import io.alunity.model.entity.npc.stats.NpcCombatDefinition;
import io.alunity.model.entity.player.PlayerFactory;
import io.alunity.model.entity.player.save.PlayerSave;
import io.alunity.model.lobby.LobbyManager;
import io.alunity.model.world.ShopHandler;
import io.alunity.punishments.PunishmentCycleEvent;
import io.alunity.model.entity.player.save.backup.PlayerSaveBackup;
import io.alunity.util.Reflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stuff to do on startup.
 * @author Michael Sasse (https://github.com/mikeysasse/)
 */
public class ServerStartup {

    private static final Logger logger = LoggerFactory.getLogger(ServerStartup.class);

    static void load() throws Exception {
        Reflection.getMethodsAnnotatedWith(Init.class).forEach(method -> {
            try {
                method.invoke(null);
            } catch (Exception e) {
                logger.error("Error loading @Init annotated method[{}] inside class[{}]", method, method.getClass(), e);
                e.printStackTrace();
                System.exit(1);
            }
        });

        DonationReward.load();
        PlayerSave.loadPlayerSaveEntries();
        EventCalendarWinnerSelect.getInstance().init();
        TrackedMonster.init();
        Boosts.init();
        ItemDef.load();
        ShopDef.load();
        ShopHandler.load();
        NpcStats.load();
        ItemStats.load();
        NpcDef.load();
        // Npc Combat Definition must be above npc load
        NpcCombatDefinition.load();
        Server.npcHandler.init();
        NPCRelationship.setup();
        EventCalendar.verifyCalendar();
        Server.getPunishments().initialize();
        Server.getEventHandler().submit(new DidYouKnowEvent());
        Server.getEventHandler().submit(new BonusApplianceEvent());
        Server.getEventHandler().submit(new PunishmentCycleEvent(Server.getPunishments(), 50));
        Server.getEventHandler().submit(new UpdateQuestTab());
        Server.getEventHandler().submit(new LeaderboardUpdateEvent());
        Listing.init();
        Wogw.init();
        PollTab.init();
        DoorDefinition.load();
        GodwarsEquipment.load();
        GodwarsNPCs.load();
        LobbyManager.initializeLobbies();
        VotePanelManager.init();
        TourneyManager.initialiseSingleton();
        TourneyManager.getSingleton().init();
        Server.getDropManager().read();
        TreasureTrailsRewards.load();
        AnimationLength.startup();
        PresetManager.getSingleton().init();
        ObjectDef.loadConfig();
        CollectionLog.init();
        Region.load();
        Server.getGlobalObjects().loadGlobalObjectFile();

        // Keep this below region load and object loading
        NpcSpawnLoader.load();
        MonsterHunt.spawnNPC();
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());
        CommandManager.initializeCommands();
        NightmareStatusNPC.init();
        if (Server.isDebug()) {
            PlayerFactory.createTestPlayers();
        }
        ReferralCode.load();
        DailyRewardContainer.load();
        DailyRewardsRecords.load();
        WorldEventContainer.getInstance().initialise();
        FireOfExchangeBurnPrice.init();
        Server.getLogging().schedule();

        ZamorakGuardian.spawn();
        new SarachnisNpc(Npcs.SARACHNIS, SarachnisNpc.SPAWN_POSITION);

        if (Server.isPublic()) {
            PlayerSaveBackup.start(Configuration.PLAYER_SAVE_TIMER_MILLIS, Configuration.PLAYER_SAVE_BACKUP_EVERY_X_SAVE_TICKS);
        }

        Reflection.getMethodsAnnotatedWith(PostInit.class).forEach(method -> {
            try {
                method.invoke(null);
            } catch (Exception e) {
                logger.error("Error loading @PostInit annotated method[{}] inside class[{}]", method, method.getClass(), e);
                e.printStackTrace();
                System.exit(1);
            }
        });
    }

}
