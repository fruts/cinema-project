package com.dev.cinemaproject.mapper;

import com.dev.cinemaproject.model.Order;
import com.dev.cinemaproject.model.dto.OrderResponseDto;
import com.dev.cinemaproject.model.dto.TicketResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final TicketMapper ticketMapper;

    public OrderMapper(TicketMapper ticketMapper) {
        this.ticketMapper = ticketMapper;
    }

    public OrderResponseDto convertToResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getId());
        orderResponseDto.setOrderDate(order.getOrderDate().toString());
        List<TicketResponseDto> tickets = order.getTickets().stream()
                .map(ticketMapper::convertToResponseDto)
                .collect(Collectors.toList());
        orderResponseDto.setTickets(tickets);
        return orderResponseDto;
    }
}
