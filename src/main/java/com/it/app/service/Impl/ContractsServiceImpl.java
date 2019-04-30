package com.it.app.service.Impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Contracts;
import com.it.app.repository.ContractsRepository;
import com.it.app.service.ContractsServise;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ContractsServiceImpl implements ContractsServise {

    private final LocalizedMessageSource localizedMessageSource;

    private final ContractsRepository contractsRepository;

    public ContractsServiceImpl(LocalizedMessageSource localizedMessageSource, ContractsRepository contractsRepository) {
        this.localizedMessageSource = localizedMessageSource;
        this.contractsRepository = contractsRepository;
    }

    @Override
    public List<Contracts> findAll() {
        return contractsRepository.findAll();
    }

    @Override
    public Contracts findById(Long id) {
        return contractsRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.user.notExist", new Object[]{})));
    }

    @Override
    public Contracts save(Contracts contracts) {
        validate(contracts.getId() != null, localizedMessageSource.getMessage("error.contract.notHaveId", new Object[]{}));
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
        validate(contracts.getId() == null, localizedMessageSource.getMessage("error.user.haveId", new Object[]{}));
        contractsRepository.delete(contracts);
    }

    @Override
    public void deleteById(Long id) {
        contractsRepository.deleteById(id);
    }

    private Contracts saveAndFlush(Contracts contracts) {
       // validate(contracts.get() == null || user.getUserRole().getId() == null, localizedMessageSource.getMessage("error.user.role.isNull", new Object[]{}));
        //user.setUserRole(userRoleService.findById(user.getUserRole().getId()));
        return contractsRepository.saveAndFlush(contracts);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
