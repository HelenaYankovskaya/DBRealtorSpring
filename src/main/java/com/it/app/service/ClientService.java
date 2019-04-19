package com.it.app.service;

import com.it.app.model.Client;

import java.util.List;

public interface ClientService {
    Client addClient(Client client);

    void delete(long id);

    Client getByName(String name);

    Client editClient(Client client);

    List<Client> getAll();
}
