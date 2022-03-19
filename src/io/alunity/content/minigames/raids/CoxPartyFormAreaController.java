package io.alunity.content.minigames.raids;

import io.alunity.content.party.PartyFormAreaController;
import io.alunity.content.party.PlayerParty;
import io.alunity.model.entity.player.Boundary;

import java.util.Set;

public class CoxPartyFormAreaController extends PartyFormAreaController {

    @Override
    public String getKey() {
        return CoxParty.TYPE;
    }

    @Override
    public Set<Boundary> getBoundaries() {
        return Set.of(Boundary.RAIDS_LOBBY_ENTRANCE);
    }

    @Override
    public PlayerParty createParty() {
        return new CoxParty();
    }
}
