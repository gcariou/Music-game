# Music Game

Jeu musical multijoueur IRL basé sur les goûts Spotify des joueurs.

## V0.1

- 2 à 5 joueurs
- Room par code
- Connexion Spotify de chaque joueur
- Import des Top 20 tracks + Top 20 artists sur ~6 mois
- 10 manches
- Mode unique : **Qui écoute ça ?**
- Votes, révélation et score en temps réel
- Pas de base de données : état des parties en mémoire

## Stack

- Frontend : Angular
- Backend : Spring Boot
- Temps réel : WebSocket / STOMP
- Musique : Spotify Web API + OAuth 2.0 Authorization Code Flow

## Structure

```text
Music-game/
├── backend/
├── frontend/
├── docs/
├── .gitignore
└── README.md
```

## Développement

Les instructions de lancement seront complétées au fur et à mesure de la V0.1.
