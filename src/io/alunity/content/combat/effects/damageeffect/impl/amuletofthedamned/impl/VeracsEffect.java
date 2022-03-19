package io.alunity.content.combat.effects.damageeffect.impl.amuletofthedamned.impl;

import io.alunity.content.combat.Damage;
import io.alunity.content.combat.effects.damageeffect.impl.amuletofthedamned.AmuletOfTheDamnedEffect;
import io.alunity.model.entity.Entity;
import io.alunity.model.entity.player.Player;
import io.alunity.model.items.EquipmentSet;

/**
 * @author Arthur Behesnilian 1:10 PM
 */
public class VeracsEffect implements AmuletOfTheDamnedEffect {

    /**
     * The singleton instance of the Amulet of the damned effect for Veracs
     */
    public static final AmuletOfTheDamnedEffect INSTANCE = new VeracsEffect();

    @Override
    public boolean hasExtraRequirement(Player player) {
        return EquipmentSet.VERAC.isWearingBarrows(player);
    }

    @Override
    public void useEffect(Player player, Entity other, Damage damage) {
        // Effects happen inline elsewhere
    }
}
