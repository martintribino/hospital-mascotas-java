package ttps.spring.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@DiscriminatorValue("Reproduccion")
public class Reproduccion extends Evento {

	/**
	 * Clase Reproduccion
	 */
	private static final long serialVersionUID = -2111217446201041768L;

	@Column(name="fecha_parto")
	@JsonProperty("fecha_parto")
	private Date fechaParto;
	@Column(name="nro_cachorros")
	@JsonProperty("nro_cachorros")
	private int nroCachorros;

	public Reproduccion() {
		super();
		this.setFechaParto(Calendar.getInstance().getTime());
		this.setNroCachorros(0);
	}

	public Reproduccion(
			LocalDate fecha,
			LocalTime inicio,
			LocalTime fin,
			String descripcion,
			Veterinario veterinario,
			Mascota mascota,
			Date fechaParto,
			int nroCachorros) {
		super(fecha, inicio, fin, descripcion, veterinario, mascota);
		this.setFechaParto(fechaParto);
		this.setNroCachorros(nroCachorros);
	}

	public Date getFechaParto() {
		return fechaParto;
	}

	public void setFechaParto(Date fechaParto) {
		this.fechaParto = fechaParto;
	}

	public int getNroCachorros() {
		return nroCachorros;
	}

	public void setNroCachorros(int nroCachorros) {
		this.nroCachorros = nroCachorros;
	}

}
