package com.kairosds.challenge.pricesapi.infrastructure.adapter.persistence.jdbc.repository;

import com.kairosds.challenge.pricesapi.domain.dto.PriceView;
import com.kairosds.challenge.pricesapi.domain.dto.PriceViewFindQuery;
import com.kairosds.challenge.pricesapi.domain.port.repository.PriceViewRepository;
import com.kairosds.challenge.pricesapi.infrastructure.adapter.persistence.jdbc.mapper.PriceViewMapper;
import com.kairosds.challenge.pricesapi.infrastructure.adapter.persistence.jdbc.model.PriceViewModel;

import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

import java.util.Collection;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class PriceViewJdbcRepository implements PriceViewRepository {

	private final JdbcAggregateTemplate jdbcAggregateTemplate;

	@Transactional(readOnly = true)
	@Override
	public Collection<PriceView> findPrice(PriceViewFindQuery query) {
		var criteria = query(where("product_id").is(query.productId())
			.and("brand_id").is(query.brandId())
			.and("start_date").lessThanOrEquals(query.applicationDate())
			.and("end_date").greaterThanOrEquals(query.applicationDate()));
		return jdbcAggregateTemplate.findAll(criteria, PriceViewModel.class).stream()
			.map(PriceViewMapper.INSTANCE::toDomain).toList();
	}
}
