package com.iucosoft.mylinksspringboot.controllers;

import com.iucosoft.mylinksspringboot.dto.MessageDTO;
import com.iucosoft.mylinksspringboot.dto.role.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequestMapping("/api/")
public interface RoleApi {

    @GetMapping(value = "roles", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<RoleDTOList>> getListOfAllRoles(final Pageable pageable);

    @PostMapping(value = "roles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<RoleDTOCreate> createRole(@Valid @RequestBody RoleDTOCreate roleDTOCreate);

    @PutMapping(value = "roles/{roleId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<RoleDTOUpdate> updateRoleById(final @PathVariable Long roleId, @Valid @RequestBody RoleDTOUpdate roleDTOUpdate);

    @GetMapping(value = "roles/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<RoleDTO> getRoleById(final @PathVariable Long roleId);

    @DeleteMapping(value = "roles/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<MessageDTO> deleteRoleById(final @PathVariable Long roleId);

}
