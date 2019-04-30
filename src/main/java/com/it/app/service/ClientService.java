package com.it.app.service;

import com.it.app.model.Client;
import com.it.app.model.User;

import java.util.List;

public interface ClientService {
    List<Client> findAll();

    Client findById(Long id);

    Client save(Client client);

    Client update(Client client);

    void delete(Client client);

    void deleteById(Long id);
}
