package com.dev.cinemaproject;

import com.dev.cinemaproject.lib.Injector;
import com.dev.cinemaproject.model.Movie;
import com.dev.cinemaproject.service.MovieService;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinemaproject");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.getAll().forEach(System.out::println);

        Movie movie = new Movie();
        movie.setTitle("Brat 1");
        movie = movieService.add(movie);

        movieService.getAll().forEach(System.out::println);
    }
}
