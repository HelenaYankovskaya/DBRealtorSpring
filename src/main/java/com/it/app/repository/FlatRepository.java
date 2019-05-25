package com.it.app.repository;


import com.it.app.model.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface FlatRepository extends JpaRepository<Flat, Long> {

    boolean existsByAddress(String address);

    Flat findByAddress(String address);

    List<Flat> findAllByNumberRooms(Long numberRooms);

    List<Flat> findAllByValueLessThan(Long value);
}
