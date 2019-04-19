package com.it.app.service.Impl;

import com.it.app.model.Client;
import com.it.app.repository.ClientRepository;
import com.it.app.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client addClient(Client client) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Client getByName(String name) {
        return null;
    }

    @Override
    public Client editClient(Client client) {
        return null;
    }

    @Override
    public List<Client> getAll() {
        return null;
    }
}
