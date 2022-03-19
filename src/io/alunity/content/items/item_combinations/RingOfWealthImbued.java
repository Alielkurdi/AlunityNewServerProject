package io.alunity.content.items.item_combinations;

import java.util.List;
import java.util.Optional;

import io.alunity.model.entity.player.Player;
import io.alunity.model.items.GameItem;
import io.alunity.model.items.ItemCombination;

public class RingOfWealthImbued extends ItemCombination {

	public RingOfWealthImbued(GameItem outcome, Optional<List<GameItem>> revertedItems, GameItem[] items) {
		super(outcome, revertedItems, items);
	}

	@Override
	public void combine(Player player) {
		items.forEach(item -> player.getItems().deleteItem2(item.getId(), item.getAmount()));
		player.getItems().addItem(outcome.getId(), outcome.getAmount());
		player.getDH().sendItemStatement("You combined the items and created a ring of wealth (i).", 12785);
		player.setCurrentCombination(Optional.empty());
		player.nextChat = -1;
	}

	@Override
	public void showDialogue(Player player) {
		player.getDH().sendStatement("The ring of wealth imbued is untradable.", "You cannot revert this.");
	}

}
