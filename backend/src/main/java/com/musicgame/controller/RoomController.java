package com.musicgame.controller;

import com.musicgame.dto.CreateRoomRequest;
import com.musicgame.dto.JoinRoomRequest;
import com.musicgame.dto.RoomJoinResponse;
import com.musicgame.model.GameRoom;
import com.musicgame.model.Player;
import com.musicgame.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "http://localhost:4200")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomJoinResponse createRoom(@Valid @RequestBody CreateRoomRequest request) {
        RoomService.RoomCreation creation = roomService.createRoom(request.displayName());
        return new RoomJoinResponse(creation.room(), creation.host().getId());
    }

    @PostMapping("/{code}/players")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomJoinResponse joinRoom(
            @PathVariable String code,
            @Valid @RequestBody JoinRoomRequest request
    ) {
        Player player = roomService.joinRoom(code, request.displayName());
        GameRoom room = roomService.getRequiredRoom(code);
        return new RoomJoinResponse(room, player.getId());
    }

    @GetMapping("/{code}")
    public GameRoom getRoom(@PathVariable String code) {
        return roomService.getRequiredRoom(code);
    }
}
