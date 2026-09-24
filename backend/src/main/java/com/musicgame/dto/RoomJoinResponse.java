package com.musicgame.dto;

import com.musicgame.model.GameRoom;

public record RoomJoinResponse(
        GameRoom room,
        String playerId
) {
}
