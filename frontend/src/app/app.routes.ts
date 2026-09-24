import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LobbyComponent } from './pages/lobby/lobby.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'room/:code', component: LobbyComponent },
  { path: '**', redirectTo: '' }
];
