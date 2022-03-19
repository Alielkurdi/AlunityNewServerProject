package io.alunity.content.itemskeptondeath.modifiers;

import io.alunity.content.combat.range.RangeData;
import io.alunity.content.itemskeptondeath.DeathItemModifier;
import io.alunity.model.Items;
import io.alunity.model.entity.player.Player;
import io.alunity.model.items.GameItem;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CrystalWeaponDeathItem implements DeathItemModifier {

    private static final Set<Integer> ALL;

    static {
        ALL = new HashSet<>();
        Arrays.stream(RangeData.CRYSTAL_BOWS).forEach(ALL::add);
        ALL.add(Items.CRYSTAL_HALBERD);

        // Crystal shield is not related here, it's tradable in the server
        //ALL.add(Items.NEW_CRYSTAL_SHIELD);
    }

    @Override
    public Set<Integer> getItemIds() {
        return ALL;
    }

    @Override
    public void modify(Player player, GameItem gameItem, boolean kept, List<GameItem> keptItems, List<GameItem> lostItems) {
        if (kept)
            return;

        if (Arrays.stream(RangeData.CRYSTAL_BOWS).anyMatch(it -> it == gameItem.getId())) {
            player.crystalBowArrowCount = 0;
        }

        lostItems.remove(gameItem);
        lostItems.add(new GameItem(Items.CRYSTAL_WEAPON_SEED));
    }
}
