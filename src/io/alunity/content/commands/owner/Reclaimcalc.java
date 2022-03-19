package io.alunity.content.commands.owner;

import io.alunity.Server;
import io.alunity.content.commands.Command;
import io.alunity.model.entity.player.Player;
import io.alunity.sql.donation.model.DonationItem;
import io.alunity.sql.donation.model.DonationItemList;
import io.alunity.sql.donation.query.GetDonationsQuery;
import io.alunity.sql.donation.reclaim.ReclaimQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class Reclaimcalc extends Command {

	@Override
	public void execute(Player c, String commandName, String input) {
		String[] names = input.split("-");

		if (names.length < 2) {
			c.sendMessage("Two names required, ::reclaimcalc-player claiming-account they are claiming");
			return;
		}

		String name = names[0].toLowerCase();
		String oldUsername = names[1].toLowerCase();

		Server.getDatabaseManager().exec(Server.getConfiguration().getStoreDatabase(), (context, connection) -> {
			try {
				boolean usedOldReclaim = Server.getDatabaseManager().executeImmediate(Server.getEmbeddedDatabase(), (context2, connection2) -> {
					PreparedStatement select = connection2.prepareStatement("SELECT * FROM reclaimed_donations WHERE username = ?");
					select.setString(1, name.toLowerCase());
					ResultSet rs = select.executeQuery();
					return rs.next();
				});

				DonationItemList donations = context.executeImmediate(Server.getConfiguration().getStoreDatabase(), new GetDonationsQuery(oldUsername));
				List<DonationItem> v1Donations = donations.stream().filter(item -> item.isV1Donation() && item.isClaimed()).collect(Collectors.toList());
				int dollars = v1Donations.stream().mapToInt(DonationItem::getItemCost).sum();

				int newDollars = ReclaimQuery.getV1DonationDollars(connection, oldUsername);
				c.addQueuedAction(plr -> c.sendMessage(String.format("'%s', usedOld=%b, oldDollars=%d, newDollars=%d, difference=%s",
						name, usedOldReclaim, dollars, newDollars, newDollars - dollars)));
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				c.addQueuedAction(plr -> plr.sendMessage("There was an error calculating old reclaim."));
			}

			return null;
		});
	}

}
