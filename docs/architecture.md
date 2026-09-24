# Architecture V0.1

## Flux utilisateur

1. Le host crée une room.
2. Les autres joueurs rejoignent avec le code de room.
3. Chaque joueur connecte Spotify.
4. Le backend récupère ses Top 20 tracks et Top 20 artists sur `medium_term`.
5. Tous les joueurs deviennent prêts.
6. Le host lance une partie de 10 manches.
7. À chaque manche, le serveur choisit un item associé à un joueur.
8. Tous les joueurs votent.
9. Le serveur révèle le propriétaire et met à jour les scores.
10. À la fin, classement final.

## Composants

```text
Angular
  |
  | REST + WebSocket/STOMP
  v
Spring Boot
  |- RoomService
  |- SpotifyService
  |- GameService
  |- RoundGenerator
  |- WebSocket broadcaster
  |
  v
Spotify Web API
```

## État V0.1

Aucune persistance. Les rooms vivent en mémoire côté backend.

```java
ConcurrentHashMap<String, GameRoom>
```

## Principes

- Aucun secret Spotify dans le frontend.
- Le backend transforme les réponses Spotify en modèles métier internes.
- Le jeu ne manipule pas directement les DTO Spotify.
- Pas de PostgreSQL en V0.1.
