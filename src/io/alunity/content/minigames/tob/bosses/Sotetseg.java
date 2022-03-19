package io.alunity.content.minigames.tob.bosses;

import com.google.common.collect.Lists;
import io.alunity.content.combat.npc.NPCAutoAttack;
import io.alunity.content.combat.npc.NPCAutoAttackBuilder;
import io.alunity.content.instances.InstancedArea;
import io.alunity.content.minigames.tob.TobBoss;
import io.alunity.model.Animation;
import io.alunity.model.CombatType;
import io.alunity.model.Npcs;
import io.alunity.model.ProjectileBaseBuilder;
import io.alunity.model.entity.player.Position;

public class Sotetseg extends TobBoss {

    private final Animation ATTACK_ANIMATION = new Animation(8139);

    public Sotetseg(InstancedArea instancedArea) {
        super(Npcs.SOTETSEG_2, new Position(3278, 4326, instancedArea.getHeight()), instancedArea);

        setNpcAutoAttacks(Lists.newArrayList(
                new NPCAutoAttackBuilder()
                        .setAnimation(ATTACK_ANIMATION)
                        .setCombatType(CombatType.MELEE)
                        .setHitDelay(1)
                        .setMaxHit(45)
                        .setAttackDelay(5)
                        .createNPCAutoAttack(),
                getProjectileAttack(CombatType.MAGE),
                getProjectileAttack(CombatType.RANGE)
        ));
    }


    private NPCAutoAttack getProjectileAttack(CombatType type) {
        return new NPCAutoAttackBuilder()
                .setMultiAttack(true)
                .setSelectPlayersForMultiAttack(npcCombatAttack -> getInstance().getPlayers())
                .setAnimation(ATTACK_ANIMATION)
                .setCombatType(type)
                .setHitDelay(4)
                .setMaxHit(45)
                .setAttackDelay(5)
                .setDistanceRequiredForAttack(25)
                .setProjectile(new ProjectileBaseBuilder()
                        .setProjectileId(type == CombatType.MAGE ? 1606 : 1607)
                        .setSendDelay(2)
                        .setCurve(0)
                        .setStartHeight(43)
                        .setEndHeight(43)
                .createProjectileBase())
                .createNPCAutoAttack();
    }

    @Override
    public int getDeathAnimation() {
        return 8140;
    }
}
