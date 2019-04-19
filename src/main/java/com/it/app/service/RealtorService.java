package com.it.app.service;

import com.it.app.model.Realtor;

import java.util.List;

public interface RealtorService {

    Realtor addRealtor(Realtor realtor);

    void delete(long id);

    Realtor getByName(String name);

    Realtor editRealtor(Realtor realtor);

    List<Realtor> getAll();
}
