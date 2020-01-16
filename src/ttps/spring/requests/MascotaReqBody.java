package ttps.spring.requests;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import ttps.spring.helpers.GenericHelper;

public class MascotaReqBody {

	@Column(name="slug", insertable = true, updatable = false, nullable = false)
	private String slug;
    @Size(min = 2, max = 40, message = "nombre debe tener entre 2 y 40 caracteres")
	private String nombre;
	@NotBlank(message = "Por favor proporcione especie")
	private String especie;
	@NotBlank(message = "Por favor proporcione raza")
	private String raza;
	@NotBlank(message = "Por favor proporcione sexo")
	private String sexo;
	private String color;
	private String senias;
	@Column(name="fecha_nacimiento")
	@Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = GenericHelper.DATE_FORMAT)
    @Past(message = "Por favor proporcione una fecha de nacimiento válida")
	private Date fechaNacimiento;
	private String imagen;
	private String username;

	public MascotaReqBody() {
		// TODO Auto-generated constructor stub
	}

	public String getSlug() {
		return slug;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSenias() {
		return senias;
	}

	public void setSenias(String senias) {
		this.senias = senias;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
