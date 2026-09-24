import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GameRoom, RoomJoinResponse } from '../models/game.models';

@Injectable({ providedIn: 'root' })
export class RoomService {

  private readonly apiUrl = 'http://localhost:8080/api/rooms';

  constructor(private readonly http: HttpClient) {}

  createRoom(displayName: string): Observable<RoomJoinResponse> {
    return this.http.post<RoomJoinResponse>(this.apiUrl, { displayName });
  }

  joinRoom(code: string, displayName: string): Observable<RoomJoinResponse> {
    return this.http.post<RoomJoinResponse>(
      `${this.apiUrl}/${code.trim().toUpperCase()}/players`,
      { displayName }
    );
  }

  getRoom(code: string): Observable<GameRoom> {
    return this.http.get<GameRoom>(
      `${this.apiUrl}/${code.trim().toUpperCase()}`
    );
  }
}
