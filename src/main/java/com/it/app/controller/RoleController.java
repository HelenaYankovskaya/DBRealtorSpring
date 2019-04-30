package com.it.app.controller;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.dto.UserRoleDto;
import com.it.app.model.UserRole;
import com.it.app.service.UserRoleService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final Mapper mapper;

    private final UserRoleService userRoleService;

    private final LocalizedMessageSource localizedMessageSource;

    public RoleController(Mapper mapper, UserRoleService userRoleService, LocalizedMessageSource localizedMessageSource) {
        this.mapper = mapper;
        this.userRoleService = userRoleService;
        this.localizedMessageSource = localizedMessageSource;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserRoleDto>> getAll() {
        final List<UserRole> userRoles = userRoleService.findAll();
        final List<UserRoleDto> userRoleDtoList = userRoles.stream()
                .map((userRole) -> mapper.map(userRole, UserRoleDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(userRoleDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserRoleDto> getOne(@PathVariable Long id) {
        final UserRoleDto userRoleDto = mapper.map(userRoleService.findById(id), UserRoleDto.class);
        return new ResponseEntity<>(userRoleDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserRoleDto> save(@Valid @RequestBody UserRoleDto userRoleDto) {
        userRoleDto.setId(null);
        final UserRoleDto responseRoleDto = mapper.map(userRoleService.save(mapper.map(userRoleDto, UserRole.class)), UserRoleDto.class);
        return new ResponseEntity<>(responseRoleDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserRoleDto> update(@Valid @RequestBody UserRoleDto userRoleDto, @PathVariable Long id) {
        if (!Objects.equals(id, userRoleDto.getId())) {
            throw new RuntimeException(localizedMessageSource.getMessage("controller.role.unexpectedId", new Object[]{}));
        }
        final UserRoleDto responseRoleDto = mapper.map(userRoleService.update(mapper.map(userRoleDto, UserRole.class)), UserRoleDto.class);
        return new ResponseEntity<>(responseRoleDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) { userRoleService.deleteById(id);
    }
}
