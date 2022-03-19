package io.alunity.content.commands.all;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;
import io.alunity.model.entity.player.PlayerHandler;

public class Staff extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        List<Player> staff = PlayerHandler.getPlayers().stream().filter(player2 -> player2 != null && player2.getRights().hasStaffPosition())
                .collect(Collectors.toList());
        if (staff.isEmpty()) {
            player.sendMessage("No staff online.");
        } else {
            player.getPA().openQuestInterface("Staff Online", staff.stream()
                    .map(Player::getDisplayNameFormatted)
                    .collect(Collectors.toList()));
        }
    }

    public Optional<String> getDescription() {
        return Optional.of("Show online staff members.");
    }
}
