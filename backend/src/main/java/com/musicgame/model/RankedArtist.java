package com.musicgame.model;

public record RankedArtist(
        String spotifyId,
        String name,
        String imageUrl,
        int rank
) {
}
