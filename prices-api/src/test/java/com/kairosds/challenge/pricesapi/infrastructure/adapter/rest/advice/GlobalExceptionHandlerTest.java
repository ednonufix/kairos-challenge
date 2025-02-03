package com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.stream.Stream;

import org.instancio.Instancio;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

	private @InjectMocks GlobalExceptionHandler handler;

	@ParameterizedTest
	@MethodSource("provideExceptions")
	void handle(Exception ex, HttpStatus status) {

		var response = new ResponseEntity<ErrorDetail>(HttpStatus.OK);

		switch (ex) {
			case MethodArgumentNotValidException v -> {
				when(v.getMessage()).thenReturn("invalidJson");
				response = handler.handle(v);
			}
			case HttpMessageNotReadableException v -> response = handler.handle(v);
			case AuthorizationDeniedException v -> response = handler.handle(v);
			case Exception v -> response = handler.handle(v);
		}

		assertThat(response.getStatusCode()).isEqualTo(status);
	}

	private static Stream<Arguments> provideExceptions() {
		return Stream.of(
			Arguments.of(mock(MethodArgumentNotValidException.class), BAD_REQUEST),
			Arguments.of(Instancio.create(HttpMessageNotReadableException.class), BAD_REQUEST),
			Arguments.of(Instancio.create(AuthorizationDeniedException.class), FORBIDDEN),
			Arguments.of(Instancio.create(Exception.class), INTERNAL_SERVER_ERROR)
		);
	}
}