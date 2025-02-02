package com.kairosds.challenge.pricesapi.infrastructure.adapter.persistence.jdbc.mapper;

import com.kairosds.challenge.pricesapi.domain.dto.PriceView;
import com.kairosds.challenge.pricesapi.infrastructure.adapter.persistence.jdbc.model.PriceViewModel;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceViewMapper {

	PriceViewMapper INSTANCE = Mappers.getMapper(PriceViewMapper.class);

	PriceView toDomain(PriceViewModel model);

}
