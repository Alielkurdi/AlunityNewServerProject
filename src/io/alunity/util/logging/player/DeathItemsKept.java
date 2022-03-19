package io.alunity.util.logging.player;

import io.alunity.model.entity.player.Player;
import io.alunity.model.entity.player.Position;
import io.alunity.model.items.GameItem;
import io.alunity.util.Misc;
import io.alunity.util.logging.PlayerLog;

import java.util.List;
import java.util.Set;

public class DeathItemsKept extends PlayerLog {

    private final List<GameItem> kept;
    private final Position position;


    public DeathItemsKept(Player player, List<GameItem> kept) {
        super(player);
        this.kept = kept;
        this.position = player.getPosition();
    }

    @Override
    public Set<String> getLogFileNames() {
        return Set.of("unsafe_death");
    }

    @Override
    public String getLoggedMessage() {
        return Misc.replaceBracketsWithArguments("{} kept {}", position, kept);
    }
}
