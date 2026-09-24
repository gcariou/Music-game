package com.musicgame.service;

import com.musicgame.model.GameRoom;
import com.musicgame.model.GameRoomStatus;
import com.musicgame.model.Player;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RoomService {

    private static final int MAX_PLAYERS = 5;
    private static final String CODE_CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private static final int CODE_LENGTH = 4;

    private final SecureRandom random = new SecureRandom();
    private final Map<String, GameRoom> rooms = new ConcurrentHashMap<>();

    public RoomCreation createRoom(String displayName) {
        String code = generateUniqueCode();
        GameRoom room = new GameRoom(code);
        Player host = new Player(displayName.trim(), true);
        room.getPlayers().add(host);
        rooms.put(code, room);
        return new RoomCreation(room, host);
    }

    public Player joinRoom(String code, String displayName) {
        GameRoom room = getRequiredRoom(code);

        synchronized (room) {
            if (room.getStatus() != GameRoomStatus.LOBBY) {
                throw new IllegalStateException("La partie a déjà commencé.");
            }

            if (room.getPlayers().size() >= MAX_PLAYERS) {
                throw new IllegalStateException("La room est complète.");
            }

            boolean nameAlreadyUsed = room.getPlayers().stream()
                    .anyMatch(player -> player.getDisplayName().equalsIgnoreCase(displayName.trim()));

            if (nameAlreadyUsed) {
                throw new IllegalArgumentException("Ce pseudo est déjà utilisé dans la room.");
            }

            Player player = new Player(displayName.trim(), false);
            room.getPlayers().add(player);
            return player;
        }
    }

    public GameRoom getRequiredRoom(String code) {
        return findRoom(code)
                .orElseThrow(() -> new IllegalArgumentException("Room introuvable."));
    }

    public Optional<GameRoom> findRoom(String code) {
        if (code == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(rooms.get(code.trim().toUpperCase()));
    }

    private String generateUniqueCode() {
        String code;
        do {
            StringBuilder builder = new StringBuilder(CODE_LENGTH);
            for (int i = 0; i < CODE_LENGTH; i++) {
                builder.append(CODE_CHARS.charAt(random.nextInt(CODE_CHARS.length())));
            }
            code = builder.toString();
        } while (rooms.containsKey(code));

        return code;
    }

    public record RoomCreation(GameRoom room, Player host) {
    }
}
