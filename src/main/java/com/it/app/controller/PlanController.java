package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.PlanDto;
import com.it.app.model.Plan;
import com.it.app.service.PlanService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A Controller for Plan entity
 */
@RestController
@RequestMapping("/plan")
public class PlanController {
    private final Mapper mapper;

    private final PlanService planService;

    private final LocalizedMessageSource localizedMessageSource;


    public PlanController(Mapper mapper, PlanService planService, LocalizedMessageSource localizedMessageSource) {
        this.mapper = mapper;
        this.planService = planService;
        this.localizedMessageSource = localizedMessageSource;
    }

    /**
     * get All plan
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PlanDto>> getAll() {
        final List<Plan> plans = planService.findAll();
        final List<PlanDto> planDtoList = plans.stream()
                .map((plan) -> mapper.map(plan, PlanDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(planDtoList, HttpStatus.OK);
    }

    /**
     * Get plan by id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PlanDto> getOne(@PathVariable Long id) {
        final PlanDto planDto = mapper.map(planService.findById(id), PlanDto.class);
        return new ResponseEntity<>(planDto, HttpStatus.OK);
    }

    /**
     * save new plan
     *
     * @param planDto
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PlanDto> save(@Valid @RequestBody PlanDto planDto) {
        planDto.setId(null);
        final PlanDto responseRoleDto = mapper.map(planService.save(mapper.map(planDto, Plan.class)), PlanDto.class);
        return new ResponseEntity<>(responseRoleDto, HttpStatus.OK);
    }

    /**
     * update  plan by id
     *
     * @param planDto
     * @param id
     * @return responseRoleDto
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PlanDto> update(@Valid @RequestBody PlanDto planDto, @PathVariable Long id) {
        if (!Objects.equals(id, planDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.role.unexpectedId", new Object[]{}));
        }
        final PlanDto responseRoleDto = mapper.map(planService.update(mapper.map(planDto, Plan.class)), PlanDto.class);
        return new ResponseEntity<>(responseRoleDto, HttpStatus.OK);
    }

    /**
     * Delete plan by id
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        planService.deleteById(id);
    }


}

