package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.request.ContractsRequestDto;
import com.it.app.dto.response.ContractsResponseDto;
import com.it.app.model.Contracts;
import com.it.app.model.Flat;
import com.it.app.model.Realtor;
import com.it.app.service.ContractsService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A Controller for Contracts entity
 */
@RestController
@RequestMapping("/contracts")
public class ContractsController {
    private final Mapper mapper;

    private final ContractsService contractsService;

    private final LocalizedMessageSource localizedMessageSource;

    public ContractsController(Mapper mapper, ContractsService contractsService, LocalizedMessageSource localizedMessageSource) {
        this.mapper = mapper;
        this.contractsService = contractsService;
        this.localizedMessageSource = localizedMessageSource;
    }

    /**
     * Get All Contracts
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ContractsResponseDto>> getAll() {
        final List<Contracts> contract = contractsService.findAll();
        final List<ContractsResponseDto> contractsResponseDtoList = contract.stream()
                .map((contracts) -> mapper.map(contracts, ContractsResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(contractsResponseDtoList, HttpStatus.OK);
    }

    /**
     * Get Contracts by Id
     *
     * @param id
     * @return ContractsResponseDto
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ContractsResponseDto> getOne(@PathVariable Long id) {
        final ContractsResponseDto contractsResponseDto = mapper.map(contractsService.findById(id), ContractsResponseDto.class);
        return new ResponseEntity<>(contractsResponseDto, HttpStatus.OK);
    }

    /**
     * Get Contracts by Realtor Id
     *
     * @param realtorId
     * @return
     */
    @RequestMapping(value = "/realtor/{realtorId}", method = RequestMethod.GET)
    public ResponseEntity<List<ContractsResponseDto>> getAllByRealtorId(@PathVariable Long realtorId) {
        final List<Contracts> contracts = contractsService.findAllByRealtorId(realtorId);
        final List<ContractsResponseDto> contractsResponseDtoList = contracts.stream()
                .map((flat) -> mapper.map(flat, ContractsResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(contractsResponseDtoList, HttpStatus.OK);
    }

    /**
     * Save new contract
     *
     * @param contractRequestDto
     * @return contractsResponseDto
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ContractsResponseDto> save(@Valid @RequestBody ContractsRequestDto contractRequestDto) {
        contractRequestDto.setId(null);
        final ContractsResponseDto contractsResponseDto = mapper.map(contractsService.save(getContracts(contractRequestDto)), ContractsResponseDto.class);
        return new ResponseEntity<>(contractsResponseDto, HttpStatus.OK);
    }

    /**
     * Update contract by Id
     *
     * @param contractsRequestDto
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ContractsResponseDto> update(@Valid @RequestBody ContractsRequestDto contractsRequestDto, @PathVariable Long id) {
        if (!Objects.equals(id, contractsRequestDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.contracts.unexpectedId", new Object[]{}));
        }
        final ContractsResponseDto contractsResponseDto = mapper.map(contractsService.update(getContracts(contractsRequestDto)), ContractsResponseDto.class);
        return new ResponseEntity<>(contractsResponseDto, HttpStatus.OK);
    }

    /**
     * Delete contract by Id
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        contractsService.deleteById(id);
    }

    /**
     * Completes information for output on request
     *
     * @param contractsRequestDto
     * @return
     */
    private Contracts getContracts(ContractsRequestDto contractsRequestDto) {
        final Contracts contracts = mapper.map(contractsRequestDto, Contracts.class);
        final Realtor realtor = new Realtor();
        realtor.setId(contractsRequestDto.getRealtorId());
        contracts.setRealtor(realtor);
        final Flat flat = new Flat();
        flat.setId(contractsRequestDto.getFlatId());
        contracts.setFlat(flat);
        return contracts;
    }
}