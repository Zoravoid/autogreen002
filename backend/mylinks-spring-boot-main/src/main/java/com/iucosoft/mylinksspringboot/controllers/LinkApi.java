package com.iucosoft.mylinksspringboot.controllers;

import com.iucosoft.mylinksspringboot.dto.MessageDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryDTO;
import com.iucosoft.mylinksspringboot.dto.link.LinkCreateDTO;
import com.iucosoft.mylinksspringboot.dto.link.LinkDTO;
import com.iucosoft.mylinksspringboot.dto.link.LinkUpdateDTO;
import com.iucosoft.mylinksspringboot.entities.Visibility;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequestMapping("/api/")
public interface LinkApi {

    @Operation(summary = "Get list of all links")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(value = "links", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<LinkDTO>> getLinks(final Pageable pageable);

    @Operation(summary = "Get list of all links by filter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(value = "links-filtered", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<LinkDTO>> getLinksFiltered(
            @Parameter(description = "Filter on the title") @RequestParam(name = "title", required = false) String title,
            @Parameter(description = "Filter on the url") @RequestParam(name = "url", required = false) String url,
            @Parameter(description = "Filter on the visibility") @RequestParam(name = "visibility", required = false) Visibility visibility,
            @Parameter(description = "Filter on the categories") @RequestParam(name = "categoryIds", required = false) List<Long> categoryIds,
            @Parameter(description = "Filter on the tags") @RequestParam(name = "tagTitles", required = false) List<String> tagTitles,
            @Parameter(hidden = true) final Pageable pageable);

    @Operation(summary = "Get a link by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(value = "links/{linkId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<LinkDTO> getLinkById(final @PathVariable(value = "linkId") Long linkId);

    @Operation(summary = "Create a new link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Link created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @PostMapping(value = "links", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<LinkDTO> createLink(@Valid @RequestBody LinkCreateDTO linkCreateDTO);

    @Operation(summary = "Update a link by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Link updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @PutMapping(value = "links/{linkId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<LinkUpdateDTO> updateLinkById(final @PathVariable(value = "linkId") Long linkId, @Valid @RequestBody LinkUpdateDTO linkUpdateDTO);

    @Operation(summary = "Delete a link by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Link deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @DeleteMapping(value = "links/{linkId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<MessageDTO> deleteLinkById(final @PathVariable(value = "linkId") Long linkId);

    @Operation(summary = "Add a link to a category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Link added to category successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @PutMapping(value = "categories/{categoryId}/add-link", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<CategoryDTO> addLinkToCategory(final @PathVariable Long categoryId, @Valid @RequestBody LinkDTO linkDTO) throws Exception;

//    @Operation(summary = "Edit links in a category")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Links in category edited successfully"),
//            @ApiResponse(responseCode = "400", description = "Bad request"),
//            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
//    })
//    @PutMapping(value = "categories/{categoryId}/links", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    ResponseEntity<CategoryDTO> editLinksInCategory(final @PathVariable Long categoryId, @Valid @RequestBody List<Long> linkIds);

    @Operation(summary = "Remove a link from a category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Link removed from category successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @DeleteMapping(value = "categories/{categoryId}/remove-link", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<MessageDTO> removeLinkFromCategory(final @PathVariable Long categoryId, @Valid @RequestBody LinkDTO linkDTO);

    @Operation(summary = "Get links owned by a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Links retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(value = "users/{userId}/links", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<LinkDTO>> getLinksByOwnerId(final @PathVariable(value = "userId") Long ownerId, final Pageable pageable);

    @Operation(summary = "GGet link by group id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(value = "groups/{groupId}/links", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<LinkDTO>> getLinksByGroupId(final @PathVariable(value = "groupId") Long groupId, final Pageable pageable);

    @Operation(summary = "Get links in a category by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Links retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(value = "categories/{categoryId}/links", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<LinkDTO>> getLinksByCategoryId(final @PathVariable Long categoryId, final Pageable pageable);

    @Operation(summary = "Get list of all visibility options")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(value = "links/visibility", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Visibility[]> getVisibilityOptions();

}
