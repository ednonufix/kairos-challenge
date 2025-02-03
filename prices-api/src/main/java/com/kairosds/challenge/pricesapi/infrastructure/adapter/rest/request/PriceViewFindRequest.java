package com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.request;

import com.kairosds.challenge.pricesapi.domain.dto.PriceViewFindQuery;
import com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.mapper.PriceViewRequestMapper;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

public record PriceViewFindRequest(
		@NotNull @Schema(example = "35455") Long productId,
		@NotNull @Schema(example = "1") Long brandId,
		@NotNull @Schema(example = "2020-06-16T21:00:00") LocalDateTime applicationDate) {
	public PriceViewFindQuery toQuery() {
		return PriceViewRequestMapper.INSTANCE.toQuery(this);
	}
}
