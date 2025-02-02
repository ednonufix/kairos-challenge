package com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.mapper;

import com.kairosds.challenge.pricesapi.domain.dto.PriceView;
import com.kairosds.challenge.pricesapi.infrastructure.adapter.rest.response.PriceViewFindResponse;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceViewResponseMapper {

	PriceViewResponseMapper INSTANCE = Mappers.getMapper(PriceViewResponseMapper.class);

	PriceViewFindResponse toResponse(PriceView priceView);

}
