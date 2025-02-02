package com.kairosds.challenge.pricesapi.infrastructure.adapter.persistence.jdbc.converter;

import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimestampToZonedDateTimeConverter implements Converter<Timestamp, ZonedDateTime> {

	@Override
	public ZonedDateTime convert(Timestamp source) {
		return source.toInstant().atZone(ZoneId.of("UTC"));
	}
}
