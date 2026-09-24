import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RoomService } from '../../services/room.service';
import { SessionService } from '../../services/session.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  displayName = '';
  roomCode = '';
  errorMessage = '';
  loading = false;

  constructor(
    private readonly roomService: RoomService,
    private readonly sessionService: SessionService,
    private readonly router: Router
  ) {}

  createRoom(): void {
    if (!this.displayName.trim()) {
      this.errorMessage = 'Choisis un pseudo.';
      return;
    }

    this.loading = true;
    this.errorMessage = '';

    this.roomService.createRoom(this.displayName.trim()).subscribe({
      next: response => {
        this.sessionService.setPlayerId(response.playerId);
        void this.router.navigate(['/room', response.room.code]);
      },
      error: error => {
        this.errorMessage = error?.error?.detail ?? 'Impossible de créer la room.';
        this.loading = false;
      }
    });
  }

  joinRoom(): void {
    if (!this.displayName.trim() || !this.roomCode.trim()) {
      this.errorMessage = 'Entre un pseudo et un code de room.';
      return;
    }

    this.loading = true;
    this.errorMessage = '';

    this.roomService.joinRoom(this.roomCode, this.displayName.trim()).subscribe({
      next: response => {
        this.sessionService.setPlayerId(response.playerId);
        void this.router.navigate(['/room', response.room.code]);
      },
      error: error => {
        this.errorMessage = error?.error?.detail ?? 'Impossible de rejoindre la room.';
        this.loading = false;
      }
    });
  }
}
