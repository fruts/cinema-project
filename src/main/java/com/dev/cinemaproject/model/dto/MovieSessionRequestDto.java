package com.dev.cinemaproject.model.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSessionRequestDto {
    @NotNull(message = "Movie id must be not null")
    private Long movieId;
    @NotNull(message = "Cinema hall id must be not null")
    private Long cinemaHallId;
    @NotNull(message = "Show time must be not null")
    private String showTime;
}
