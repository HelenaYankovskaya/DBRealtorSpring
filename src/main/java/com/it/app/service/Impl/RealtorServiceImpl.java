package com.it.app.service.Impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Realtor;
import com.it.app.repository.RealtorRepository;
import com.it.app.service.RealtorService;
import com.it.app.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
/**
 * Class, which implements methods of RealtorService interface
 */
@Service
@Transactional
public class RealtorServiceImpl implements RealtorService {

    private final LocalizedMessageSource localizedMessageSource;

    private final UserService userService;

    private final RealtorRepository realtorRepository;

    public RealtorServiceImpl(LocalizedMessageSource localizedMessageSource, UserService userService, RealtorRepository realtorRepository) {
        this.localizedMessageSource = localizedMessageSource;
        this.userService = userService;
        this.realtorRepository = realtorRepository;
    }

    @Override
    public List<Realtor> findAll() {
        return  realtorRepository.findAll();
    }

    @Override
    public Realtor findById(Long id) {
        return realtorRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.realtor.notExist", new Object[]{})));
    }

    public Realtor save(Realtor realtor) {
        validate(realtor.getId() != null, localizedMessageSource.getMessage("error.realtor.notHaveId", new Object[]{}));
        validate(realtorRepository.existsByPost(realtor.getPost()), localizedMessageSource.getMessage("error.realtor.post.notUnique", new Object[]{}));
        return saveAndFlush(realtor);
    }

    @Override
    public Realtor update(Realtor realtor) {
        final Long id = realtor.getId();
        validate(id == null, localizedMessageSource.getMessage("error.realtor.haveId", new Object[]{}));
        final Realtor duplicateRealtor = realtorRepository.findByPost(realtor.getPost());
        final boolean isDuplicateExists = duplicateRealtor != null && !Objects.equals(duplicateRealtor.getId(), id );
        validate(isDuplicateExists, localizedMessageSource.getMessage("error.realtor.post.notUnique", new Object[]{}));
        return saveAndFlush(realtor);
    }

    @Override
    public void delete(Realtor realtor) {
        validate(realtor.getId() == null, localizedMessageSource.getMessage("error.realtor.haveId", new Object[]{}));
        realtorRepository.delete(realtor);
    }

    @Override
    public void deleteById(Long id) {
        realtorRepository.deleteById(id);
    }

    private Realtor saveAndFlush(Realtor realtor) {
        validate(realtor.getUser() == null || realtor.getUser().getId() == null, localizedMessageSource.getMessage("error.realtor.user.isNull", new Object[]{}));
        realtor.setUser(userService.findById(realtor.getUser().getId()));
        return realtorRepository.saveAndFlush(realtor);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
