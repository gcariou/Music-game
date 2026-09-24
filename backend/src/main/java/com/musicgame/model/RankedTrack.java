package com.musicgame.model;

public record RankedTrack(
        String spotifyId,
        String title,
        String artistName,
        String albumImageUrl,
        int rank
) {
}
