package io.alunity.content.skills.herblore;

import io.alunity.content.achievement_diary.impl.VarrockDiaryEntry;
import io.alunity.content.dialogue.DialogueBuilder;
import io.alunity.content.dialogue.DialogueExpression;
import io.alunity.content.dialogue.DialogueOption;
import io.alunity.model.Items;
import io.alunity.model.Npcs;
import io.alunity.model.SlottedItem;
import io.alunity.model.entity.npc.NPC;
import io.alunity.model.entity.player.Player;
import io.alunity.util.Misc;

public class HerbloreDecantCleanUnfNpc {

    public static final int NPC_ID = Npcs.ZAHUR;
    private static final int SHOP_OPTION = 1;
    private static final int DECANT_OPTION = 2;
    private static final int CLEAN_OPTION = 3;
    private static final int UNF_OPTION = 4;

    public static boolean clickNpc(Player player, NPC npc, int option) {
        if (npc.getNpcId() != NPC_ID)
            return false;

        if (option == SHOP_OPTION) {
            player.getShops().openShop(21); // Herblore secondaries shop for non-ironman
        } else if (option == DECANT_OPTION) {
            PotionDecanting.decantInventory(player);
            player.getDiaryManager().getVarrockDiary().progress(VarrockDiaryEntry.POTION_DECANT);
        } else if (option == CLEAN_OPTION) {
            int price = HerbCleaner.getPriceForInventory(player);
            if (price < 1) {
                new DialogueBuilder(player).setNpcId(npc.getNpcId())
                        .npc(DialogueExpression.DISTRESSED, "You don't have any herbs to clean.")
                        .send();
                return true;
            }

            new DialogueBuilder(player).setNpcId(npc.getNpcId())
                    .npc("Would you like to clean these herbs?",
                            "It will cost " + Misc.formatCoins(price) + " coins.")
                    .option(
                            new DialogueOption("Yes, spend " + Misc.formatCoins(price) + " coins.", plr -> HerbCleaner.cleanHerbsFromInventory(player)),
                            new DialogueOption("No thanks.", plr -> plr.getPA().closeAllWindows())
                    )
                    .send();
        } else if (option == UNF_OPTION) {
            int price = UnfCreator.getPriceForInventory(player);

            if (price < 1) {
                new DialogueBuilder(player).setNpcId(npc.getNpcId())
                        .npc(DialogueExpression.DISTRESSED, "You don't have any herbs to make potions with.")
                        .send();
                return true;
            }

            new DialogueBuilder(player).setNpcId(npc.getNpcId())
                    .npc("Would you like to make unfinished potions?",
                            "It will cost " + Misc.formatCoins(price) + " coins.")
                    .option(
                            new DialogueOption("Yes, spend " + Misc.formatCoins(price) + " coins.", plr -> UnfCreator.makeUnfPotionsFromInventory(player)),
                            new DialogueOption("No thanks.", plr -> plr.getPA().closeAllWindows())
                    )
                    .send();
        }

        return true;
    }

    public static boolean useItemOnNpc(Player player, NPC npc, int itemId) {
        if (npc.getNpcId() != NPC_ID)
            return false;

        SlottedItem inventoryItem = player.getItems().getInventoryItem(itemId);
        if (inventoryItem == null)
            return true;


        PotionData.UnfinishedPotions unf = PotionData.UnfinishedPotions.forNotedOrUnNotedHerb(itemId);
        if (unf != null) {
            UnfCreator.makeUnfPotion(player, inventoryItem, unf, true);
            return true;
        }

        Herb herb = Herb.forNotedOrUnNotedGrimyHerb(itemId);
        if (herb != null) {
            HerbCleaner.cleanHerb(player, inventoryItem, herb, true);
            return true;
        }

        if (itemId == Items.COINS) {
            new DialogueBuilder(player).setNpcId(npc.getNpcId())
                    .npc(DialogueExpression.ALMOST_CRYING, "I'm not one of those girls!!")
                    .send();
            return true;
        }

        new DialogueBuilder(player).setNpcId(npc.getNpcId())
                .npc(DialogueExpression.ANGER_1, "And what shall I do with that?")
                .send();
        return true;
    }


}
