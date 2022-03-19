package io.alunity.content.bosses.bryophyta;

import io.alunity.model.entity.Entity;
import io.alunity.model.entity.npc.NPC;
import io.alunity.model.entity.player.Player;
import io.alunity.model.entity.player.Position;

public class Growthling extends NPC {

    public Growthling(int npcId, Position position, Player spawnedBy) {
        super(npcId, position);
        this.spawnedBy = spawnedBy.getIndex();
    }

    @Override
    public boolean canBeAttacked(Entity entity) {
        if (this.spawnedBy != entity.getIndex()) {
            if (entity instanceof Player) {
                Player p = (Player) entity;
                if (p != null)
                    p.sendMessage(this.getName()+" isn't after you.");
            }
            return false;
        }
        return true;
    }
}
