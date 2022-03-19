package io.alunity.content.teleportation.inter;

import java.util.function.Consumer;

import io.alunity.model.entity.player.Player;

public class TeleportButton {

    private final String name;
    private final Consumer<Player> action;

    public TeleportButton(String name, Consumer<Player> action) {
        this.name = name;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public Consumer<Player> getAction() {
        return action;
    }
}
