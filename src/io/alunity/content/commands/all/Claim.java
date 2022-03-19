package io.alunity.content.commands.all;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.alunity.Server;
import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;
import io.alunity.model.entity.player.PlayerHandler;
import io.alunity.model.entity.player.Right;
import io.alunity.sql.Donation;
import io.alunity.sql.donation.query.ClaimDonationsQuery;
import io.alunity.sql.donation.model.DonationItem;
import io.alunity.sql.donation.model.DonationItemList;
import io.alunity.sql.donation.query.GetDonationsQuery;
import io.alunity.util.logging.player.DonatedLog;

/**
 * Changes the password of the player.
 *
 * @author Emiel
 *
 */
public class Claim extends Command {

	public static boolean giveDonationItem(Player plr, DonationItem item) {
		int itemId = item.getItemId();
		int itemQuantity = item.getItemAmount();
		if (plr.getItems().hasRoomInInventory(itemId, itemQuantity)) {
			plr.getItems().addItem(itemId, itemQuantity);
			Server.getLogging().write(new DonatedLog(plr, item));
			plr.getDonationRewards().increaseDonationAmount(item.getItemCost() * itemQuantity);
			plr.sendMessage("You've received x" + item.getItemAmount() + " " + item.getItemName());
			PlayerHandler.message(Right.OWNER, "@blu@[" + plr.getDisplayName() + "]@pur@ has just donated for " + itemQuantity + " " + item.getItemName() + "!");
			return true;
		} else {
			plr.sendMessage("Not enough room in inventory to claim " + item.getItemName() + ", make space and try again.");
			return false;
		}
	}

	@Override
	public void execute(Player c, String commandName, String input) {
		//new Thread(new Donation(c)).start();
		new Donation(c).run();
		// the reason you will see those "NULL" error -> Non-game thread is writing packets thread=Thread-9 {}

		//is because of me making it do a new thread for checking donations so it doesn't affect the game server
		//Because of using Fox Store, we need to open a new thread to claim the donations for that player.
		c.getDonationRewards().openInterface();
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Claim your donated item.");
	}
}
