package com.kairosds.challenge.pricesapi.infrastructure.security;

import com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.advice.ErrorDetail;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;

import com.fasterxml.jackson.databind.ObjectMapper;

@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
		throws IOException {
		if (authException instanceof OAuth2AuthenticationException oauth) {
			var error = oauth.getError();
			var errordetail = new ErrorDetail(error.getErrorCode(), error.getDescription(), LocalDateTime.now());
			response.reset();
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType("application/json");
			response.getWriter().write(objectMapper.writeValueAsString(errordetail));
		}
	}
}
