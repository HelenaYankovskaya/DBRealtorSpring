package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.request.ClientRequestDto;
import com.it.app.dto.response.ClientResponseDto;
import com.it.app.model.Client;
import com.it.app.model.User;
import com.it.app.service.ClientService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A Controller for Client entity
 */
@RestController
@RequestMapping("/clients")
public class ClientController {
    private final Mapper mapper;

    private final ClientService clientService;

    private final LocalizedMessageSource localizedMessageSource;

    public ClientController(Mapper mapper, ClientService clientService, LocalizedMessageSource localizedMessageSource) {
        this.mapper = mapper;
        this.clientService = clientService;
        this.localizedMessageSource = localizedMessageSource;
    }

    /**
     * Get's ALL clients
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClientResponseDto>> getAll() {
        final List<Client> clients = clientService.findAll();
        final List<ClientResponseDto> clientResponseDtoList = clients.stream()
                .map((client) -> mapper.map(client, ClientResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(clientResponseDtoList, HttpStatus.OK);
    }

    /**
     * Get's one client by Id
     *
     * @param id
     * @return clientResponseDto
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClientResponseDto> getOne(@PathVariable Long id) {
        final ClientResponseDto clientResponseDto = mapper.map(clientService.findById(id), ClientResponseDto.class);
        return new ResponseEntity<>(clientResponseDto, HttpStatus.OK);
    }

    /**
     * Save new client
     *
     * @param clientRequestDto
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClientResponseDto> save(@Valid @RequestBody ClientRequestDto clientRequestDto) {
        clientRequestDto.setId(null);
        final ClientResponseDto clientResponseDto = mapper.map(clientService.save(getClient(clientRequestDto)), ClientResponseDto.class);
        return new ResponseEntity<>(clientResponseDto, HttpStatus.OK);
    }

    /**
     * Update Client with Id (param)
     *
     * @param clientRequestDto
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ClientResponseDto> update(@Valid @RequestBody ClientRequestDto clientRequestDto, @PathVariable Long id) {
        if (!Objects.equals(id, clientRequestDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.client.unexpectedId", new Object[]{}));
        }
        final ClientResponseDto clientResponseDto = mapper.map(clientService.update(getClient(clientRequestDto)), ClientResponseDto.class);
        return new ResponseEntity<>(clientResponseDto, HttpStatus.OK);
    }

    /**
     * Delete client  with id (param)
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        clientService.deleteById(id);
    }

    /**
     * Completes information for output on request
     *
     * @param clientRequestDto
     * @return
     */
    private Client getClient(ClientRequestDto clientRequestDto) {
        final Client client = mapper.map(clientRequestDto, Client.class);
        final User user = new User();
        user.setId(clientRequestDto.getUserId());
        client.setUser(user);
        return client;
    }
}
