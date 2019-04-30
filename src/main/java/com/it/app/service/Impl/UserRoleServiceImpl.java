package com.it.app.service.Impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.UserRole;
import com.it.app.repository.UserRoleRepository;
import com.it.app.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

        private final LocalizedMessageSource localizedMessageSource;

        private final UserRoleRepository userRoleRepository;

        public UserRoleServiceImpl(UserRoleRepository userRoleRepository, LocalizedMessageSource localizedMessageSource) {
            this.userRoleRepository = userRoleRepository;
            this.localizedMessageSource = localizedMessageSource;
        }

        @Override
        public List<UserRole> findAll() {
            return userRoleRepository.findAll();
        }

        @Override
        public UserRole findById(Long id) {
            return userRoleRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.role.notExist", new Object[]{})));
        }

        @Override
        public UserRole save(UserRole userRole) {
            validate(userRole.getId() != null, localizedMessageSource.getMessage("error.role.notHaveId", new Object[]{}));
            validate(userRoleRepository.existsByName(userRole.getRole()), localizedMessageSource.getMessage("error.role.name.notUnique", new Object[]{}));
            return userRoleRepository.saveAndFlush(userRole);
        }

        @Override
        public UserRole update(UserRole userRole) {
            final Long roleId = userRole.getId();
            validate(roleId == null, localizedMessageSource.getMessage("error.role.haveId", new Object[]{}));
            final UserRole duplicateRole = userRoleRepository.findByName(userRole.getRole());
            final boolean isDuplicateExists = duplicateRole != null && !Objects.equals(duplicateRole.getId(), roleId);
            validate(isDuplicateExists, localizedMessageSource.getMessage("error.role.name.notUnique", new Object[]{}));
            return userRoleRepository.saveAndFlush(userRole);
        }

        @Override
        public void delete(UserRole userRole) {
            validate(userRole.getId() == null, localizedMessageSource.getMessage("error.role.haveId", new Object[]{}));
            userRoleRepository.delete(userRole);
        }

        @Override
        public void deleteById(Long id) {
            userRoleRepository.deleteById(id);
        }

        private void validate(boolean expression, String errorMessage) {
            if (expression) {
                throw new RuntimeException(errorMessage);
            }
        }
    }

