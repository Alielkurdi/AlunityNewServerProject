package io.alunity.content.itemskeptondeath.modifiers;

import io.alunity.content.itemskeptondeath.DeathItemModifier;
import io.alunity.model.Items;
import io.alunity.model.entity.player.Player;
import io.alunity.model.items.GameItem;

import java.util.List;
import java.util.Set;

public class ZamorakianHastaDeathItem implements DeathItemModifier {
    @Override
    public Set<Integer> getItemIds() {
        return Set.of(Items.ZAMORAKIAN_HASTA);
    }

    @Override
    public void modify(Player player, GameItem gameItem, boolean kept, List<GameItem> keptItems, List<GameItem> lostItems) {
        if (kept)
            return;
        lostItems.remove(gameItem);
        lostItems.add(new GameItem(Items.ZAMORAKIAN_SPEAR));
    }
}
