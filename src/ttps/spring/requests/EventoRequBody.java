package ttps.spring.requests;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ttps.spring.helpers.GenericHelper;
import ttps.spring.helpers.LocalDateToStringConverter;
import ttps.spring.helpers.LocalTimeToStringConverter;
import ttps.spring.helpers.StringToLocalDateConverter;
import ttps.spring.helpers.StringToLocalTimeConverter;
import ttps.spring.model.Evento;

public class EventoRequBody {

    @JsonSerialize(converter = LocalDateToStringConverter.class)
    @JsonDeserialize(converter = StringToLocalDateConverter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = GenericHelper.LOCALDATE_FORMAT)
	private LocalDate fecha;
    @JsonSerialize(converter = LocalTimeToStringConverter.class)
    @JsonDeserialize(converter = StringToLocalTimeConverter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = GenericHelper.LOCALTIME_FORMAT)
	private LocalTime inicio;
    @JsonSerialize(converter = LocalTimeToStringConverter.class)
    @JsonDeserialize(converter = StringToLocalTimeConverter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = GenericHelper.LOCALTIME_FORMAT)
	private LocalTime fin;
    @Size(min = 4, max = 50, message = "nombre debe tener entre 4 y 50 caracteres")
	private String username;
	private String slug;
	private Evento evento;

	public EventoRequBody() {}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalTime inicio) {
		this.inicio = inicio;
	}

	public LocalTime getFin() {
		return fin;
	}

	public void setFin(LocalTime fin) {
		this.fin = fin;
	}

}
