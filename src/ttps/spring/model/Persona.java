package ttps.spring.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="persona")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="role", length=20)
public class Persona implements Serializable {
	/**
	 * Clase Persona
	 */
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
    /*@Column(unique=true, insertable = false, updatable = false)
    @NotBlank
    private String slug;*/
	private String nombre;
    @Size(min = 2, max = 40, message = "apellido debe tener entre 2 y 40 caracteres")
	private String apellido;
    @Min(value = 1, message = "Dni debe ser mayor que 0")
    @Max(value = 99999999, message = "Dni no puede ser mayor a 99999999")
	private int dni;
    @Email(message = "El mail debe ser válido")
	@Column(name="email", unique=true, updatable= true)
	@NotBlank(message = "Por favor proporcione un email")
	private String email;
	private int telefono;
	private String imagen;
	private String domicilio;
	@OneToOne(
			optional = false,
			fetch = FetchType.EAGER,
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private Usuario usuario;
	@Column(name="role", insertable = false, updatable = false)
	private String role;
	
	public Persona() {
		this.setNombre("");
		this.setApellido("");
		this.setEmail("");
		this.setDni(0);
		this.setTelefono(0);
		this.setDomicilio("");
		this.setUsuario(null);
	}
	
	protected Persona(
			String nombreUsuario,
			String nombre,
			String apellido,
			String claveStr,
			String email,
			int dni,
			int telefono,
			String domicilio) {
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setEmail(email);
		this.setDni(dni);
		this.setTelefono(telefono);
		this.setDomicilio(domicilio);
		Usuario usuarioNuevo = new Usuario(
				nombreUsuario,
				claveStr,
				this
			);
		usuarioNuevo.setClave(claveStr);
		this.setUsuario(
				usuarioNuevo
				);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
