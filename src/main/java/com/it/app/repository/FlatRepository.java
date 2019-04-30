package com.it.app.repository;

import com.it.app.model.Contracts;
import com.it.app.model.Flat;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FlatRepository extends JpaRepository<Flat, Long> {

    boolean existsByAdress(String adress);

    Flat findByAdress(String adress);



}
