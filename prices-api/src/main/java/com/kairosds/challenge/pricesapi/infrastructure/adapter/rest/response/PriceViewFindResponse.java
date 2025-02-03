package com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.response;

import java.time.ZonedDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

public record PriceViewFindResponse(
		@Schema(example = "35455") Long productId,
		@Schema(example = "1") Long brandId,
		@Schema(example = "Rate 4") String name,
		@Schema(example = "2020-06-15T16:00:00Z") ZonedDateTime startDate,
		@Schema(example = "2020-12-31T23:59:59Z") ZonedDateTime endDate,
		@Schema(example = "38.95") Double price) {
}
