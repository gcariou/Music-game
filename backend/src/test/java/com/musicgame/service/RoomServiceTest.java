package com.musicgame.service;

import com.musicgame.model.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoomServiceTest {

    private final RoomService service = new RoomService();

    @Test
    void createRoomCreatesHost() {
        RoomService.RoomCreation creation = service.createRoom("Gwen");

        assertThat(creation.room().getCode()).hasSize(4);
        assertThat(creation.room().getPlayers()).hasSize(1);
        assertThat(creation.host().getDisplayName()).isEqualTo("Gwen");
        assertThat(creation.host().isHost()).isTrue();
    }

    @Test
    void joinRoomAddsPlayer() {
        RoomService.RoomCreation creation = service.createRoom("Gwen");

        Player player = service.joinRoom(creation.room().getCode(), "Alice");

        assertThat(player.getDisplayName()).isEqualTo("Alice");
        assertThat(player.isHost()).isFalse();
        assertThat(creation.room().getPlayers()).hasSize(2);
    }

    @Test
    void duplicateNameIsRejected() {
        RoomService.RoomCreation creation = service.createRoom("Gwen");

        assertThatThrownBy(() -> service.joinRoom(creation.room().getCode(), "gwen"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void roomCannotContainMoreThanFivePlayers() {
        RoomService.RoomCreation creation = service.createRoom("A");
        String code = creation.room().getCode();

        service.joinRoom(code, "B");
        service.joinRoom(code, "C");
        service.joinRoom(code, "D");
        service.joinRoom(code, "E");

        assertThatThrownBy(() -> service.joinRoom(code, "F"))
                .isInstanceOf(IllegalStateException.class);
    }
}
