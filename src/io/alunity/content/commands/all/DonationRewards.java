package io.alunity.content.commands.all;

import java.util.Optional;

import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;

public class DonationRewards extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        player.getDonationRewards().openInterface();
    }

    public Optional<String> getDescription() {
        return Optional.of("Opens the Donation rewards interface.");
    }
}
