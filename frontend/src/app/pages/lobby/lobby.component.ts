import { Component, OnDestroy, OnInit, signal } from '@angular/core';
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

  room = signal<GameRoom | null>(null);
  errorMessage = signal('');

  private subscription?: Subscription;

  constructor(
    private readonly route: ActivatedRoute,
    private readonly roomService: RoomService
  ) { }

  ngOnInit(): void {
    const code = this.route.snapshot.paramMap.get('code');

    if (!code) {
      this.errorMessage.set('Code de room manquant.');
      return;
    }

    // Chargement immédiat
    this.roomService.getRoom(code).subscribe({
      next: room => this.room.set(room),
      error: () => this.errorMessage.set('Impossible de charger la room.')
    });

    // Rafraîchissement temporaire toutes les 1,5 secondes
    this.subscription = interval(1500)
      .pipe(
        switchMap(() => this.roomService.getRoom(code))
      )
      .subscribe({
        next: room => this.room.set(room),
        error: () => this.errorMessage.set('Impossible de charger la room.')
      });
  }

  ngOnDestroy(): void {
    this.subscription?.unsubscribe();
  }
}