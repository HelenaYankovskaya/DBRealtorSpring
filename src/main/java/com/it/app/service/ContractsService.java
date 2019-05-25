package com.it.app.service;

import com.it.app.model.Contracts;

import java.util.List;

/**
 * Service for Contracts entity
 */
public interface ContractsService {
    /**
     *  Returns ALL Сontracts
     */
    List<Contracts> findAll();

    /**
     * Returns ALL Сontracts
     * @param realtorId
     * @return
     */
    List<Contracts> findAllByRealtorId(Long realtorId);

    /**
     * Returns Contracts By Id
     * @param id
     * @return
     */
    Contracts findById(Long id);

    /**
     * Save new contract
     * @param contracts
     * @return
     */
    Contracts save(Contracts contracts);

    /**
     * Update  contract
     * @param contracts
     * @return
     */
    Contracts update(Contracts contracts);

    void delete(Contracts contracts);

    void deleteById(Long id);
}
