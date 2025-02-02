package com.kairosds.challenge.pricesapi.domain.dto;

import java.time.ZonedDateTime;

public record PriceView(Long id,
								Long productId,
								Long brandId,
								String name,
								ZonedDateTime startDate,
								ZonedDateTime endDate,
								Double price,
								Integer priority) {
}
