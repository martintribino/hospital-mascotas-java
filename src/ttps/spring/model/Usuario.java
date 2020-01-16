package ttps.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import ttps.spring.model.Encrypt;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	/**
	 * Clase Usuario
	 */
	private static final long serialVersionUID = 2683988584852057037L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@Column(name="nombre_usuario", unique=true, updatable= true)
    @Size(min = 4, max = 50, message = "nombre debe tener entre 4 y 50 caracteres")
	private String nombreUsuario;
    @Size(min = 4, max = 150, message = "clave debe tener entre 4 y 150 caracteres")
    @JsonProperty(access = Access.WRITE_ONLY)
	private String clave;
	@JsonIgnore
	@OneToOne(mappedBy="usuario")
	private Persona persona;

	public Usuario() {
		this.setNombreUsuario("");
		this.setClave("");
		this.setPersona(null);
	}

	public Usuario(
			String nombreUsuario,
			String claveStr,
			Persona persona
	) {
		this.setNombreUsuario(nombreUsuario);
		this.setClave(claveStr);
		this.setPersona(persona);
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String claveStr) {
		this.clave = claveStr;
	}

	public Persona getPersona() {
		return persona;
	}

	public String getImagen() {
		String img = "";
		if(this.getPersona() != null) {
			img = this.getPersona().getImagen();
		}
		return img;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Boolean verificarClave(String clave) {
		return Encrypt.matches(clave, this.getClave());
	}
	
	public String getRole() {
		String role = "";
		if (this.getPersona() != null) {
			role = this.getPersona().getRole();
		}
		return role;
	}

}
