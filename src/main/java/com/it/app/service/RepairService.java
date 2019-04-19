package com.it.app.service;

import com.it.app.model.Repair;

import java.util.List;

public interface RepairService {

    Repair addRepair(Repair repair);

    void delete(long id);

    Repair getByName(String name);

    Repair editRepair(Repair repair);

    List<Repair> getAll();
}
