package com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.controller;

import com.kairosds.challenge.pricesapi.application.query.handle.PriceViewQueryHandler;
import com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.mapper.PriceViewResponseMapper;
import com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.request.PriceViewFindRequest;
import com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.response.PriceViewFindResponse;
import com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.ui.PriceViewRestUI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PriceViewRestController implements PriceViewRestUI {

	private final PriceViewQueryHandler priceViewQueryHandler;

	@Override
	public ResponseEntity<PriceViewFindResponse> findPrice(PriceViewFindRequest request) {
		return ResponseEntity.of(priceViewQueryHandler.findPrice(request.toQuery())
			.map(PriceViewResponseMapper.INSTANCE::toResponse));
	}

}
