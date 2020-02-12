package ttps.spring.helpers;

import java.time.LocalTime;

import com.fasterxml.jackson.databind.util.StdConverter;

public class StringToLocalTimeConverter extends StdConverter<String, LocalTime> {

	@Override
	public LocalTime convert(String value) {
	      return LocalTime.parse(value, LocalTimeToStringConverter.DATE_FORMATTER);
	}

}
