package io.alunity.content.combat.effects.damageeffect.impl.bolts;

import io.alunity.content.combat.Damage;
import io.alunity.content.combat.effects.damageeffect.DamageBoostingEffect;
import io.alunity.content.combat.range.RangeData;
import io.alunity.model.Items;
import io.alunity.model.entity.Entity;
import io.alunity.model.entity.npc.NPC;
import io.alunity.model.entity.player.Player;
import io.alunity.util.Misc;

public class OpalBoltSpecial implements DamageBoostingEffect {

    @Override
    public void execute(Player attacker, Player defender, Damage damage) {
        int change = Misc.random((int) (damage.getAmount() * 1.25));
        damage.setAmount(change);
        RangeData.createCombatGraphic(defender, 749, false);
    }

    @Override
    public void execute(Player attacker, NPC defender, Damage damage) {
        if (defender.getDefinition().getName() == null) {
            return;
        }
        RangeData.createCombatGraphic(defender, 749, false);
    }

    @Override
    public boolean isExecutable(Player operator) {
        return RangeData.boltSpecialAvailable(operator, Items.OPAL_BOLTS_E, Items.OPAL_DRAGON_BOLTS_E);
    }

    @Override
    public double getMaxHitBoost(Player attacker, Entity defender) {
        return 0.25;
    }

}
