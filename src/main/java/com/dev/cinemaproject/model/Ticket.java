package com.dev.cinemaproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private MovieSession movieSession;
    @ManyToOne
    private User user;

    public Ticket() {

    }

    public Ticket(MovieSession movieSession, User user) {
        this.movieSession = movieSession;
        this.user = user;
    }
}
