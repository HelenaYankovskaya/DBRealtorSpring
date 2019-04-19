package com.it.app.service.Impl;

import com.it.app.model.Walls;
import com.it.app.repository.WallsRepository;
import com.it.app.service.WallsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WallsServiceImpl implements WallsService {
    @Autowired
    WallsRepository wallsRepository;

    @Override
    public Walls addWalls(Walls walls) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Walls getByName(String name) {
        return null;
    }

    @Override
    public Walls editWalls(Walls walls) {
        return null;
    }

    @Override
    public List<Walls> getAll() {
        return null;
    }
}
