package com.musicgame.model;

import java.util.UUID;

public class Player {

    private final String id;
    private String displayName;
    private boolean host;
    private boolean spotifyConnected;
    private int score;
    private MusicProfile musicProfile;

    public Player(String displayName, boolean host) {
        this.id = UUID.randomUUID().toString();
        this.displayName = displayName;
        this.host = host;
        this.spotifyConnected = false;
        this.score = 0;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isHost() {
        return host;
    }

    public boolean isSpotifyConnected() {
        return spotifyConnected;
    }

    public void setSpotifyConnected(boolean spotifyConnected) {
        this.spotifyConnected = spotifyConnected;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public MusicProfile getMusicProfile() {
        return musicProfile;
    }

    public void setMusicProfile(MusicProfile musicProfile) {
        this.musicProfile = musicProfile;
    }
}
