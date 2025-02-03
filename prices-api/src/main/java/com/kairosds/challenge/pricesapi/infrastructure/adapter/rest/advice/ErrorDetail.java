package com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.advice;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

public record ErrorDetail(
		@Schema(example = "Error message") String message,
		@Schema(example = "Detailed error message") String detailMessage,
		@Schema(example = "2025-02-03T10:00:00") LocalDateTime time) {
}
