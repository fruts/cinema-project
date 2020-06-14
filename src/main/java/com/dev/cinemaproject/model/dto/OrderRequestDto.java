package com.dev.cinemaproject.model.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    @NotNull(message = "User id must be not null")
    private Long userId;
}
