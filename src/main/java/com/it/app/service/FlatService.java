package com.it.app.service;

import com.it.app.model.Flat;
import com.it.app.model.Plan;

import java.util.List;

public interface FlatService {

    Flat addFlat(Flat flat);

    void delete(long id);

    Flat getByName(String name);

    Flat editFlat(Flat flat);

    List<Flat> getAll();
}
