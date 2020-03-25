package ttps.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ttps.spring.model.Persona;
import ttps.spring.requests.PersonaReqBody;

@Entity
@Table(name="duenio")
@PrimaryKeyJoinColumn(name = "persona")
@DiscriminatorValue("duenio")
public class Duenio extends Persona {

	/**
	 * Clase Dueño
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(
		mappedBy="duenio",
		cascade = CascadeType.ALL,
        orphanRemoval = true
    )
	@JsonIgnore
	private List<Mascota> mascotas;
	
	public Duenio() {
		super();
		this.setMascotas(new ArrayList<Mascota>());
	}

	public Duenio(PersonaReqBody p) {
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
		this.setMascotas(new ArrayList<Mascota>());
	}

	public Duenio(
			String nombreUsuario,
			String nombre,
			String apellido,
			String claveStr,
			String email,
			int dni,
			int telefono,
			String domicilio,
			List<Mascota> mascotas ) {
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
		this.setMascotas(mascotas);
	}

	public Duenio(
			String nombre,
			String apellido,
			String email,
			int dni,
			int telefono,
			String domicilio,
			Usuario usuario,
			List<Mascota> mascotas ) {
		super(
				nombre,
				apellido,
				email,
				dni,
				telefono,
				domicilio,
				usuario
		);
		this.setMascotas(mascotas);
	}

	public List<Mascota> getMascotas() {
		return mascotas;
	}

	public void setMascotas(List<Mascota> mascotas) {
		this.mascotas = mascotas;
	}

}
