package ttps.spring.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(Visita.TIPO)
public class Visita extends Evento {

	/**
	 * Clase Visita
	 */
	private static final long serialVersionUID = -8503792134284508309L;
	//constantes
	public static final String TIPO = "Visita";
	
	private String peso;
	private String motivo;
	private String diagnostico;
	private String indicaciones;

	public Visita() {
		super();
		this.setTipo(Visita.TIPO);
		this.setPeso("");
		this.setMotivo("");
		this.setDiagnostico("");
		this.setIndicaciones("");
	}

	public Visita(
			LocalDate fecha,
			LocalTime inicio,
			LocalTime fin,
			String descripcion,
			Mascota mascota,
			String peso,
			String motivo,
			String diagnostico,
			String indicaciones) {
		super(fecha, inicio, fin, Visita.TIPO, descripcion, mascota);
		this.setPeso(peso);
		this.setMotivo(motivo);
		this.setDiagnostico(diagnostico);
		this.setIndicaciones(indicaciones);
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String d) {
		this.peso = d;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getIndicaciones() {
		return indicaciones;
	}

	public void setIndicaciones(String indicaciones) {
		this.indicaciones = indicaciones;
	}

}
