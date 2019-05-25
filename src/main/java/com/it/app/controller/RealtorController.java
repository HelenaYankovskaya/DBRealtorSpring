package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.request.RealtorRequestDto;
import com.it.app.dto.response.RealtorResponseDto;
import com.it.app.model.Realtor;
import com.it.app.model.User;
import com.it.app.service.RealtorService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A Controller for Realtor entity
 */
@RestController
@RequestMapping("/realtors")
public class RealtorController {
    private final Mapper mapper;

    private final RealtorService realtorService;

    private final LocalizedMessageSource localizedMessageSource;

    public RealtorController(Mapper mapper, RealtorService realtorService, LocalizedMessageSource localizedMessageSource) {
        this.mapper = mapper;
        this.realtorService = realtorService;
        this.localizedMessageSource = localizedMessageSource;
    }

    /**
     * Get All realtors
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<RealtorResponseDto>> getAll() {
        final List<Realtor> realtors = realtorService.findAll();
        final List<RealtorResponseDto> realtorResponseDtoList = realtors.stream()
                .map((realtor) -> mapper.map(realtor, RealtorResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(realtorResponseDtoList, HttpStatus.OK);
    }

    /**
     * Get realtors by Id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<RealtorResponseDto> getOne(@PathVariable Long id) {
        final RealtorResponseDto realtorResponseDto = mapper.map(realtorService.findById(id), RealtorResponseDto.class);
        return new ResponseEntity<>(realtorResponseDto, HttpStatus.OK);
    }

    /**
     * Save new Realtor
     *
     * @param realtorRequestDto
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RealtorResponseDto> save(@Valid @RequestBody RealtorRequestDto realtorRequestDto) {
        realtorRequestDto.setId(null);
        final RealtorResponseDto realtorResponseDto = mapper.map(realtorService.save(getRealtor(realtorRequestDto)), RealtorResponseDto.class);
        return new ResponseEntity<>(realtorResponseDto, HttpStatus.OK);
    }

    /**
     * Update realtor by Id
     *
     * @param realtorRequestDto
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RealtorResponseDto> update(@Valid @RequestBody RealtorRequestDto realtorRequestDto, @PathVariable Long id) {
        if (!Objects.equals(id, realtorRequestDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.realtor.unexpectedId", new Object[]{}));
        }
        final RealtorResponseDto realtorResponseDto = mapper.map(realtorService.update(getRealtor(realtorRequestDto)), RealtorResponseDto.class);
        return new ResponseEntity<>(realtorResponseDto, HttpStatus.OK);
    }

    /**
     * Delete realtor by Id
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        realtorService.deleteById(id);
    }

    /**
     * Completes information for output on request
     *
     * @param realtorRequestDto
     * @return
     */
    private Realtor getRealtor(RealtorRequestDto realtorRequestDto) {
        final Realtor realtor = mapper.map(realtorRequestDto, Realtor.class);
        final User user = new User();
        user.setId(realtorRequestDto.getUserId());
        realtor.setUser(user);
        return realtor;
    }
}
