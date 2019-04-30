package com.it.app.repository;

import com.it.app.model.Plan;
import com.it.app.model.Walls;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WallsRepository extends JpaRepository<Walls, Long> {

    boolean existsByName(String walls);

    Walls findByName(String walls);

}
