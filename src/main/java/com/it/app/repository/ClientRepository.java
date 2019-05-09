package com.it.app.repository;

import com.it.app.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByPassport(String passport);

     Client findByPassport(String passport);


}

