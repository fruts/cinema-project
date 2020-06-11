package com.dev.cinemaproject.mapper;

import com.dev.cinemaproject.model.Ticket;
import com.dev.cinemaproject.model.dto.TicketResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public TicketResponseDto convertToResponseDto(Ticket ticket) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setTicketId(ticket.getId());
        ticketResponseDto.setMovieTitle(ticket.getMovieSession().getMovie().getTitle());
        ticketResponseDto.setHallDescription(ticket.getMovieSession()
                .getCinemaHall().getDescription());
        ticketResponseDto.setShowTime(ticket.getMovieSession().getShowTime().toString());
        return ticketResponseDto;
    }
}
