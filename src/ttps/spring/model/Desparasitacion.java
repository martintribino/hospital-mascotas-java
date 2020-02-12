package ttps.spring.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Desparasitacion")
public class Desparasitacion extends Evento {

	/**
	 * Clase Desparasitacion
	 */
	private static final long serialVersionUID = -693528691073413498L;
	
	private String droga;
	private String resultado;

	public Desparasitacion() {
		super();
		this.setDroga("");
		this.setResultado("");
	}

	public Desparasitacion(
			LocalDate fecha,
			LocalTime inicio,
			LocalTime fin,
			String descripcion,
			Veterinario veterinario,
			Mascota mascota,
			String droga,
			String resultado) {
		super(fecha, inicio, fin, descripcion, veterinario, mascota);
		this.setDroga(droga);
		this.setResultado(resultado);
	}

	public String getDroga() {
		return droga;
	}

	public void setDroga(String droga) {
		this.droga = droga;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
}
