package io.alunity.model.entity.npc.autoattacks;

import io.alunity.content.combat.npc.NPCAutoAttackBuilder;
import io.alunity.model.Animation;
import io.alunity.model.CombatType;
import io.alunity.model.Graphic;
import io.alunity.model.ProjectileBaseBuilder;

public class RangedShootArrow extends NPCAutoAttackBuilder {

    public RangedShootArrow(int maxHit) {
        setAttackDelay(3);
        setMaxHit(maxHit);
        setAnimation(new Animation(426));
        setCombatType(CombatType.RANGE);
        setDistanceRequiredForAttack(10);
        setHitDelay(3);
        setStartGraphic(new Graphic(19, Graphic.GraphicHeight.MIDDLE));
        setProjectile(new ProjectileBaseBuilder().setSendDelay(2).setProjectileId(11).createProjectileBase());
    }
}
