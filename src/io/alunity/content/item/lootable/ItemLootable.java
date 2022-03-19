package io.alunity.content.item.lootable;

import com.google.common.base.Preconditions;
import io.alunity.model.entity.player.Player;
import io.alunity.model.items.GameItem;
import io.alunity.util.Misc;

public abstract class ItemLootable implements Lootable {

    public abstract int getLootableItem();

    public abstract int getRollCount();

    @Override
    public void roll(Player player) {
        Preconditions.checkState(getLoot().get(LootRarity.RARE) == null);
        Preconditions.checkState(getLoot().get(LootRarity.UNCOMMON) == null);
        Preconditions.checkState(getLoot().get(LootRarity.COMMON) != null);
        Preconditions.checkState(!getLoot().get(LootRarity.COMMON).isEmpty());

        if (player.getItems().playerHasItem(getLootableItem())) {
            player.getItems().deleteItem(getLootableItem(), 1);
            for (int roll = 0; roll < getRollCount(); roll++) {
                GameItem reward = Misc.getRandomItem(getLoot().get(LootRarity.COMMON));
                player.getItems().addItemUnderAnyCircumstance(reward.getId(), reward.getAmount());
            }
        }
    }
}
