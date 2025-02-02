package com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.ui;

import com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.request.PriceViewFindRequest;
import com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.response.PriceViewFindResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/v1")
public interface PriceViewRestUI {

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/price")
	ResponseEntity<PriceViewFindResponse> findPrice(@Valid @RequestBody PriceViewFindRequest request);
}
