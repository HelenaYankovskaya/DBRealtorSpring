package com.it.app.service.Impl;

import com.it.app.model.Contracts;
import com.it.app.repository.ContractsRepository;
import com.it.app.service.ContractsServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractsServiceImpl implements ContractsServise {
    @Autowired
    ContractsRepository contractsRepository;

    @Override
    public Contracts addContracts(Contracts contracts) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Contracts getByName(String name) {
        return null;
    }

    @Override
    public Contracts editContracts(Contracts contracts) {
        return null;
    }

    @Override
    public List<Contracts> getAll() {
        return null;
    }
}
