package io.alunity.content.itemskeptondeath;

import io.alunity.model.entity.player.Player;
import io.alunity.model.items.GameItem;

import java.util.List;
import java.util.Set;

public class DeathItemModifierTestImpl implements DeathItemModifier {

    @Override
    public Set<Integer> getItemIds() {
        return Set.of(1);
    }

    @Override
    public void modify(Player player, GameItem gameItem, boolean kept, List<GameItem> keptItems, List<GameItem> lostItems) {
        lostItems.removeIf(it -> it.getId() == gameItem.getId());
        lostItems.add(new GameItem(2));
    }
}