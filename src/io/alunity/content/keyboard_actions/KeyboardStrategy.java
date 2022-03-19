package io.alunity.content.keyboard_actions;

import io.alunity.model.entity.player.Player;

@FunctionalInterface
public interface KeyboardStrategy {
    void execute(Player player);
}
