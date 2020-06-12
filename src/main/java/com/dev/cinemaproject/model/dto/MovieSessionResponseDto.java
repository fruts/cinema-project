package com.dev.cinemaproject.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSessionResponseDto {
    private Long movieSessionId;
    private Long movieId;
    private String movieTitle;
    private Long cinemaHallId;
    private String cinemaHallDescription;
    private String showTime;
}
