package com.dev.cinemaproject.controllers;

import com.dev.cinemaproject.mapper.MovieMapper;
import com.dev.cinemaproject.model.Movie;
import com.dev.cinemaproject.model.dto.MovieRequestDto;
import com.dev.cinemaproject.model.dto.MovieResponseDto;
import com.dev.cinemaproject.service.MovieService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @GetMapping
    public List<MovieResponseDto> getMovies() {
        List<Movie> movies = movieService.getAll();
        return movies.stream()
                .map(movieMapper::convertToResponseDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        movieService.add(movieMapper.convertToMovie(movieRequestDto));
    }
}
