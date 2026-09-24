package com.musicgame.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class GameRoom {

    private final String code;
    private final Instant createdAt;
    private GameRoomStatus status;
    private final List<Player> players;

    public GameRoom(String code) {
        this.code = code;
        this.createdAt = Instant.now();
        this.status = GameRoomStatus.LOBBY;
        this.players = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public GameRoomStatus getStatus() {
        return status;
    }

    public void setStatus(GameRoomStatus status) {
        this.status = status;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
