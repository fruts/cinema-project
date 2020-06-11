package com.dev.cinemaproject.model.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSessionRequestDto {
    private Long movieId;
    private Long cinemaHallId;
    private String showTime;
}
