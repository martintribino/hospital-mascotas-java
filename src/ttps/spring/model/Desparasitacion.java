package ttps.spring.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Desparasitacion")
public class Desparasitacion extends Evento {
	/**
	 * Clase Desparasitacion
	 */
	
	private String droga;
	private String resultado;

	public Desparasitacion() {
		super();
		this.setDroga("");
		this.setResultado("");
	}

	public Desparasitacion(
			Date fecha,
			String descripcion,
			Boolean concurrio,
			Veterinario veterinario,
			Mascota mascota,
			String droga,
			String resultado) {
		super(fecha, descripcion, concurrio, veterinario, mascota);
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
