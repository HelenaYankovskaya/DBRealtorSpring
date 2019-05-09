package com.it.app.service;

import com.it.app.model.Flat;
import java.util.List;

public interface FlatService {

    List<Flat> findAll();

    List<Flat> findAllByNumberRooms(Long numberRooms);

    Flat findById(Long id);

    Flat save(Flat flat);

    Flat update(Flat flat);

    void delete(Flat flat);

    void deleteById(Long id);
}
