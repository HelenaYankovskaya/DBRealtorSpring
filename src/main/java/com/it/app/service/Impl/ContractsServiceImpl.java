package com.it.app.service.Impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Contracts;
import com.it.app.repository.ContractsRepository;
import com.it.app.repository.FlatRepository;
import com.it.app.repository.RealtorRepository;
import com.it.app.service.ContractsService;
import com.it.app.service.FlatService;
import com.it.app.service.RealtorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@Service
@Transactional
public class ContractsServiceImpl implements ContractsService {

    private final LocalizedMessageSource localizedMessageSource;

    private final ContractsRepository contractsRepository;

    private final RealtorService realtorService;

    public ContractsServiceImpl(LocalizedMessageSource localizedMessageSource, ContractsRepository contractsRepository, RealtorService realtorService) {
        this.localizedMessageSource = localizedMessageSource;
        this.contractsRepository = contractsRepository;
        this.realtorService = realtorService;
    }

    @Override
    public List<Contracts> findAll() {
        return contractsRepository.findAll();
    }

    @Override
    public Contracts findById(Long id) {
        return contractsRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.contract.notExist", new Object[]{})));
    }

    @Override
    public Contracts save(Contracts contracts) {
        validate(contracts.getId() != null, localizedMessageSource.getMessage("error.contract.notHaveId", new Object[]{}));
        validate(contractsRepository.existsByData(contracts.getData()), localizedMessageSource.getMessage("error.contract.data.notUnique", new Object[]{}));
        return saveAndFlush(contracts);
    }

    @Override
    public Contracts update(Contracts contracts) {
        final Long id = contracts.getId();
        validate(id == null, localizedMessageSource.getMessage("error.contracts.haveId", new Object[]{}));
        return saveAndFlush(contracts);
    }


    @Override
    public void delete(Contracts contracts) {
        validate(contracts.getId() == null, localizedMessageSource.getMessage("error.contracts.haveId", new Object[]{}));
        contractsRepository.delete(contracts);
    }

    @Override
    public void deleteById(Long id) {
        contractsRepository.deleteById(id);
    }

    private Contracts saveAndFlush(Contracts contracts) {
        validate(contracts.getRealtor() == null || contracts.getRealtor().getId() == null, localizedMessageSource.getMessage("error.contracts.realtor.isNull", new Object[]{}));
        contracts.setRealtor(realtorService.findById(contracts.getRealtor().getId()));
        return contractsRepository.saveAndFlush(contracts);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
