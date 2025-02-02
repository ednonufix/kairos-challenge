package com.kairosds.challenge.pricesapi.infrastructure.config;

import com.kairosds.challenge.pricesapi.infrastructure.adapter.persistence.jdbc.converter.TimestampToZonedDateTimeConverter;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.lang.NonNull;

import java.util.List;

@Configuration
public class DataJdbcConfig extends AbstractJdbcConfiguration {

	@Override
	public @NonNull JdbcCustomConversions jdbcCustomConversions() {
		return new JdbcCustomConversions(List.of(new TimestampToZonedDateTimeConverter()));
	}
}
