package com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.ui;

import com.kairosds.challenge.pricesapi.application.query.dto.AuthenticationDetail;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestController
@RequestMapping("/v1")
public interface AuthorizedRestUI {

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/authorized")
	ResponseEntity<AuthenticationDetail> authenticationDetail(@RequestParam String code);

}
