package io.alunity.content.commands.owner;

import io.alunity.Configuration;
import io.alunity.content.commands.Command;
import io.alunity.content.wogw.Wogw;
import io.alunity.model.entity.player.Player;
import io.alunity.util.Misc;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Doubledrops extends Command {
    @Override
    public void execute(Player player, String commandName, String input) {
        int minutes = Integer.parseInt(input);
        if (minutes == 0) {
            Configuration.DOUBLE_DROPS_TIMER = 0;
            player.sendMessage("Double drops have ended.");
            return;
        }

        Configuration.DOUBLE_DROPS_TIMER = Misc.toCycles(minutes, TimeUnit.MINUTES);
        Wogw.sendActivateMessage("double drops");
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.of("Turn on double drops for x minutes, ::doubledrops 60 (60 minutes).");
    }
}
