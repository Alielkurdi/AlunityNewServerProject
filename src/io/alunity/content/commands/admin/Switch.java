package io.alunity.content.commands.admin;

import io.alunity.content.commands.Command;
import io.alunity.model.SpellBook;
import io.alunity.model.entity.player.Player;

/**
 * @author Arthur Behesnilian 1:04 PM
 */
public class Switch extends Command {

    @Override
    public void execute(Player player, String commandName, String input) {
        SpellBook nextSpellbook = SpellBook.MODERN;
        switch (player.playerMagicBook) {
            case 0:
                nextSpellbook = SpellBook.ANCIENT;
                break;
            case 1:
                nextSpellbook = SpellBook.LUNAR;
                break;
        }
        player.sendMessage("You switch your spellbook to the " + nextSpellbook.name()+ " spellbook.");
        player.setSpellBook(nextSpellbook);
    }


}
