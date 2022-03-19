package io.alunity.util.logging.player;

import java.util.List;
import java.util.Set;

import io.alunity.model.entity.player.Player;
import io.alunity.model.items.GameItem;
import io.alunity.util.logging.PlayerLog;

public class ItemStakeLog extends PlayerLog {

    private final String tradedWith;
    private final List<GameItem> received;
    private final List<GameItem> given;

    public ItemStakeLog(Player player, String tradedWith, List<GameItem> received, List<GameItem> given) {
        super(player);
        this.tradedWith = tradedWith;
        this.received = received;
        this.given = given;
    }

    @Override
    public Set<String> getLogFileNames() {
        return Set.of("items_received_stake", "items_received");
    }

    @Override
    public String getLoggedMessage() {
        return String.format("Staked with [%s] Received [%s] Given [%s]", tradedWith, received, given);
    }
}
