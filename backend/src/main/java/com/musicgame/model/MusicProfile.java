package com.musicgame.model;

import java.util.ArrayList;
import java.util.List;

public class MusicProfile {

    private List<RankedTrack> topTracks = new ArrayList<>();
    private List<RankedArtist> topArtists = new ArrayList<>();

    public List<RankedTrack> getTopTracks() {
        return topTracks;
    }

    public void setTopTracks(List<RankedTrack> topTracks) {
        this.topTracks = topTracks;
    }

    public List<RankedArtist> getTopArtists() {
        return topArtists;
    }

    public void setTopArtists(List<RankedArtist> topArtists) {
        this.topArtists = topArtists;
    }
}
