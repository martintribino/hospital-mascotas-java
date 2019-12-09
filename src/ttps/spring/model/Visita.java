package ttps.spring.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Visita")
public class Visita extends Evento {
	/**
	 * Clase Visita
	 */
	
	private String peso;
	private String motivo;
	private String diagnostico;
	private String indicaciones;

	public Visita() {
		super();
		this.setPeso("");
		this.setMotivo("");
		this.setDiagnostico("");
		this.setIndicaciones("");
	}

	public Visita(
			Date fecha,
			String descripcion,
			Boolean concurrio,
			Veterinario veterinario,
			Mascota mascota,
			String droga,
			String peso,
			String motivo,
			String diagnostico,
			String indicaciones) {
		super(fecha, descripcion, concurrio,veterinario, mascota);
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
