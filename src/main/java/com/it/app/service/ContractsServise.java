package com.it.app.service;

import com.it.app.model.Contracts;
import java.util.List;

public interface ContractsServise {
    List<Contracts> findAll();

    Contracts findById(Long id);

    Contracts save(Contracts contracts);

    Contracts update(Contracts contracts);

    void delete(Contracts contracts);

    void deleteById(Long id);
}
