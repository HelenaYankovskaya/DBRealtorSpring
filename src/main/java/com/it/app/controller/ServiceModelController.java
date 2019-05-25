package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.request.ServiceModelRequestDto;
import com.it.app.dto.response.ServiceModelResponseDto;
import com.it.app.model.ServiceModel;
import com.it.app.service.ServiceModelService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A Controller for ServiceModel entity
 */
@RestController
@RequestMapping("/services")
public class ServiceModelController {

    private final Mapper mapper;

    private final ServiceModelService serviceModelService;

    private final LocalizedMessageSource localizedMessageSource;


    public ServiceModelController(Mapper mapper, ServiceModelService serviceModelService, LocalizedMessageSource localizedMessageSource) {
        this.mapper = mapper;
        this.serviceModelService = serviceModelService;
        this.localizedMessageSource = localizedMessageSource;
    }

    /**
     * Get All services
     *
     * @return serviceModelResponseDtoList
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ServiceModelResponseDto>> getAll() {
        final List<ServiceModel> serviceModels = serviceModelService.findAll();
        final List<ServiceModelResponseDto> serviceModelResponseDtoList = serviceModels.stream()
                .map((serviceModel) -> mapper.map(serviceModel, ServiceModelResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(serviceModelResponseDtoList, HttpStatus.OK);
    }

    /**
     * Get's service by Id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ServiceModelResponseDto> getOne(@PathVariable Long id) {
        final ServiceModelResponseDto serviceModelResponseDto = mapper.map(serviceModelService.findById(id), ServiceModelResponseDto.class);
        return new ResponseEntity<>(serviceModelResponseDto, HttpStatus.OK);
    }

    /**
     * Save's new service
     *
     * @param serviceModelRequestDto
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ServiceModelResponseDto> save(@Valid @RequestBody ServiceModelRequestDto serviceModelRequestDto) {
        serviceModelRequestDto.setId(null);
        final ServiceModelResponseDto serviceModelResponseDto = mapper.map(serviceModelService.save(getServiceModel(serviceModelRequestDto)), ServiceModelResponseDto.class);
        return new ResponseEntity<>(serviceModelResponseDto, HttpStatus.OK);
    }

    /**
     * Update's services by Id
     *
     * @param serviceModelRequestDto
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ServiceModelResponseDto> update(@Valid @RequestBody ServiceModelRequestDto serviceModelRequestDto, @PathVariable Long id) {
        if (!Objects.equals(id, serviceModelRequestDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.serviceModel.unexpectedId", new Object[]{}));
        }
        final ServiceModelResponseDto serviceModelResponseDto = mapper.map(serviceModelService.update(getServiceModel(serviceModelRequestDto)), ServiceModelResponseDto.class);
        return new ResponseEntity<>(serviceModelResponseDto, HttpStatus.OK);
    }

    /**
     * Delete's service by Id
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        serviceModelService.deleteById(id);
    }

    /**
     * Completes information for output on request
     *
     * @param serviceModelRequestDto
     * @return
     */
    private ServiceModel getServiceModel(ServiceModelRequestDto serviceModelRequestDto) {
        final ServiceModel serviceModel = mapper.map(serviceModelRequestDto, ServiceModel.class);
        return serviceModel;

    }
}