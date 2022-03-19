package io.alunity.content.itemskeptondeath.modifiers;

import io.alunity.content.itemskeptondeath.DeathItemModifier;
import io.alunity.model.Items;
import io.alunity.model.entity.player.Player;
import io.alunity.model.items.GameItem;

import java.util.List;
import java.util.Set;

public class FerociousGlovesDeathItem implements DeathItemModifier {
    @Override
    public Set<Integer> getItemIds() {
        return Set.of(Items.FEROCIOUS_GLOVES);
    }

    @Override
    public void modify(Player player, GameItem gameItem, boolean kept, List<GameItem> keptItems, List<GameItem> lostItems) {
        if (kept)
            return;
        lostItems.remove(gameItem);
        lostItems.add(new GameItem(Items.HYDRA_LEATHER));
    }
}
