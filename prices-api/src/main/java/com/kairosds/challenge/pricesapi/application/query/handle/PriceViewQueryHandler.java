package com.kairosds.challenge.pricesapi.application.query.handle;

import com.kairosds.challenge.pricesapi.domain.dto.PriceView;
import com.kairosds.challenge.pricesapi.domain.dto.PriceViewFindQuery;

import java.util.Optional;

public interface PriceViewQueryHandler {
	Optional<PriceView> findPrice(PriceViewFindQuery query);
}
