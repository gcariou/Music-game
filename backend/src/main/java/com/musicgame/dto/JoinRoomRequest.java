package com.musicgame.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record JoinRoomRequest(
        @NotBlank
        @Size(max = 30)
        String displayName
) {
}
