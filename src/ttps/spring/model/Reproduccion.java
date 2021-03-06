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
@DiscriminatorValue(Reproduccion.TIPO)
public class Reproduccion extends Evento {

	/**
	 * Clase Reproduccion
	 */
	private static final long serialVersionUID = -2111217446201041768L;
	//constantes
	public static final String TIPO = "Reproduccion";

	@Column(name="fecha_parto")
	@JsonProperty("fecha_parto")
	private Date fechaParto;
	@Column(name="nro_cachorros")
	@JsonProperty("nro_cachorros")
	private int nroCachorros;

	public Reproduccion() {
		super();
		this.setTipo(Reproduccion.TIPO);
		this.setFechaParto(Calendar.getInstance().getTime());
		this.setNroCachorros(0);
	}

	public Reproduccion(
			LocalDate fecha,
			LocalTime inicio,
			LocalTime fin,
			String descripcion,
			Mascota mascota,
			Date fechaParto,
			int nroCachorros) {
		super(fecha, inicio, fin, Reproduccion.TIPO, descripcion, mascota);
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
