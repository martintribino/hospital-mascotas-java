package ttps.spring.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Vacuna")
public class Vacuna extends Evento {
	/**
	 * Clase Vacuna
	 */

	public Vacuna() {
		super();
	}

	public Vacuna(
			Date fecha,
			String descripcion,
			Boolean concurrio,
			Veterinario veterinario,
			Mascota mascota) {
		super(fecha, descripcion, concurrio, veterinario, mascota);
	}

}
