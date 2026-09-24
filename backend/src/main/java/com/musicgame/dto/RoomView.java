package com.musicgame.dto;

import com.musicgame.model.GameRoom;
import com.musicgame.model.GameRoomStatus;

import java.time.Instant;
import java.util.List;

public record RoomView(
        String code,
        Instant createdAt,
        GameRoomStatus status,
        List<PlayerView> players
) {

    public static RoomView from(GameRoom room) {
        return new RoomView(
                room.getCode(),
                room.getCreatedAt(),
                room.getStatus(),
                room.getPlayers().stream()
                        .map(PlayerView::from)
                        .toList()
        );
    }
}
