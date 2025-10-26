package com.iucosoft.mylinksspringboot.controllers;

import com.iucosoft.mylinksspringboot.dto.link.LinkDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Validated
@RequestMapping("/api/")
public interface DataApi {
    @Operation(summary = "Get list of all tags")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
    })
    @GetMapping(value = "data/tags", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<String>> getAllTags();
}
