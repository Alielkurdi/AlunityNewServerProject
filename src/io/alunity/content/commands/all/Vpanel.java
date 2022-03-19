package io.alunity.content.commands.all;

import java.util.Optional;

import io.alunity.content.commands.Command;
import io.alunity.content.vote_panel.VotePanelInterface;
import io.alunity.model.entity.player.Player;


public class Vpanel extends Command {

    @Override
    public void execute(Player c, String commandName, String input) {
        VotePanelInterface.openInterface(c, true);
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.of("Opens your vote panel.");
    }

}
