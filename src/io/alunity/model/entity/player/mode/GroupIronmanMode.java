package io.alunity.model.entity.player.mode;

import io.alunity.model.entity.player.Player;
import io.alunity.model.entity.player.mode.group.GroupIronmanGroup;
import io.alunity.model.entity.player.mode.group.GroupIronmanRepository;

import java.time.LocalDate;

public class GroupIronmanMode extends IronmanMode {

    private static final LocalDate DONATION_DATE = LocalDate.of(2021, 7, 16);

    public GroupIronmanMode(ModeType type) {
        super(type);
    }

    @Override
    public boolean isTradingPermitted(Player player, Player other) {
        if (player == null || other == null)
            return false;
        GroupIronmanGroup group = GroupIronmanRepository.getGroupForOnline(player).orElse(null);
        return group != null && group.isGroupMember(other);
    }

    @Override
    public boolean isItemScavengingPermitted() {
        return false;
    }

    @Override
    public boolean isDonatingPermitted() {
        if (!LocalDate.now().isAfter(DONATION_DATE)) {
            return false;
        }
        return true;
    }

}
