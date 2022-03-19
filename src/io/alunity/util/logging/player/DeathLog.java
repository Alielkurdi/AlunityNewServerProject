package io.alunity.util.logging.player;

import io.alunity.model.entity.player.Player;
import io.alunity.util.logging.PlayerLog;

import java.util.Set;

public class DeathLog extends PlayerLog {

    private final Player player;

    public DeathLog(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public Set<String> getLogFileNames() {
        return Set.of("death");
    }

    @Override
    public String getLoggedMessage() {
        return player.getPosition().toString();
    }
}
