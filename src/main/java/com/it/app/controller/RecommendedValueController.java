package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.RecommendedValueDto;
import com.it.app.model.RecommendedValue;
import com.it.app.service.RecommendedValueService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A Controller for RecommendedValue entity
 */
@RestController
@RequestMapping("/recommendedValue")
public class RecommendedValueController {
    
    private final Mapper mapper;

    private final RecommendedValueService recommendedValueService;

    private final LocalizedMessageSource localizedMessageSource;

    public RecommendedValueController(Mapper mapper, RecommendedValueService recommendedValueService, LocalizedMessageSource localizedMessageSource) {
        this.mapper = mapper;
        this.recommendedValueService = recommendedValueService;
        this.localizedMessageSource = localizedMessageSource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<RecommendedValueDto>> getAll() {
        final List<RecommendedValue> recommendedValues = recommendedValueService.findAll();
        final List<RecommendedValueDto> recommendedValueDtoList = recommendedValues.stream()
                .map((recommendedValue) -> mapper.map(recommendedValue, RecommendedValueDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(recommendedValueDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<RecommendedValueDto> getOne(@PathVariable Long id) {
        final RecommendedValueDto recommendedValueDto = mapper.map(recommendedValueService.findById(id), RecommendedValueDto.class);
        return new ResponseEntity<>(recommendedValueDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RecommendedValueDto> save(@Valid @RequestBody RecommendedValueDto recommendedValueDto) {
        recommendedValueDto.setId(null);
        final RecommendedValueDto responseRoleDto = mapper.map(recommendedValueService.save(mapper.map(recommendedValueDto, RecommendedValue.class)), RecommendedValueDto.class);
        return new ResponseEntity<>(responseRoleDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RecommendedValueDto> update(@Valid @RequestBody RecommendedValueDto recommendedValueDto, @PathVariable Long id) {
        if (!Objects.equals(id, recommendedValueDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.role.unexpectedId", new Object[]{}));
        }
        final RecommendedValueDto responseRoleDto = mapper.map(recommendedValueService.update(mapper.map(recommendedValueDto, RecommendedValue.class)), RecommendedValueDto.class);
        return new ResponseEntity<>(responseRoleDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        recommendedValueService.deleteById(id);
    }
}
