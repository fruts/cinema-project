package com.dev.cinemaproject;

import com.dev.cinemaproject.exception.AuthenticationException;
import com.dev.cinemaproject.lib.Injector;
import com.dev.cinemaproject.model.CinemaHall;
import com.dev.cinemaproject.model.Movie;
import com.dev.cinemaproject.model.MovieSession;
import com.dev.cinemaproject.model.ShoppingCart;
import com.dev.cinemaproject.model.User;
import com.dev.cinemaproject.security.AuthenticationService;
import com.dev.cinemaproject.service.CinemaHallService;
import com.dev.cinemaproject.service.MovieService;
import com.dev.cinemaproject.service.MovieSessionService;
import com.dev.cinemaproject.service.OrderService;
import com.dev.cinemaproject.service.ShoppingCartService;
import com.dev.cinemaproject.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.apache.log4j.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);
    private static final Injector INJECTOR = Injector.getInstance("com.dev.cinemaproject");
    private static MovieService movieService
            = (MovieService) INJECTOR.getInstance(MovieService.class);
    private static MovieSessionService movieSessionService
            = (MovieSessionService) INJECTOR.getInstance(MovieSessionService.class);
    private static CinemaHallService cinemaHallService
            = (CinemaHallService) INJECTOR.getInstance(CinemaHallService.class);
    private static UserService userService
            = (UserService) INJECTOR.getInstance(UserService.class);
    private static AuthenticationService authenticationService
            = (AuthenticationService) INJECTOR.getInstance(AuthenticationService.class);
    private static ShoppingCartService cartService
            = (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
    private static OrderService orderService
            = (OrderService) INJECTOR.getInstance(OrderService.class);

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
        User user = authenticationService.register("mail", "pass");
        System.out.println(userService.findByEmail("mail"));
        try {
            authenticationService.login("mail", "pass");
            LOGGER.info("Authentication of user with id was succeed");
        } catch (AuthenticationException e) {
            LOGGER.error(e);
        }
        cartService.registerNewShoppingCart(user);
        ShoppingCart cart = cartService.getByUser(user);
        System.out.println("FIRST");
        System.out.println(cart.toString());
        cartService.addSession(firstSession, user);
        ShoppingCart cart2 = cartService.getByUser(user);
        System.out.println("SECOND");
        System.out.println(cart2.toString());
        orderService.completeOrder(cart.getTickets(), user);
        orderService.getOrderHistory(user).forEach(System.out::println);
    }
}
