package io.alunity.content.questing.hftd;

import io.alunity.content.instances.InstanceConfiguration;
import io.alunity.content.instances.impl.LegacySoloPlayerInstance;
import io.alunity.model.cycleevent.CycleEvent;
import io.alunity.model.cycleevent.CycleEventContainer;
import io.alunity.model.cycleevent.CycleEventHandler;
import io.alunity.model.entity.npc.NPC;
import io.alunity.model.entity.player.Boundary;
import io.alunity.model.entity.player.Player;
import io.alunity.model.entity.player.Position;

public class DagannothMotherInstance extends LegacySoloPlayerInstance {

    private static final int PLAYER_START_X = 2515, PLAYER_START_Y = 4632;
    private static final int DAG_SPAWN_X = 2521, DAG_SPAWN_Y = 4644;

    private final Player player;

    private final boolean questInstance;

    public DagannothMotherInstance(Player player, boolean questInstance) {
        super(InstanceConfiguration.CLOSE_ON_EMPTY_RESPAWN, player, Boundary.DAGANNOTH_MOTHER_HFTD);
        this.player = player;
        this.questInstance = questInstance;
    }

    public void init() {
        add(player);
        player.getPA().movePlayer(PLAYER_START_X, PLAYER_START_Y, getHeight());
        spawnDagannothMother();
    }

    public void spawnDagannothMother() {
        CycleEventHandler.getSingleton().addEvent(player, new CycleEvent() {
            @Override
            public void execute(CycleEventContainer container) {
                NPC dagannothMother = new DagannothMother(new Position(DAG_SPAWN_X, DAG_SPAWN_Y, getHeight()));
                dagannothMother.getBehaviour().setRespawn(!questInstance);
                dagannothMother.getBehaviour().setRespawnWhenPlayerOwned(!questInstance);
                add(dagannothMother);
                container.stop();
            }
        }, 6);
    }

}
