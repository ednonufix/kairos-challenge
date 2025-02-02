package com.kairosds.challenge.pricesapi.infrastructure.adapter.persistence.jdbc.repository;

import com.kairosds.challenge.pricesapi.domain.dto.PriceViewFindQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@DataJdbcTest
@Import(PriceViewJdbcRepository.class)
class PriceViewJdbcRepositoryTest {

	private @Autowired PriceViewJdbcRepository repository;

	@Test
	void findPrices() {
		var query = new PriceViewFindQuery(35455L, 1L, LocalDateTime.of(2020, 6, 14, 10, 0));
		var prices = repository.findPrice(query);
		assertThat(prices).hasSize(1)
			.extracting("productId", "brandId", "name", "startDate", "endDate", "price", "priority")
			.containsExactly(tuple(35455L, 1L, "Rate 1", ZonedDateTime.of(LocalDateTime.of(2020, 6, 14, 0, 0), ZoneId.of("UTC")),
				ZonedDateTime.of(LocalDateTime.of(2020, 12, 31, 23, 59, 59), ZoneId.of("UTC")), 35.5, 0)
			);
	}

	@Test
	void findPricesNotFound() {
		var query = Instancio.create(PriceViewFindQuery.class);
		var prices = repository.findPrice(query);
		assertThat(prices).isEmpty();
	}

}