package com.musicgame.dto;

import com.musicgame.model.Player;

public record PlayerView(
        String id,
        String displayName,
        boolean host,
        boolean spotifyConnected,
        int score
) {

    public static PlayerView from(Player player) {
        return new PlayerView(
                player.getId(),
                player.getDisplayName(),
                player.isHost(),
                player.isSpotifyConnected(),
                player.getScore()
        );
    }
}
