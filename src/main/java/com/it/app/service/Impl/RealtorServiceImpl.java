package com.it.app.service.Impl;

import com.it.app.model.Realtor;
import com.it.app.repository.RealtorRepository;
import com.it.app.service.RealtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealtorServiceImpl implements RealtorService {
    @Autowired
    RealtorRepository realtorRepository;

    @Override
    public Realtor addRealtor(Realtor realtor) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Realtor getByName(String name) {
        return null;
    }

    @Override
    public Realtor editRealtor(Realtor realtor) {
        return null;
    }

    @Override
    public List<Realtor> getAll() {
        return null;
    }
}
