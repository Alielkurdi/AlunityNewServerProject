package io.alunity.test;

import io.alunity.Server;
import io.alunity.ServerConfiguration;
import io.alunity.ServerState;

public class ServerTest {

    private final ServerConfiguration configuration;

    public ServerTest(ServerConfiguration configuration) {
        this.configuration = configuration;
        try {
            Server.startServerless(configuration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ServerTest(ServerState serverState) {
        this(TestServerConfiguration.getConfiguration(serverState));
    }

    public ServerConfiguration getConfiguration() {
        return configuration;
    }
}
