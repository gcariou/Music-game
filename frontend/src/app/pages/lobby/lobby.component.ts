import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { interval, Subscription, switchMap } from 'rxjs';
import { GameRoom } from '../../models/game.models';
import { RoomService } from '../../services/room.service';

@Component({
  selector: 'app-lobby',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './lobby.component.html',
  styleUrl: './lobby.component.css'
})
export class LobbyComponent implements OnInit, OnDestroy {

  room?: GameRoom;
  errorMessage = '';
  private subscription?: Subscription;

  constructor(
    private readonly route: ActivatedRoute,
    private readonly roomService: RoomService
  ) {}

  ngOnInit(): void {
    const code = this.route.snapshot.paramMap.get('code');

    if (!code) {
      this.errorMessage = 'Code de room manquant.';
      return;
    }

    this.subscription = interval(1500)
      .pipe(switchMap(() => this.roomService.getRoom(code)))
      .subscribe({
        next: room => this.room = room,
        error: () => this.errorMessage = 'Impossible de charger la room.'
      });

    this.roomService.getRoom(code).subscribe({
      next: room => this.room = room,
      error: () => this.errorMessage = 'Impossible de charger la room.'
    });
  }

  ngOnDestroy(): void {
    this.subscription?.unsubscribe();
  }
}
