package com.dev.cinemaproject.dao;

import java.util.List;

public interface GenericDao<T> {
    T add(T element);

    List<T> getAll();

    T getById(Long id);
}
