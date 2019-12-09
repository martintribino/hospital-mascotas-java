package ttps.spring.requests;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PersonaReqBody {

    @Size(min = 4, max = 50, message = "nombre debe tener entre 2 y 50 caracteres")
	private String nombreUsuario;
	@Size(min = 2, max = 40, message = "nombre debe tener entre 2 y 40 caracteres")
	private String nombre;
    @Size(min = 2, max = 40, message = "apellido debe tener entre 2 y 40 caracteres")
	private String apellido;
    @Size(min = 4, max = 150, message = "clave debe tener entre 4 y 150 caracteres")
	private String clave;
	private String confirmarClave;
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
	@NotBlank(message = "Por favor proporcione un rol")
	private String role;

    public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
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
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getConfirmarClave() {
		return confirmarClave;
	}
	public void setConfirmarClave(String confirmarClave) {
		this.confirmarClave = confirmarClave;
	}

}
