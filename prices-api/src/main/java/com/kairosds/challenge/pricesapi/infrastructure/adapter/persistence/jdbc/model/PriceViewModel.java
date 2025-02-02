package com.kairosds.challenge.pricesapi.infrastructure.adapter.persistence.jdbc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.ZonedDateTime;

@Table("PRICE_VIEW")
public record PriceViewModel(@Id Long id,
										@Column("PRODUCT_ID") Long productId,
										@Column("BRAND_ID") Long brandId,
										@Column("NAME") String name,
										@Column("START_DATE") ZonedDateTime startDate,
										@Column("END_DATE") ZonedDateTime endDate,
										@Column("PRICE") Double price,
										@Column("PRIORITY") Integer priority) {
}
