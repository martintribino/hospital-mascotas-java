package ttps.spring.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(Desparasitacion.TIPO)
public class Desparasitacion extends Evento {

	/**
	 * Clase Desparasitacion
	 */
	private static final long serialVersionUID = -693528691073413498L;
	//constantes
	public static final String TIPO = "Desparasitacion";
	
	private String droga;
	private String resultado;

	public Desparasitacion() {
		super();
		this.setTipo(Desparasitacion.TIPO);
		this.setDroga("");
		this.setResultado("");
	}

	public Desparasitacion(
			LocalDate fecha,
			LocalTime inicio,
			LocalTime fin,
			String descripcion,
			Mascota mascota,
			String droga,
			String resultado) {
		super(fecha, inicio, fin, Desparasitacion.TIPO, descripcion, mascota);
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
