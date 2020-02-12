package ttps.spring.responses;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ttps.spring.helpers.LocalTimeToStringConverter;
import ttps.spring.helpers.StringToLocalTimeConverter;
import ttps.spring.model.Turno;
import ttps.spring.model.Turno.Estados;

public class HorariosResponse implements Serializable {

	/**
	 * HorariosResponse
	 */
	private static final long serialVersionUID = 6735173663090852153L;

	private int turno;
    @JsonSerialize(converter = LocalTimeToStringConverter.class)
    @JsonDeserialize(converter = StringToLocalTimeConverter.class)
	private LocalTime inicio;
    @JsonSerialize(converter = LocalTimeToStringConverter.class)
    @JsonDeserialize(converter = StringToLocalTimeConverter.class)
	private LocalTime fin;
	private Estados[] estados;
	private List<Turno> turnosValidos;

	public HorariosResponse(LocalDate fecha) {
		this.turno = Turno.TURNO;
		this.inicio = Turno.HORARIO_INICIO;
		this.fin = Turno.HORARIO_FIN;
		this.setEstados(Estados.values());
		this.turnosValidos = new ArrayList<Turno>();
		if(this.turno > 0 && this.fin.isAfter(this.inicio)) {
			LocalDate now = LocalDate.now();
			// La fecha es disponible mientras la fecha sea mayor a la actual o
			// igual y el tiempo mayor al actual
			Estados estado = Estados.DISPONIBLE;
			Boolean esHoy = false;
			if (fecha.isBefore(now)) {
				estado = Estados.NODISPONIBLE;
			} else if (fecha.isAfter(now)) {
				estado = Estados.DISPONIBLE;
			} else {
				esHoy = true;
			}
			if (esHoy) {
				LocalTime tiempoNow = LocalTime.now();
				for (int hora = this.getInicio().getHour(); hora <= this.getFin().getHour(); hora++ ) {
					for (int minuto = 0; minuto < 60; minuto += this.turno) {
						LocalTime tiempoInicio = LocalTime.of(hora, minuto);
						LocalTime tiempoFin = LocalTime.of(hora, minuto + this.turno - 1);
						estado = tiempoNow.isAfter(tiempoInicio) ? Estados.NODISPONIBLE : Estados.DISPONIBLE;
						this.turnosValidos.add(new Turno(
								fecha,
								tiempoInicio,
								tiempoFin,
								estado
						));
					}
				}
			} else {
				for (int hora = this.getInicio().getHour(); hora <= this.getFin().getHour(); hora++ ) {
					for (int minuto = 0; minuto < 60; minuto += this.turno) {
						this.turnosValidos.add(new Turno(
								fecha,
								LocalTime.of(hora, minuto),
								LocalTime.of(hora, minuto + this.turno - 1),
								estado
						));
					}
				}
			}
		}
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
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

	public Estados[] getEstados() {
		return estados;
	}

	public void setEstados(Estados[] estados) {
		this.estados = estados;
	}

	public List<Turno> getTurnosValidos() {
		return turnosValidos;
	}

	public void setTurnosValidos(List<Turno> turnosValidos) {
		this.turnosValidos = turnosValidos;
	}

}
