package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.RepairDto;
import com.it.app.model.Repair;
import com.it.app.service.RepairService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A Controller for Repair entity
 */
@RestController
@RequestMapping("/repair")
public class RepairController {

    private final Mapper mapper;

    private final RepairService repairService;

    private final LocalizedMessageSource localizedMessageSource;


    public RepairController(Mapper mapper, RepairService repairService, LocalizedMessageSource localizedMessageSource) {
        this.mapper = mapper;
        this.repairService = repairService;
        this.localizedMessageSource = localizedMessageSource;
    }

    /**
     * Get's all repair
     *
     * @return repairDtoList
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<RepairDto>> getAll() {
        final List<Repair> repairs = repairService.findAll();
        final List<RepairDto> repairDtoList = repairs.stream()
                .map((repair) -> mapper.map(repair, RepairDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(repairDtoList, HttpStatus.OK);
    }

    /**
     * Get's repair by Id
     *
     * @param id
     * @return repairDto
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<RepairDto> getOne(@PathVariable Long id) {
        final RepairDto repairDto = mapper.map(repairService.findById(id), RepairDto.class);
        return new ResponseEntity<>(repairDto, HttpStatus.OK);
    }

    /**
     * Save new repair
     *
     * @param repairDto
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RepairDto> save(@Valid @RequestBody RepairDto repairDto) {
        repairDto.setId(null);
        final RepairDto responseRepairDto = mapper.map(repairService.save(mapper.map(repairDto, Repair.class)), RepairDto.class);
        return new ResponseEntity<>(responseRepairDto, HttpStatus.OK);
    }

    /**
     * Update's repair by Id
     *
     * @param repairDto
     * @param id
     * @return responseRepairDt
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RepairDto> update(@Valid @RequestBody RepairDto repairDto, @PathVariable Long id) {
        if (!Objects.equals(id, repairDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.role.unexpectedId", new Object[]{}));
        }
        final RepairDto responseRepairDto = mapper.map(repairService.update(mapper.map(repairDto, Repair.class)), RepairDto.class);
        return new ResponseEntity<>(responseRepairDto, HttpStatus.OK);
    }

    /**
     * Delete repair by Id
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        repairService.deleteById(id);
    }
}

