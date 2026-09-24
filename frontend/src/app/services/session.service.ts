import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class SessionService {

  private readonly playerIdKey = 'music-game-player-id';

  setPlayerId(playerId: string): void {
    sessionStorage.setItem(this.playerIdKey, playerId);
  }

  getPlayerId(): string | null {
    return sessionStorage.getItem(this.playerIdKey);
  }
}
