package io.alunity.model;

import io.alunity.model.entity.player.Player;

public interface AmountInput {
    void handle(Player player, int amount);
}
