package com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.controller;

import com.kairosds.challenge.pricesapi.application.query.dto.AuthenticationDetail;
import com.kairosds.challenge.pricesapi.application.query.handle.AuthenticationQueryHandler;
import com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.ui.AuthorizedRestUI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AuthorizedRestController implements AuthorizedRestUI {

	private final AuthenticationQueryHandler authenticationQueryHandler;

	@Override
	public ResponseEntity<AuthenticationDetail> authenticationDetail(String code) {
		return ResponseEntity.ok(authenticationQueryHandler.authenticationDetail(code));
	}
}
