package com.dev.cinemaproject.model.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCartResponseDto {
    private List<TicketResponseDto> tickets;
}
