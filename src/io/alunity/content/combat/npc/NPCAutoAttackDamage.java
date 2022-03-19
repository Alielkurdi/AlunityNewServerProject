package io.alunity.content.combat.npc;

import io.alunity.model.entity.Entity;
import io.alunity.model.entity.npc.NPC;

public class NPCAutoAttackDamage {

    private final NPC npc;
    private final Entity entity;
    private final int damage;

    public NPCAutoAttackDamage(NPC npc, Entity entity, int damage) {
        this.npc = npc;
        this.entity = entity;
        this.damage = damage;
    }

    public NPC getNpc() {
        return npc;
    }

    public Entity getEntity() {
        return entity;
    }

    public int getDamage() {
        return damage;
    }
}
