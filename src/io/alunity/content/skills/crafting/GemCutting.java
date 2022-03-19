package io.alunity.content.skills.crafting;

import java.util.Arrays;
import java.util.Optional;

import io.alunity.Server;
import io.alunity.content.skills.Skill;
import io.alunity.model.cycleevent.Event;
import io.alunity.model.entity.player.Player;
import io.alunity.util.Misc;

public class GemCutting {

	public static void cut(Player c, int use, int used) {
		Optional<Gem> gem = Arrays.stream(Gem.values()).filter(g -> g.getUncut() == used || g.getUncut() == use).findFirst();
		gem.ifPresent(g -> {
			c.getPA().stopSkilling();
			if (c.playerLevel[Skill.CRAFTING.getId()] < g.getLevel()) {
				c.sendMessage("You need a crafting level of " + g.getLevel() + " to do this.");
				return;
			}
			c.startAnimation(886);
			Server.getEventHandler().submit(new Event<Player>("skilling", c, 2) {

				@Override
				public void execute() {
					if (attachment == null || attachment.isDisconnected() || attachment.getSession() == null) {
						stop();
						return;
					}
					if (Misc.random(300) == 0 && attachment.getInterfaceEvent().isExecutable()) {
						attachment.getInterfaceEvent().execute();
						super.stop();
						return;
					}
					if (attachment.getItems().playerHasItem(g.getUncut())) {
						attachment.getItems().deleteItem2(g.getUncut(), 1);
						attachment.getItems().addItem(g.getCut(), 1);
						attachment.getPA().addSkillXPMultiplied(g.getExperience(), Skill.CRAFTING.getId(), true);
						attachment.startAnimation(886);
					}
					if (!attachment.getItems().playerHasItem(g.getUncut())) {
						stop();
						return;
					}
				}

				@Override
				public void stop() {
					super.stop();
					if (attachment == null || attachment.isDisconnected() || attachment.getSession() == null) {
						return;
					}
					attachment.stopAnimation();
				}

			});
		});
	}

}