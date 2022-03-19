package io.alunity.util.discord;

import io.alunity.ServerState;
import io.alunity.test.ServerTest;
import org.junit.jupiter.api.Test;

class DiscordTest {

    private static final ServerTest test = new ServerTest(ServerState.PUBLIC);

    @Test
    public void sendDiscordTestMessages() throws InterruptedException {
        Discord.writeServerSyncMessage("test");
        Discord.writeBugMessage("Test");
        Discord.writeCheatEngineMessage("test");
        Discord.writeFoeMessage("Test");
        Discord.writeReferralMessage("Test");
        Discord.writeSuggestionMessage("test");
        Discord.writeAddressSwapMessage("test");
        Thread.sleep(60_000 * 15);
    }

}