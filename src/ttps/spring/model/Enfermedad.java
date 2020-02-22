package ttps.spring.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(Enfermedad.TIPO)
public class Enfermedad extends Evento {

	/**
	 * Clase Enfermedad
	 */
	private static final long serialVersionUID = -2596005362855999285L;
	//constantes
	public static final String TIPO = "Enfermedad";

	public Enfermedad() {
		super();
		this.setTipo(Enfermedad.TIPO);
	}

	public Enfermedad(
			LocalDate fecha,
			LocalTime inicio,
			LocalTime fin,
			String descripcion,
			Mascota mascota) {
		super(fecha, inicio, fin, Enfermedad.TIPO, descripcion, mascota);
	}

}
