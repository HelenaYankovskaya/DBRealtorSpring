package com.it.app.service.Impl;

import com.it.app.model.Repair;
import com.it.app.repository.RepairRepository;
import com.it.app.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairServiceImpl implements RepairService {
    @Autowired
    RepairRepository repairRepository;

    @Override
    public Repair addRepair(Repair repair) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Repair getByName(String name) {
        return null;
    }

    @Override
    public Repair editRepair(Repair repair) {
        return null;
    }

    @Override
    public List<Repair> getAll() {
        return null;
    }
}
