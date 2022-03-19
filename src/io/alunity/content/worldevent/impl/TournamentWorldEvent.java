package io.alunity.content.worldevent.impl;

import java.util.List;

import io.alunity.content.commands.Command;
import io.alunity.content.commands.all.Outlast;
import io.alunity.content.tournaments.TourneyManager;
import io.alunity.content.worldevent.WorldEvent;
import io.alunity.model.entity.player.Player;
import io.alunity.model.entity.player.Position;
import io.alunity.model.entity.player.broadcasts.Broadcast;

public class TournamentWorldEvent implements WorldEvent {

    private final TourneyManager tourney = TourneyManager.getSingleton();

    @Override
    public void init() {
        tourney.openLobby();
    }

    @Override
    public void dispose() {
        tourney.endGame();
    }

    @Override
    public boolean isEventCompleted() {
        return !tourney.isLobbyOpen() && !tourney.isArenaActive();
    }

    @Override
    public String getCurrentStatus() {
        return tourney.getTimeLeft();
    }

    @Override
    public String getEventName() {
        return "Outlast";
    }

    @Override
    public String getStartDescription() {
        return "starts";
    }

    @Override
    public Class<? extends Command> getTeleportCommand() {
        return Outlast.class;
    }

    @Override
    public void announce(List<Player> players) {
        new Broadcast("An Outlast " + tourney.getTournamentType() + " tournament will begin soon, type ::outlast or click HERE to join!").addTeleport(new Position(3080, 3510, 0)).copyMessageToChatbox().submit();
    }
}
