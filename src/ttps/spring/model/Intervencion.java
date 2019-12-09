package ttps.spring.model;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Intervencion")
public class Intervencion extends Evento {
	/**
	 * Clase Intervencion
	 */

	public Intervencion() {
		super();
	}

	public Intervencion(
			Date fecha,
			String descripcion,
			Boolean concurrio,
			Veterinario veterinario,
			Mascota mascota) {
		super(fecha, descripcion, concurrio, veterinario, mascota);
	}

}
