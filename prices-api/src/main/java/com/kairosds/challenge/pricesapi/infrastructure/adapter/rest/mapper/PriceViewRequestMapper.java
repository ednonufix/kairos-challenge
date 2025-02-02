package com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.mapper;

import com.kairosds.challenge.pricesapi.domain.dto.PriceViewFindQuery;
import com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.request.PriceViewFindRequest;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceViewRequestMapper {

	PriceViewRequestMapper INSTANCE = Mappers.getMapper(PriceViewRequestMapper.class);

	PriceViewFindQuery toQuery(PriceViewFindRequest request);

}
