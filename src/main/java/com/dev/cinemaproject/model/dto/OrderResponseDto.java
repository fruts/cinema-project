package com.dev.cinemaproject.model.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {
    private Long orderId;
    private String orderDate;
    private List<TicketResponseDto> tickets;
}
