package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.request.FlatRequestDto;
import com.it.app.dto.response.FlatResponseDto;
import com.it.app.model.Flat;
import com.it.app.model.Plan;
import com.it.app.model.Repair;
import com.it.app.model.Walls;
import com.it.app.service.FlatService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A Controller for Flat entity
 */
@RestController
@RequestMapping("/flats")
public class FlatController {
    private final Mapper mapper;

    private final FlatService flatService;

    private final LocalizedMessageSource localizedMessageSource;

    public FlatController(Mapper mapper, FlatService flatService, LocalizedMessageSource localizedMessageSource) {
        this.mapper = mapper;
        this.flatService = flatService;
        this.localizedMessageSource = localizedMessageSource;
    }

    /**
     * Get All Flats
     *
     * @return flatResponseDtoList
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<FlatResponseDto>> getAll() {
        final List<Flat> flats = flatService.findAll();
        final List<FlatResponseDto> flatResponseDtoList = flats.stream()
                .map((flat) -> mapper.map(flat, FlatResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(flatResponseDtoList, HttpStatus.OK);
    }

    /**
     * Get Flat by Id
     *
     * @param id
     * @return flatResponseDto
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<FlatResponseDto> getOne(@PathVariable Long id) {
        final FlatResponseDto flatResponseDto = mapper.map(flatService.findById(id), FlatResponseDto.class);
        return new ResponseEntity<>(flatResponseDto, HttpStatus.OK);
    }

    /**
     * Get Flats  by number of rooms
     *
     * @param numberRooms
     * @return flatResponseDtoList
     */
    @RequestMapping(value = "/rooms/{numberRooms}", method = RequestMethod.GET)
    public ResponseEntity<List<FlatResponseDto>> getAllByNumberRooms(@PathVariable Long numberRooms) {
        final List<Flat> flats = flatService.findAllByNumberRooms(numberRooms);
        final List<FlatResponseDto> flatResponseDtoList = flats.stream()
                .map((flat) -> mapper.map(flat, FlatResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(flatResponseDtoList, HttpStatus.OK);
    }

    /**
     * Get flats, wich value is less then param
     *
     * @param value
     * @return flatResponseDtoList
     */
    @RequestMapping(value = "/value/{value}", method = RequestMethod.GET)
    public ResponseEntity<List<FlatResponseDto>> getAllByValueLessThan(@PathVariable Long value) {
        final List<Flat> flats = flatService.findAllByValueLessThan(value);
        final List<FlatResponseDto> flatResponseDtoList = flats.stream()
                .map((flat) -> mapper.map(flat, FlatResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(flatResponseDtoList, HttpStatus.OK);
    }

    /**
     * Save new flat
     *
     * @param flatRequestDto
     * @return flatResponseDto
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FlatResponseDto> save(@Valid @RequestBody FlatRequestDto flatRequestDto) {
        flatRequestDto.setId(null);
        final FlatResponseDto flatResponseDto = mapper.map(flatService.save(getFlat(flatRequestDto)), FlatResponseDto.class);
        return new ResponseEntity<>(flatResponseDto, HttpStatus.OK);
    }

    /**
     * Update flat by id
     *
     * @param flatRequestDto
     * @param id
     * @return flatResponseDto
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FlatResponseDto> update(@Valid @RequestBody FlatRequestDto flatRequestDto, @PathVariable Long id) {
        if (!Objects.equals(id, flatRequestDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.flat.unexpectedId", new Object[]{}));
        }
        final FlatResponseDto flatResponseDto = mapper.map(flatService.update(getFlat(flatRequestDto)), FlatResponseDto.class);
        return new ResponseEntity<>(flatResponseDto, HttpStatus.OK);
    }

    /**
     * Delete flat by id
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        flatService.deleteById(id);
    }

    /**
     * Completes information for output on request
     *
     * @param flatRequestDto
     * @return
     */
    private Flat getFlat(FlatRequestDto flatRequestDto) {
        final Flat flat = mapper.map(flatRequestDto, Flat.class);
        final Plan plan = new Plan();
        plan.setId(flatRequestDto.getPlanId());
        flat.setPlan(plan);
        final Repair repair = new Repair();
        repair.setId(flatRequestDto.getRepairId());
        flat.setRepair(repair);
        final Walls walls = new Walls();
        walls.setId(flatRequestDto.getWallsId());
        flat.setWalls(walls);
        return flat;
    }
}
