package com.dev.cinemaproject.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderDate;
    @OneToMany
    private List<Ticket> tickets;
    @ManyToOne
    private User user;

    public Order() {

    }

    public Order(List<Ticket> tickets, User user, LocalDateTime orderDate) {
        this.tickets = tickets;
        this.user = user;
        this.orderDate = orderDate;
    }
}
