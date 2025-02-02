package com.kairosds.challenge.pricesapi.application.query.handle.impl;

import java.util.Comparator;

import com.kairosds.challenge.pricesapi.domain.dto.PriceView;
import com.kairosds.challenge.pricesapi.domain.dto.PriceViewFindQuery;
import com.kairosds.challenge.pricesapi.domain.port.repository.PriceViewRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceViewQueryHandlerImplTest {

	private @Mock PriceViewRepository priceViewRepository;
	private @InjectMocks PriceViewQueryHandlerImpl handler;

	@Test
	void findPrice() {

		var query = Instancio.create(PriceViewFindQuery.class);
		var priceViewResponse = Instancio.createList(PriceView.class);
		var maxPrice = priceViewResponse.stream().max(Comparator.comparingInt(PriceView::priority));

		when(priceViewRepository.findPrice(query)).thenReturn(priceViewResponse);

		var result = handler.findPrice(query);

		assertThat(result).isPresent().usingRecursiveComparison().isEqualTo(maxPrice);
	}

}