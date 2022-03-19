package io.alunity.content.minigames.tob.rooms;

import io.alunity.content.instances.InstancedArea;
import io.alunity.content.minigames.tob.TobConstants;
import io.alunity.content.minigames.tob.TobRoom;
import io.alunity.content.minigames.tob.bosses.MaidenOfSugadinti;
import io.alunity.model.collisionmap.WorldObject;
import io.alunity.model.entity.player.Boundary;
import io.alunity.model.entity.player.Player;
import io.alunity.model.entity.player.Position;
import io.alunity.model.world.objects.GlobalObject;

public class RoomOneMaiden extends TobRoom {

    @Override
    public MaidenOfSugadinti spawn(InstancedArea instancedArea) {
        return new MaidenOfSugadinti(instancedArea);
    }

    @Override
    public Position getPlayerSpawnPosition() {
        return new Position(3219, 4460);
    }

    @Override
    public boolean handleClickObject(Player player, WorldObject worldObject, int option) {
        return false;
    }

    @Override
    public void handleClickBossGate(Player player, WorldObject worldObject) {
        if (player.getX() >= 3186) {
            player.getPA().movePlayer(3184, player.getY(), player.getHeight());
        } else if (player.getX() <= 3184) {
            player.getPA().movePlayer(3186, player.getY(), player.getHeight());
        }
    }

    @Override
    public boolean isRoomComplete(InstancedArea instancedArea) {
        return instancedArea.getNpcs().isEmpty();
    }

    @Override
    public Boundary getBoundary() {
        return TobConstants.MAIDEN_BOSS_ROOM_BOUNDARY;
    }

    @Override
    public Position getDeathPosition() {
        return new Position(3186, 4446);
    }

    @Override
    public Position getFightStartPosition() {
        return new Position(3184, 4446, 0);
    }

    @Override
    public GlobalObject getFoodChestPosition() {
        return getFoodChest(new Position(3175, 4422, 0), 0);
    }
}
