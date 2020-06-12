package com.dev.cinemaproject.mapper;

import com.dev.cinemaproject.model.ShoppingCart;
import com.dev.cinemaproject.model.dto.ShoppingCartResponseDto;
import com.dev.cinemaproject.model.dto.TicketResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    private final TicketMapper ticketMapper;

    public ShoppingCartMapper(TicketMapper titcketMapper) {
        this.ticketMapper = titcketMapper;
    }

    public ShoppingCartResponseDto convertToResponseDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        List<TicketResponseDto> tickets = shoppingCart.getTickets().stream()
                .map(ticketMapper::convertToResponseDto)
                .collect(Collectors.toList());
        shoppingCartResponseDto.setTickets(tickets);
        return shoppingCartResponseDto;
    }
}
