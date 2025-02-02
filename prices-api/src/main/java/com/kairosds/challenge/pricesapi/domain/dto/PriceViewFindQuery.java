package com.kairosds.challenge.pricesapi.domain.dto;

import java.time.LocalDateTime;

public record PriceViewFindQuery(Long productId, Long brandId, LocalDateTime applicationDate) {
}
