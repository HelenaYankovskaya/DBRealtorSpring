package com.it.app.service;

import com.it.app.model.Repair;

import java.util.List;

/**
 * Service for Repair entity
 */
public interface RepairService {

    List<Repair> findAll();

    Repair findById(Long id);

    Repair save(Repair repair);

    Repair update(Repair repair);

    void delete(Repair repair);

    void deleteById(Long id);
}
