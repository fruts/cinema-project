package com.dev.cinemaproject.controllers;

import com.dev.cinemaproject.mapper.MovieSessionMapper;
import com.dev.cinemaproject.model.MovieSession;
import com.dev.cinemaproject.model.dto.MovieSessionRequestDto;
import com.dev.cinemaproject.model.dto.MovieSessionResponseDto;
import com.dev.cinemaproject.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    @Autowired
    public MovieSessionController(MovieSessionService movieSessionService, MovieSessionMapper movieSessionMapperMapper) {
        this.movieSessionService = movieSessionService;
        this.movieSessionMapper = movieSessionMapperMapper;
    }

    @PostMapping
    public void addMovieSession(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.add(movieSessionMapper.convertToMovieSession(movieSessionRequestDto));
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAvailableSessions(@RequestParam Long movieId,
                                                              @RequestParam
                                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<MovieSession> sessions = movieSessionService.findAvailableSessions(movieId, date);
        return sessions.stream()
                .map(movieSessionMapper::convertToResponseDto)
                .collect(Collectors.toList());
    }
}
