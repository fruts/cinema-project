package com.dev.cinemaproject.dao;

import com.dev.cinemaproject.model.Movie;
import java.util.List;

public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();

    Movie getById(Long id);
}
