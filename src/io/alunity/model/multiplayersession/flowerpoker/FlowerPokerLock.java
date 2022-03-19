package io.alunity.model.multiplayersession.flowerpoker;

import io.alunity.model.Items;
import io.alunity.model.entity.player.Player;
import io.alunity.model.entity.player.lock.CompleteLock;

public class FlowerPokerLock extends CompleteLock {
    @Override
    public boolean cannotClickItem(Player player, int itemId) {
        if (itemId == Items.MITHRIL_SEEDS)
            return false;
        return true;
    }
}
