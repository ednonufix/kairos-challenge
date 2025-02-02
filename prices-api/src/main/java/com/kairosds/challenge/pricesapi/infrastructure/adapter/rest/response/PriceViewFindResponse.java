package com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.response;

import java.time.ZonedDateTime;

public record PriceViewFindResponse(Long productId,
												Long brandId,
												String name,
												ZonedDateTime startDate,
												ZonedDateTime endDate,
												Double price) {
}
