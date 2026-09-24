package com.musicgame.dto;

public record RoomJoinResponse(
        RoomView room,
        String playerId
) {
}
