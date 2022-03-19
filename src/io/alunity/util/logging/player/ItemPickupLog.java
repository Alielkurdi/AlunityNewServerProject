package io.alunity.util.logging.player;

import java.util.Set;

import io.alunity.model.entity.player.Player;
import io.alunity.model.entity.player.Position;
import io.alunity.model.items.GameItem;
import io.alunity.util.logging.PlayerLog;

public class ItemPickupLog extends PlayerLog {

    private final GameItem gameItem;
    private final Position position;
    private final String droppedBy;

    public ItemPickupLog(Player player, GameItem gameItem, Position position, String droppedBy) {
        super(player);
        this.gameItem = gameItem;
        this.position = position;
        this.droppedBy = droppedBy;
    }

    @Override
    public Set<String> getLogFileNames() {
        return Set.of("items_received_pickup", "items_received");
    }

    @Override
    public String getLoggedMessage() {
        return String.format("Picked up %s at %s, owner %s", gameItem, position, droppedBy);
    }
}
