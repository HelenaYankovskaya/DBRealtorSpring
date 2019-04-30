package com.it.app.repository;

import com.it.app.model.Client;
import com.it.app.model.Realtor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealtorRepository extends JpaRepository<Realtor, Long> {

    boolean existsByPost();

    Realtor findByPost(String post);
}
