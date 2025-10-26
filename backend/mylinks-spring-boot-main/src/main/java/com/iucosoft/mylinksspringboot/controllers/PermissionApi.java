package com.iucosoft.mylinksspringboot.controllers;


import com.iucosoft.mylinksspringboot.dto.MessageDTO;
import com.iucosoft.mylinksspringboot.dto.PermissionDTO;
import com.iucosoft.mylinksspringboot.entities.Permission;
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
public interface PermissionApi {

    @GetMapping(value = "permissions", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<PermissionDTO>> getListOfAllPermissions(final Pageable pageable);

    @PostMapping(value = "permissions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PermissionDTO> createPermission(@Valid @RequestBody PermissionDTO permissionDTO);

    @PutMapping(value = "permissions/{permissionId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PermissionDTO> updatePermissionById(final @PathVariable (value = "permissionId") Long permissionId, @Valid @RequestBody PermissionDTO permissionDTO);

    @GetMapping(value = "permissions/{permissionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PermissionDTO> getPermissionById(final @PathVariable Long permissionId);

    @DeleteMapping(value = "permissions/{permissionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<MessageDTO> deletePermissionById(final @PathVariable Long permissionId);




}
