package com.it.app.service.Impl;

import com.it.app.model.Flat;
import com.it.app.repository.FlatRepository;
import com.it.app.service.FlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlatServiceImpl implements FlatService {
    @Autowired
    FlatRepository flatRepository;

    @Override
    public Flat addFlat(Flat flat) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Flat getByName(String name) {
        return null;
    }

    @Override
    public Flat editFlat(Flat flat) {
        return null;
    }

    @Override
    public List<Flat> getAll() {
        return null;
    }
}
