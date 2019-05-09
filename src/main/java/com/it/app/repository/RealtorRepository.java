package com.it.app.repository;

import com.it.app.model.Realtor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealtorRepository extends JpaRepository<Realtor, Long> {

    boolean existsByPost(String post);

    Realtor findByPost(String post);

}
