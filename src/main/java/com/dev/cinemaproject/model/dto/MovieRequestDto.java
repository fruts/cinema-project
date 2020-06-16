package com.dev.cinemaproject.model.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRequestDto {
    @NotNull(message = "Title must be not null")
    private String title;
    @NotNull(message = "Movie description must be not null")
    private String description;
}
