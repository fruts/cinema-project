package com.dev.cinemaproject.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketResponseDto {
    private Long ticketId;
    private String movieTitle;
    private String hallDescription;
    private String showTime;

}
