package io.alunity.content.questing.MonkeyMadness;

import io.alunity.model.entity.npc.NPC;
import io.alunity.model.entity.player.Position;

public class MMDemon extends NPC {

    public MMDemon(Position position) {
        super(1443, position);
        getBehaviour().setAggressive(true);
    }
}
