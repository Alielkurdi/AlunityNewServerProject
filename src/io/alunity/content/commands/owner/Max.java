package io.alunity.content.commands.owner;

import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;

public class Max extends Command {

	@Override
	public void execute(Player player, String commandName, String input) {
		for (int i = 0; i < 22; i++) {
			player.playerLevel[i] = 99;
			player.playerXP[i] = player.getPA().getXPForLevel(99) + 1;
			player.getPA().refreshSkill(i);
			player.getPA().setSkillLevel(i, player.playerLevel[i], player.playerXP[i]);
			player.getPA().levelUp(i);
		}
	}

}
