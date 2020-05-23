package com.dev.cinemaproject.service.impl;

import com.dev.cinemaproject.dao.CinemaHallDao;
import com.dev.cinemaproject.lib.Inject;
import com.dev.cinemaproject.lib.Service;
import com.dev.cinemaproject.model.CinemaHall;
import com.dev.cinemaproject.service.CinemaHallService;
import java.util.List;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {

    @Inject
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
