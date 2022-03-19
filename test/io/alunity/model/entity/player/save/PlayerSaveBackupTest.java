package io.alunity.model.entity.player.save;

import io.alunity.ServerState;
import io.alunity.model.entity.player.save.backup.PlayerSaveBackup;
import io.alunity.test.ServerTest;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PlayerSaveBackupTest {

    private static final ServerTest serverTest = new ServerTest(ServerState.PUBLIC);

    private static File create(LocalDateTime time) throws IOException {
        File file = PlayerSaveBackup.backup(time);
        assertTrue(file != null);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
        return file;
    }

    @Test
    public void testBackups() throws IOException {
        File oldFile = create(LocalDateTime.of(2020, 5, 10, 1, 1, 1));
        File newFile = create(LocalDateTime.now());
        assertTrue(!oldFile.exists());
        assertTrue(newFile.exists());
    }

}