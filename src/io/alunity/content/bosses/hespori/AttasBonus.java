package io.alunity.content.bosses.hespori;

import java.util.concurrent.TimeUnit;

import io.alunity.content.QuestTab;
import io.alunity.content.bonus.DoubleExperience;
import io.alunity.content.wogw.Wogw;
import io.alunity.model.entity.player.Player;
import io.alunity.model.entity.player.PlayerHandler;

public class AttasBonus implements HesporiBonus {
    @Override
    public void activate(Player player) {
        Wogw.EXPERIENCE_TIMER += TimeUnit.HOURS.toMillis(1) / 600;
        PlayerHandler.executeGlobalMessage("@bla@[@gre@Hespori@bla@] The Attas has sprouted and is granting 1 hours bonus xp!");
        QuestTab.updateAllQuestTabs();
    }

    @Override
    public void deactivate() {
        Hespori.activeAttasSeed = false;
        Hespori.ATTAS_TIMER = 0;
        updateObject(false);
    }

    @Override
    public boolean canPlant(Player player) {
        if (DoubleExperience.isDoubleExperience()) {
            player.sendMessage("This seed can't be planted during bonus experience.");
            return false;
        }
        return true;
    }

    @Override
    public HesporiBonusPlant getPlant() {
        return HesporiBonusPlant.ATTAS;
    }
}
