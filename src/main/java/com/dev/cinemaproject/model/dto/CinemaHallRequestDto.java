package com.dev.cinemaproject.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CinemaHallRequestDto {
    @NotNull(message = "Capacity must be not null")
    @Min(30)
    private Integer capacity;
    @NotNull(message = "Description must be not null")
    @Size(min = 10, max = 200, message = "Description size must be > 10 and < 200")
    private String description;
}
