package com.lejtman.joseph.repositories.jpa.converters;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ZonedDateTimeAttributeConverter  implements AttributeConverter<ZonedDateTime, Long> {

	@Override
    public Long convertToDatabaseColumn(ZonedDateTime instant) {
    	return (instant == null ? null :instant.toInstant().toEpochMilli());
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(Long sqlTimestamp) {
    	return (sqlTimestamp == null ? null : ZonedDateTime.ofInstant(Instant.ofEpochMilli(sqlTimestamp), ZoneId.of("Z")));
    }

}
