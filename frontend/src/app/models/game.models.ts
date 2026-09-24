export interface RankedTrack {
  spotifyId: string;
  title: string;
  artistName: string;
  albumImageUrl: string | null;
  rank: number;
}

export interface RankedArtist {
  spotifyId: string;
  name: string;
  imageUrl: string | null;
  rank: number;
}

export interface MusicProfile {
  topTracks: RankedTrack[];
  topArtists: RankedArtist[];
}

export interface Player {
  id: string;
  displayName: string;
  host: boolean;
  spotifyConnected: boolean;
  score: number;
  musicProfile?: MusicProfile;
}

export type GameRoomStatus = 'LOBBY' | 'IN_GAME' | 'FINISHED';

export interface GameRoom {
  code: string;
  createdAt: string;
  status: GameRoomStatus;
  players: Player[];
}

export interface RoomJoinResponse {
  room: GameRoom;
  playerId: string;
}
