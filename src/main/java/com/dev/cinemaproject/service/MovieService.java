package com.dev.cinemaproject.service;

import com.dev.cinemaproject.model.Movie;
import java.util.List;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
