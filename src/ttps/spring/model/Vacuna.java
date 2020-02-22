package ttps.spring.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(Vacuna.TIPO)
public class Vacuna extends Evento {

	/**
	 * Clase Vacuna
	 */
	private static final long serialVersionUID = 9025927139424449766L;
	//constantes
	public static final String TIPO = "Vacuna";

	public Vacuna() {
		super();
		this.setTipo(Vacuna.TIPO);
	}

	public Vacuna(
			LocalDate fecha,
			LocalTime inicio,
			LocalTime fin,
			String descripcion,
			Mascota mascota) {
		super(fecha, inicio, fin, Vacuna.TIPO, descripcion, mascota);
	}

}
