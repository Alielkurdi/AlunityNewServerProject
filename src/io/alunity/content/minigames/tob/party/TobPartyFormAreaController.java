package io.alunity.content.minigames.tob.party;

import io.alunity.content.minigames.tob.TobConstants;
import io.alunity.content.party.PartyFormAreaController;
import io.alunity.content.party.PlayerParty;
import io.alunity.model.entity.player.Boundary;

import java.util.Set;

public class TobPartyFormAreaController extends PartyFormAreaController {

    @Override
    public String getKey() {
        return TobParty.TYPE;
    }

    @Override
    public Set<Boundary> getBoundaries() {
        return Set.of(TobConstants.TOB_LOBBY);
    }

    @Override
    public PlayerParty createParty() {
        return new TobParty();
    }
}
