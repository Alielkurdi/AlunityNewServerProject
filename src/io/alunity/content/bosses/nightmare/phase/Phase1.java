package io.alunity.content.bosses.nightmare.phase;

import io.alunity.content.bosses.nightmare.Nightmare;
import io.alunity.content.bosses.nightmare.NightmareAttack;
import io.alunity.content.bosses.nightmare.NightmarePhase;
import io.alunity.content.bosses.nightmare.NightmareStatus;
import io.alunity.content.bosses.nightmare.attack.FlowerPower;
import io.alunity.content.bosses.nightmare.attack.GraspingClaws;
import io.alunity.content.bosses.nightmare.attack.Husks;

public class Phase1 implements NightmarePhase {


    @Override
    public void start(Nightmare nightmare) {

    }

    @Override
    public NightmareStatus getStatus() {
        return NightmareStatus.PHASE_1;
    }

    @Override
    public NightmareAttack[] getAttacks() {
        return new NightmareAttack[] { new GraspingClaws(), new Husks(), new FlowerPower() };
    }
}
