package io.alunity.content.item.lootable;

import java.util.List;
import java.util.Map;

import io.alunity.model.entity.player.Player;
import io.alunity.model.items.GameItem;

public interface Lootable {

    Map<LootRarity, List<GameItem>> getLoot();

    void roll(Player player);

}
