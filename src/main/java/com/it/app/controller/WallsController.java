package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.WallsDto;
import com.it.app.model.Walls;
import com.it.app.service.WallsService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A Controller for Walls entity
 */
@RestController
@RequestMapping("/walls")
public class WallsController {

    private final Mapper mapper;

    private final WallsService wallsService;

    private final LocalizedMessageSource localizedMessageSource;


    public WallsController(Mapper mapper, WallsService wallsService, LocalizedMessageSource localizedMessageSource) {
        this.mapper = mapper;
        this.wallsService = wallsService;
        this.localizedMessageSource = localizedMessageSource;
    }

    /**
     * Get's all walls
     *
     * @return wallsDtoList
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<WallsDto>> getAll() {
        final List<Walls> walls1 = wallsService.findAll();
        final List<WallsDto> wallsDtoList = walls1.stream()
                .map((walls) -> mapper.map(walls, WallsDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(wallsDtoList, HttpStatus.OK);
    }

    /**
     * Get's walls by Id
     *
     * @param id
     * @return wallsDto
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<WallsDto> getOne(@PathVariable Long id) {
        final WallsDto wallsDto = mapper.map(wallsService.findById(id), WallsDto.class);
        return new ResponseEntity<>(wallsDto, HttpStatus.OK);
    }

    /**
     * Save's new walls
     *
     * @param wallsDto
     * @return responseWallsDto
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<WallsDto> save(@Valid @RequestBody WallsDto wallsDto) {
        wallsDto.setId(null);
        final WallsDto responseWallsDto = mapper.map(wallsService.save(mapper.map(wallsDto, Walls.class)), WallsDto.class);
        return new ResponseEntity<>(responseWallsDto, HttpStatus.OK);
    }

    /**
     * Update's walls by Id
     *
     * @param wallsDto
     * @param id
     * @return responseWallsDto
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<WallsDto> update(@Valid @RequestBody WallsDto wallsDto, @PathVariable Long id) {
        if (!Objects.equals(id, wallsDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.role.unexpectedId", new Object[]{}));
        }
        final WallsDto responseWallsDto = mapper.map(wallsService.update(mapper.map(wallsDto, Walls.class)), WallsDto.class);
        return new ResponseEntity<>(responseWallsDto, HttpStatus.OK);
    }

    /**
     * Delete's walls by id
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        wallsService.deleteById(id);
    }
}

