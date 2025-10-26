package com.iucosoft.mylinksspringboot.controllers;

import com.iucosoft.mylinksspringboot.dto.MessageDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryCreateDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryDTO;
import com.iucosoft.mylinksspringboot.dto.category.CategoryUpdateDTO;
import com.iucosoft.mylinksspringboot.dto.group.GroupDTO;
import com.iucosoft.mylinksspringboot.util.AuthConstants;
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
public interface CategoryApi {

    @Operation(summary = "Get list of all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(value = "categories", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<CategoryDTO>> getListOfAllCategories(final Pageable pageable);

    @Operation(summary = "Get list of all categories by filter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(value = "categories-filtered", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<CategoryDTO>> getListOfCategoriesFiltered(
            @Parameter(description = "Filter on the title") @RequestParam(name = "title", required = false) String title,
            @Parameter(description = "Filter on the description") @RequestParam(name = "description", required = false) String description,
            @Parameter(hidden = true) final Pageable pageable);

    @Operation(summary = "Get category by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(value = "categories/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<CategoryDTO> getCategoryById(final @PathVariable Long categoryId);

    @Operation(summary = "Create a category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @PostMapping(value = "categories", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryCreateDTO categoryCreateDTO, @RequestHeader(AuthConstants.AUTHORIZATION) String authorizationHeader);


    @Operation(summary = "Update a category by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @PutMapping(value = "categories/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<CategoryDTO> updateCategoryById(final @PathVariable(value = "categoryId") Long categoryId, @Valid @RequestBody CategoryUpdateDTO categoryUpdateDTO);

    @Operation(summary = "Delete a category by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @DeleteMapping(value = "categories/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<MessageDTO> deleteCategoryById(final @PathVariable Long categoryId);

    @Operation(summary = "Get categories owned by a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(value = "users/{userId}/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<CategoryDTO>> getCategoriesByOwnerId(final @PathVariable Long userId, Pageable pageable);

    @Operation(summary = "Get categories of a link by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(value = "links/{linkId}/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<CategoryDTO>> getCategoriesByLinkId(final @PathVariable(value = "linkId") Long linkId, final Pageable pageable);

    @Operation(summary = "Get members by group id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(value = "groups/{groupId}/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Page<CategoryDTO>> getCategoriesByGroupId(final @PathVariable(value = "groupId") Long groupId, final Pageable pageable);

    @Operation(summary = "Add category to group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category added successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @PostMapping(value = "groups/{groupId}/add-category", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<GroupDTO> addCategoryToGroup(final @PathVariable Long groupId, @Valid @RequestBody CategoryDTO categoryDTO);

    @Operation(summary = "Edit categories in group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories in group edited successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @PutMapping(value = "groups/{groupId}/categories", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<GroupDTO> editCategoriesInGroup(final @PathVariable Long groupId, @Valid @RequestBody List<Long> categoryIds);

    @Operation(summary = "Remove category from group")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category removed successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @PutMapping(value = "categories/{groupId}/remove-category", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<MessageDTO> removeCategoryFromGroup(final @PathVariable Long groupId, final CategoryDTO categoryDTO);

}
