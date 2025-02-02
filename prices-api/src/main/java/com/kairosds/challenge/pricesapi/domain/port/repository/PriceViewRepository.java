package com.kairosds.challenge.pricesapi.domain.port.repository;

import com.kairosds.challenge.pricesapi.domain.dto.PriceView;
import com.kairosds.challenge.pricesapi.domain.dto.PriceViewFindQuery;

import java.util.Collection;

public interface PriceViewRepository {
	Collection<PriceView> findPrice(PriceViewFindQuery query);
}
