package com.it.app.service;

import com.it.app.model.Walls;

import java.util.List;

/**
 * Service for Walls entity
 */
public interface WallsService {

    List<Walls> findAll();

    Walls findById(Long id);

    Walls save(Walls walls);

    Walls update(Walls walls);

    void delete(Walls walls);

    void deleteById(Long id);
}
