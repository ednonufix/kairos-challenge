package com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.advice;

import java.time.LocalDateTime;

public record ErrorDetail(String message, String detailMessage, LocalDateTime time) {
}
