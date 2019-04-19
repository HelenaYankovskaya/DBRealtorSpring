package com.it.app.service;

import com.it.app.model.Contracts;

import java.util.List;

public interface ContractsServise {
    Contracts addContracts(Contracts contracts);
    void delete(long id);
    Contracts getByName(String name);
    Contracts editContracts(Contracts contracts);
    List<Contracts> getAll();
}
