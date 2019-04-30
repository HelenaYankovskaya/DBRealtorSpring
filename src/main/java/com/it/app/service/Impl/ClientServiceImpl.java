package com.it.app.service.Impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Client;
import com.it.app.repository.ClientRepository;
import com.it.app.service.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final LocalizedMessageSource localizedMessageSource;

    private final ClientRepository clientRepository;

    public ClientServiceImpl(LocalizedMessageSource localizedMessageSource, ClientRepository clientRepository) {
        this.localizedMessageSource = localizedMessageSource;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.client.notExist", new Object[]{})));
    }

    public Client save(Client client) {
        validate(client.getId() != null, localizedMessageSource.getMessage("error.client.notHaveId", new Object[]{}));
        validate(clientRepository.existsByPassport(), localizedMessageSource.getMessage("error.client.passport.notUnique", new Object[]{}));
        return saveAndFlush(client);
    }

    @Override
    public Client update(Client client) {
        final Long id = client.getId();
        validate(id == null, localizedMessageSource.getMessage("error.client.haveId", new Object[]{}));
        final Client duplicateClient = clientRepository.findByPassport(client.getPassport());
        final boolean isDuplicateExists = duplicateClient != null && !Objects.equals(duplicateClient.getId(), id);
        validate(isDuplicateExists, localizedMessageSource.getMessage("error.client.name.notUnique", new Object[]{}));
        return saveAndFlush(client);
    }

    @Override
    public void delete(Client client) {
        validate(client.getId() == null, localizedMessageSource.getMessage("error.client.haveId", new Object[]{}));
        clientRepository.delete(client);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    private Client saveAndFlush(Client client) {
     //   validate(client.getPassport() == null || client.getServices() == null, localizedMessageSource.getMessage("error.client.passport.isNull", new Object[]{}));
      // client.setPassport(ClientService.      (client.getUserRole().getId()));
        return clientRepository.saveAndFlush(client);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
