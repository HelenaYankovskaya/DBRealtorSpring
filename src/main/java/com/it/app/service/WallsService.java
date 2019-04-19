package com.it.app.service;

import com.it.app.model.Walls;

import java.util.List;

public interface WallsService {

    Walls addWalls(Walls walls);

    void delete(long id);

    Walls getByName(String name);

    Walls editWalls(Walls walls);

    List<Walls> getAll();
}
