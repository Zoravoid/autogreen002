package com.iucosoft.mylinksspringboot.controllers;

import com.iucosoft.mylinksspringboot.dto.MessageDTO;
import com.iucosoft.mylinksspringboot.dto.group.GroupCreateDTO;
import com.iucosoft.mylinksspringboot.dto.user.UserDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryDTO;
import com.iucosoft.mylinksspringboot.dto.group.GroupDTO;
import com.iucosoft.mylinksspringboot.dto.group.GroupUpdateDTO;
import com.iucosoft.mylinksspringboot.dto.link.LinkDTO;
import com.iucosoft.mylinksspringboot.util.AuthConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Validated
@RequestMapping("/api/")
public interface GroupApi {

    @Operation(summary = "Get list of all groups")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(value = "groups", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<GroupDTO>> getListOfAllGroups(final Pageable pageable);

    @Operation(summary = "Get group by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(value = "groups/{groupId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<GroupDTO> getGroupById(final @PathVariable Long groupId);

    @Operation(summary = "Create a group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Group created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @PostMapping(value = "groups", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<GroupDTO> createGroup(@Valid @RequestBody GroupCreateDTO groupCreateDTO, @RequestHeader(AuthConstants.AUTHORIZATION) String authorizationHeader);

    @Operation(summary = "Update group by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Group updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @PutMapping(value = "groups/{groupId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<GroupDTO> updateGroupById(final @PathVariable(value = "groupId") Long groupId, @Valid @RequestBody GroupUpdateDTO groupUpdateDTO);

    @Operation(summary = "Delete group by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Group deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @DeleteMapping(value = "groups/{groupId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<MessageDTO> deleteGroupById(final @PathVariable Long groupId);

    @Operation(summary = "Get groups created by a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Groups retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(value = "users/{userId}/created-groups", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<GroupDTO>> getGroupsByOwnerId(final @PathVariable Long userId, Pageable pageable);

    @Operation(summary = "Get groups where the user is a member")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Groups retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(value = "users/{userId}/groups", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<GroupDTO>> getGroupsByMemberId(final @PathVariable Long userId, Pageable pageable);

    @Operation(summary = "Get the group of a link by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Group retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(value = "links/{linkId}/group", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<GroupDTO> getGroupByLinkId(final @PathVariable(value = "linkId") Long linkId);

    @Operation(summary = "Get groups in a category by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Groups retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(value = "categories/{categoryId}/groups", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<GroupDTO>> getGroupsByCategoryId(final @PathVariable Long categoryId, final Pageable pageable);

}
