package ttps.spring.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(Recordatorio.TIPO)
public class Recordatorio extends Evento {

	/**
	 * Recordatorio
	 */
	private static final long serialVersionUID = -1341189128380163233L;
	//constantes
	public static final String TIPO = "Recordatorio";

	public Recordatorio() {
		super();
		this.setTipo(Recordatorio.TIPO);
	}

	public Recordatorio(
			LocalDate fecha,
			LocalTime inicio,
			LocalTime fin,
			String descripcion,
			Mascota mascota) {
		super(fecha, inicio, fin, Recordatorio.TIPO, descripcion, mascota);
	}

}
