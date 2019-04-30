package com.it.app.repository;

import com.it.app.model.Client;
import com.it.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository  extends JpaRepository<Client, Long> {

    boolean existsByPassport();

    Client findByPassport(String passport);


}

