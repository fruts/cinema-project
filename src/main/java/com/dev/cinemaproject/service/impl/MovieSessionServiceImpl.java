package com.dev.cinemaproject.service.impl;

import com.dev.cinemaproject.dao.MovieSessionDao;
import com.dev.cinemaproject.lib.Inject;
import com.dev.cinemaproject.lib.Service;
import com.dev.cinemaproject.model.MovieSession;
import com.dev.cinemaproject.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    private MovieSessionDao movieSessionDao;

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession add(MovieSession session) {
        return movieSessionDao.add(session);
    }
}
