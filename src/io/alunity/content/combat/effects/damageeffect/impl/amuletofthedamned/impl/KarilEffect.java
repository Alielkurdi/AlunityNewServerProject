package io.alunity.content.combat.effects.damageeffect.impl.amuletofthedamned.impl;

import io.alunity.content.combat.Damage;
import io.alunity.content.combat.Hitmark;
import io.alunity.content.combat.effects.damageeffect.impl.amuletofthedamned.AmuletOfTheDamnedEffect;
import io.alunity.content.items.Degrade;
import io.alunity.model.CombatType;
import io.alunity.model.entity.Entity;
import io.alunity.model.entity.player.Player;
import io.alunity.model.items.EquipmentSet;
import io.alunity.util.Misc;

/**
 * @author Arthur Behesnilian 12:55 PM
 */
public class KarilEffect implements AmuletOfTheDamnedEffect {

    /**
     * The singleton instance of the Amulet of the damned effect for Karils
     */
    public static AmuletOfTheDamnedEffect INSTANCE = new KarilEffect();

    @Override
    public boolean hasExtraRequirement(Player player) {
        return EquipmentSet.KARIL.isWearingBarrows(player)
                && Misc.isLucky(25);
    }

    @Override
    public void useEffect(Player player, Entity other, Damage damage) {
        if (damage.getAmount() < 2) {
            return;
        }

        int halfDamage = (int) Math.ceil((double) damage.getAmount() / 2);
        Damage secondDamage = new Damage(other, halfDamage, player.hitDelay, player.playerEquipment, Hitmark.HIT, CombatType.RANGE);
        player.getDamageQueue().add(secondDamage);

        Degrade.degrade(player, Degrade.DegradableItem.AMULETS_OF_THE_DAMNED);

        /*if(Server.isDebug()) {
            player.debug("Karil's Effect with AOD: Appended Damage:" + secondDamage);
        }*/
    }
}
