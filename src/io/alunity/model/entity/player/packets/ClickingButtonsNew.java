package io.alunity.model.entity.player.packets;

import io.alunity.Server;
import io.alunity.content.dailyrewards.DailyRewards;
import io.alunity.content.party.PartyInterface;
import io.alunity.content.wildwarning.WildWarning;
import io.alunity.model.entity.player.PacketType;
import io.alunity.model.entity.player.Player;
import io.alunity.util.logging.player.ClickButtonLog;

/**
 * Since button clicking is messed up and sends a weird id some new things
 * were colliding with old things. This is an attempt to fix that with a cheap hack.
 * @author Michael Sasse (https://github.com/mikeysasse/)
 */
public class ClickingButtonsNew implements PacketType {

    public static final int CLICKING_BUTTONS_NEW = 184;

    @Override
    public void processPacket(Player c, int packetType, int packetSize) {
        if (c.getMovementState().isLocked())
            return;
        if (c.isFping()) {
            /**
             * Cannot do action while fping
             */
            return;
        }
        int buttonId = c.getInStream().readUnsignedWord();
        Server.getLogging().write(new ClickButtonLog(c, buttonId, true));

        if (c.debugMessage) {
            c.sendMessage("ClickingButtonsNew: " + buttonId + ", DialogueID: " + c.dialogueAction);
        }

        if (c.getQuestTab().handleActionButton(buttonId)) {
            return;
        }

        if (c.getEventCalendar().handleButton(buttonId)) {
            return;
        }

        if (buttonId == DailyRewards.CLAIM_BUTTON) {
            c.getDailyRewards().claim();
        }

        if (c.getWogwContributeInterface().clickButton(buttonId)) {
            return;
        }

        if (c.getQuesting().clickButton(buttonId)) {
            return;
        }

        if (c.getTeleportInterface().clickButton(buttonId)) {
            return;
        }

        if (c.getAchievements().clickButton(buttonId)) {
            return;
        }

        if (c.getDiaryManager().clickButton(buttonId)) {
            return;
        }

        if (PartyInterface.handleButton(c, buttonId))
            return;
        if (WildWarning.handleButtonClick(c, buttonId))
            return;
    }
}
