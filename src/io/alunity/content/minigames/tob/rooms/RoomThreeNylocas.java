package io.alunity.content.minigames.tob.rooms;

import io.alunity.content.instances.InstancedArea;
import io.alunity.content.minigames.tob.TobConstants;
import io.alunity.content.minigames.tob.TobRoom;
import io.alunity.content.minigames.tob.bosses.Nylocas;
import io.alunity.model.collisionmap.WorldObject;
import io.alunity.model.entity.player.Boundary;
import io.alunity.model.entity.player.Player;
import io.alunity.model.entity.player.Position;
import io.alunity.model.world.objects.GlobalObject;

public class RoomThreeNylocas extends TobRoom {

    @Override
    public Nylocas spawn(InstancedArea instancedArea) {
        return new Nylocas(instancedArea);
    }

    @Override
    public Position getPlayerSpawnPosition() {
        return new Position(3296, 4283);
    }

    @Override
    public boolean handleClickObject(Player player, WorldObject worldObject, int option) {
        return false;
    }

    @Override
    public void handleClickBossGate(Player player, WorldObject worldObject) {
        if (player.getY() >= 4256) {
            player.getPA().movePlayer(player.getX(), 4254, player.getHeight());
        } else {
            player.getPA().movePlayer(player.getX(), 4256, player.getHeight());
        }
    }

    @Override
    public boolean isRoomComplete(InstancedArea instancedArea) {
        return instancedArea.getNpcs().isEmpty();
    }

    @Override
    public Boundary getBoundary() {
        return TobConstants.NYLOCAS_BOSS_ROOM_BOUNDARY;
    }

    @Override
    public Position getDeathPosition() {
        return new Position(3295, 4256, 0);
    }

    @Override
    public Position getFightStartPosition() {
        return new Position(3295, 4254, 0);
    }

    @Override
    public GlobalObject getFoodChestPosition() {
        return getFoodChest(new Position(3303, 4274, 0), 3);
    }
}
