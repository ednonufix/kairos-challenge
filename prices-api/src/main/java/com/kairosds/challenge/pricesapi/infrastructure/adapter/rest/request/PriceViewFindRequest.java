package com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.request;

import com.kairosds.challenge.pricesapi.domain.dto.PriceViewFindQuery;
import com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.mapper.PriceViewRequestMapper;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record PriceViewFindRequest(@NotNull Long productId,
												@NotNull Long brandId,
												@NotNull LocalDateTime applicationDate) {
	public PriceViewFindQuery toQuery() {
		return PriceViewRequestMapper.INSTANCE.toQuery(this);
	}
}
