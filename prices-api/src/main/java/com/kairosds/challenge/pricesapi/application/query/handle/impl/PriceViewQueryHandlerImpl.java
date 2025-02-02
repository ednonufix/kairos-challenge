package com.kairosds.challenge.pricesapi.application.query.handle.impl;

import com.kairosds.challenge.pricesapi.application.query.handle.PriceViewQueryHandler;
import com.kairosds.challenge.pricesapi.domain.dto.PriceView;
import com.kairosds.challenge.pricesapi.domain.dto.PriceViewFindQuery;
import com.kairosds.challenge.pricesapi.domain.port.repository.PriceViewRepository;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PriceViewQueryHandlerImpl implements PriceViewQueryHandler {

	private final PriceViewRepository priceViewRepository;

	@Override
	public Optional<PriceView> findPrice(PriceViewFindQuery query) {
		var comparator = Comparator.comparingInt(PriceView::priority);
		return priceViewRepository.findPrice(query).stream().max(comparator);
	}
}
