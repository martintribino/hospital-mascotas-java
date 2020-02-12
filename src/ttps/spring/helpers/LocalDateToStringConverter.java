package ttps.spring.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.fasterxml.jackson.databind.util.StdConverter;

public class LocalDateToStringConverter extends StdConverter<LocalDate, String> {
	static final DateTimeFormatter DATE_FORMATTER =
			DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);

	@Override
	public String convert(LocalDate value) {
	      return value.format(DATE_FORMATTER);
	}

}
