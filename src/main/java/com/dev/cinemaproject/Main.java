package com.dev.cinemaproject;

import com.dev.cinemaproject.lib.Injector;
import com.dev.cinemaproject.model.CinemaHall;
import com.dev.cinemaproject.model.Movie;
import com.dev.cinemaproject.model.MovieSession;
import com.dev.cinemaproject.service.CinemaHallService;
import com.dev.cinemaproject.service.MovieService;
import com.dev.cinemaproject.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    private static final Injector INJECTOR = Injector.getInstance("com.dev.cinemaproject");
    private static MovieService movieService
            = (MovieService) INJECTOR.getInstance(MovieService.class);
    private static MovieSessionService movieSessionService
            = (MovieSessionService) INJECTOR.getInstance(MovieSessionService.class);
    private static CinemaHallService cinemaHallService
            = (CinemaHallService) INJECTOR.getInstance(CinemaHallService.class);

    public static void main(String[] args) {
        movieService.getAll().forEach(System.out::println);

        Movie movie = new Movie();
        movie.setTitle("Shrek 2");
        movie.setDescription("Best film ever");
        movieService.add(movie);

        movieService.getAll().forEach(System.out::println);

        CinemaHall firstHall = new CinemaHall();
        firstHall.setCapacity(25);
        firstHall.setDescription("for loosers");
        cinemaHallService.add(firstHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession firstSession = new MovieSession();
        firstSession.setCinemaHall(firstHall);
        firstSession.setMovie(movie);
        firstSession.setShowTime(LocalDateTime
                .of(LocalDate.of(2020,5,21),
                        LocalTime.of(15,40)));
        LocalDate today = LocalDate.of(2020,5,21);
        movieSessionService.add(firstSession);
        movieSessionService.findAvailableSessions(movie.getId(), today)
                .forEach(System.out::println);
    }
}
