package com.it.app.service.Impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Flat;
import com.it.app.repository.FlatRepository;
import com.it.app.service.*;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class FlatServiceImpl implements FlatService {

    private final LocalizedMessageSource localizedMessageSource;

    private final FlatRepository flatRepository;

    public FlatServiceImpl(LocalizedMessageSource localizedMessageSource, FlatRepository flatRepository) {
        this.localizedMessageSource = localizedMessageSource;
        this.flatRepository = flatRepository;
    }

    @Override
    public List<Flat> findAll() {
        return flatRepository.findAll();
    }

    @Override
    public Flat findById(Long id) {
        return flatRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.user.notExist", new Object[]{})));
    }

    @Override
    public Flat save(Flat flat) {
        validate(flat.getId() != null, localizedMessageSource.getMessage("error.flat.notHaveId", new Object[]{}));
        validate(flatRepository.existsByAdress(flat.getAdress()), localizedMessageSource.getMessage("error.flat.adress.notUnique", new Object[]{}));
        return saveAndFlush(flat);
    }

    @Override
    public Flat update(Flat flat) {
        final Long id = flat.getId();
        validate(id == null, localizedMessageSource.getMessage("error.flat.haveId", new Object[]{}));
        final Flat duplicateFlat = flatRepository.findByAdress(flat.getAdress());
        final boolean isDuplicateExists = duplicateFlat != null && !Objects.equals(duplicateFlat.getId(), id);
        validate(isDuplicateExists, localizedMessageSource.getMessage("error.flat.adress.notUnique", new Object[]{}));
        return saveAndFlush(flat);
    }

    @Override
    public void delete(Flat flat) {
        validate(flat.getId() == null, localizedMessageSource.getMessage("error.flat.haveId", new Object[]{}));
        flatRepository.delete(flat);
    }

    @Override
    public void deleteById(Long id) {
        flatRepository.deleteById(id);
    }

    private Flat saveAndFlush(Flat flat) {
      //  validate(flat.getUserRole() == null || user.getUserRole().getId() == null, localizedMessageSource.getMessage("error.user.role.isNull", new Object[]{}));
       // user.setUserRole(userRoleService.findById(user.getUserRole().getId()));
        return flatRepository.saveAndFlush(flat);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
