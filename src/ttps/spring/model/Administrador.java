package ttps.spring.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import ttps.spring.requests.PersonaReqBody;

@Entity
@Table(name="administrador")
@PrimaryKeyJoinColumn(name = "persona")
@DiscriminatorValue("administrador")
public class Administrador  extends Persona {

	/**
	 * Clase Administrador
	 */
	
	private static final long serialVersionUID = 1L;
	
	public Administrador() {
		super();
	}
	
	public Administrador(PersonaReqBody p) {
		super(
				p.getNombreUsuario(),
				p.getNombre(),
				p.getApellido(),
				p.getClave(),
				p.getEmail(),
				p.getDni(),
				p.getTelefono(),
				p.getDomicilio()
		);
	}

	public Administrador(
			String nombreUsuario,
			String nombre,
			String apellido,
			String claveStr,
			String email,
			int dni,
			int telefono,
			String domicilio) {
		super(
				nombreUsuario,
				nombre,
				apellido,
				claveStr,
				email,
				dni,
				telefono,
				domicilio
		);
	}

}
