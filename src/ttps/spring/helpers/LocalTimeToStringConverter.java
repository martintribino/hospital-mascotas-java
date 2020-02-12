package ttps.spring.helpers;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.fasterxml.jackson.databind.util.StdConverter;

public class LocalTimeToStringConverter extends StdConverter<LocalTime, String> {
	static final DateTimeFormatter DATE_FORMATTER =
			DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);

	@Override
	public String convert(LocalTime value) {
	      return value.format(DATE_FORMATTER);
	}

}
