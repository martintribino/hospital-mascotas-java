package ttps.spring.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(Intervencion.TIPO)
public class Intervencion extends Evento {

	/**
	 * Clase Intervencion
	 */
	private static final long serialVersionUID = -8738807883108116756L;
	//constantes
	public static final String TIPO = "Intervencion";

	public Intervencion() {
		super();
		this.setTipo(Intervencion.TIPO);
	}

	public Intervencion(
			LocalDate fecha,
			LocalTime inicio,
			LocalTime fin,
			String descripcion,
			Mascota mascota) {
		super(fecha, inicio, fin, Intervencion.TIPO, descripcion, mascota);
	}

}
