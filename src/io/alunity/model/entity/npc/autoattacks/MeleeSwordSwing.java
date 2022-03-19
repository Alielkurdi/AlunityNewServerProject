package io.alunity.model.entity.npc.autoattacks;

import io.alunity.content.combat.npc.NPCAutoAttackBuilder;
import io.alunity.model.Animation;
import io.alunity.model.CombatType;

public class MeleeSwordSwing extends NPCAutoAttackBuilder {

    public MeleeSwordSwing(int maxHit) {
        setAttackDelay(4);
        setMaxHit(maxHit);
        setAnimation(new Animation(451));
        setCombatType(CombatType.MELEE);
    }
}
