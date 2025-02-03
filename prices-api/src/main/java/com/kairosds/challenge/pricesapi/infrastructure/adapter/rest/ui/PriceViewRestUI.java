package com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.ui;

import com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.advice.ErrorDetail;
import com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.request.PriceViewFindRequest;
import com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.response.PriceViewFindResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Price-View-Controller")
@Validated
@RestController
@RequestMapping("/v1")
public interface PriceViewRestUI {

	@PreAuthorize("hasAuthority('ROLE_USERS')")
	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/price")
	@Operation(summary = "Find price by product, brand and application date")
	@ApiResponse(responseCode = "200", description = "Price found")
	@ApiResponse(responseCode = "400", description = "Invalid request", content = {@Content(schema = @Schema(implementation = ErrorDetail.class))})
	@ApiResponse(responseCode = "403", description = "Forbidden", content = {@Content(schema = @Schema(implementation = ErrorDetail.class))})
	@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema = @Schema(implementation = ErrorDetail.class))})
	ResponseEntity<PriceViewFindResponse> findPrice(@Valid @RequestBody PriceViewFindRequest request);
}
