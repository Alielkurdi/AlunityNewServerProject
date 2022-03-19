package io.alunity.content.combat.effects.damageeffect;

import io.alunity.model.entity.Entity;
import io.alunity.model.entity.player.Player;

public interface DamageBoostingEffect extends DamageEffect {

    double getMaxHitBoost(Player attacker, Entity defender);

}
