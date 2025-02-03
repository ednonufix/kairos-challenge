package com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetail> handle(MethodArgumentNotValidException ex) {
		var errordetail = new ErrorDetail("Invalid json", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errordetail);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorDetail> handle(HttpMessageNotReadableException ex) {
		var errordetail = new ErrorDetail("Malformed json", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errordetail);
	}

	@ExceptionHandler(AuthorizationDeniedException.class)
	public ResponseEntity<ErrorDetail> handle(AuthorizationDeniedException ex) {
		var errordetail = new ErrorDetail("Authorization denied", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errordetail);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetail> handle(Exception ex) {
		var errordetail = new ErrorDetail("Error", ex.getMessage(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errordetail);
	}

}
