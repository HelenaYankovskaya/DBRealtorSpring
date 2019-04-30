package com.it.app.service;

import com.it.app.model.Realtor;

import java.util.List;

public interface RealtorService {

    List<Realtor> findAll();

    Realtor findById(Long id);

    Realtor save(Realtor realtor);

    Realtor update(Realtor realtor);

    void delete(Realtor realtor);

    void deleteById(Long id);
}
