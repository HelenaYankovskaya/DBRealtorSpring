package com.it.app.repository;

import com.it.app.model.Walls;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WallsRepository extends JpaRepository<Walls, Long> {

    boolean existsByWalls(String walls);

    Walls findByWalls(String walls);

}
