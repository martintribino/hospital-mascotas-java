package ttps.spring.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import ttps.spring.helpers.GenericHelper;

@Entity
@Table(name="mascota")
public class Mascota implements Serializable {
	/**
	 * Clase Mascota
	 */
	private static final long serialVersionUID = 263020006357396096L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@Column(name="slug", unique = true, updatable = true)
    @JsonProperty(access = Access.READ_ONLY)
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = GenericHelper.LOCALDATETIME_FORMAT)
    @Past(message = "Por favor proporcione una fecha de nacimiento válida")
	private Date fechaNacimiento;
	private String imagen;
	private Boolean extraviada;
	@OneToOne(
			optional = false,
			fetch = FetchType.EAGER,
			cascade={CascadeType.ALL},
			orphanRemoval = true
	)
	private FichaPublica ficha;
	@ManyToOne(optional = true)
	@JoinColumn(name="duenio")
	private Duenio duenio;
	@ManyToOne(optional = true)
	@JoinColumn(name="veterinario")
	private Veterinario veterinario;
	@OneToMany(
			mappedBy="mascota",
			cascade={CascadeType.ALL},
			orphanRemoval = true
	)
	@JsonIgnore
	private List<Evento> eventos;
	@OneToMany(mappedBy="mascota",
			cascade={CascadeType.ALL},
			orphanRemoval = true
	)
	@JsonIgnore
	private List<Solicitud> solicitudes;

	public  Mascota() {
		UUID uuid = UUID.randomUUID();
		this.setSlug(uuid.toString());
		this.setNombre("");
		this.setFechaNacimiento(Calendar.getInstance().getTime());
		this.setEspecie("");
		this.setRaza("");
		this.setSenias("");
		this.setColor("");
		this.setSexo("");
		this.setImagen("");
		this.setExtraviada(false);
		this.setDuenio(null);
		this.setEventos(new ArrayList<Evento>());
		this.setFicha(new FichaPublica());
		this.setSolicitudes(new ArrayList<Solicitud>());
	}

	public  Mascota(
			String nombre,
			Date fechaNacimiento,
			String especie,
			String raza,
			String sexo,
			String color,
			String senias,
			String imagen,
			Duenio duenio) {
		UUID uuid = UUID.randomUUID();
		this.setSlug(uuid.toString());
		this.setNombre(nombre);
		this.setFechaNacimiento(fechaNacimiento);
		this.setEspecie(especie);
		this.setRaza(raza);
		this.setSenias(senias);
		this.setColor(color);
		this.setSexo(sexo);
		this.setImagen(imagen);
		this.setExtraviada(false);
		this.setDuenio(duenio);
		this.setEventos(new ArrayList<Evento>());
		this.setFicha(new FichaPublica());
		this.setSolicitudes(new ArrayList<Solicitud>());
	}

	public  Mascota(
			String nombre,
			Date fechaNacimiento,
			String especie,
			String raza,
			String sexo,
			String color,
			String senias,
			String imagen,
			Duenio duenio,
			Veterinario veterinario) {
		UUID uuid = UUID.randomUUID();
		this.setSlug(uuid.toString());
		this.setNombre(nombre);
		this.setFechaNacimiento(fechaNacimiento);
		this.setEspecie(especie);
		this.setRaza(raza);
		this.setSenias(senias);
		this.setColor(color);
		this.setSexo(sexo);
		this.setImagen(imagen);
		this.setExtraviada(false);
		this.setDuenio(duenio);
		this.setVeterinario(veterinario);
		this.setEventos(new ArrayList<Evento>());
		this.setFicha(new FichaPublica());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Boolean getExtraviada() {
		return extraviada;
	}

	public void setExtraviada(Boolean extraviada) {
		this.extraviada = extraviada;
	}

	public FichaPublica getFicha() {
		return ficha;
	}

	public void setFicha(FichaPublica ficha) {
		this.ficha = ficha;
	}

	public Duenio getDuenio() {
		return duenio;
	}

	public void setDuenio(Duenio duenio) {
		this.duenio = duenio;
	}

	public Veterinario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public List<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(List<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public void habilitarNombre() {
		this.ficha.setNombre(true);
	}
	
	public void deshabilitarNombre() {
		this.ficha.setNombre(false);
	}
	
	public Boolean nombreEstaHabilitado() {
		return this.ficha.getNombre();
	}
	
	public void habilitarFecha() {
		this.ficha.setFechaNacimiento(true);
	}
	
	public void deshabilitarFecha() {
		this.ficha.setFechaNacimiento(false);
	}
	
	public Boolean fechaEstaHabilitado() {
		return this.ficha.getFechaNacimiento();
	}
	
	public void habilitarEspecie() {
		this.ficha.setEspecie(true);
	}
	
	public void deshabilitarEspecie() {
		this.ficha.setEspecie(false);
	}
	
	public Boolean especieEstaHabilitado() {
		return this.ficha.getEspecie();
	}
	
	public void habilitarRaza() {
		this.ficha.setRaza(true);
	}
	
	public void deshabilitarRaza() {
		this.ficha.setRaza(false);
	}
	
	public Boolean razaEstaHabilitado() {
		return this.ficha.getRaza();
	}
	
	public void habilitarSexo() {
		this.ficha.setSexo(true);
	}
	
	public void deshabilitarSexo() {
		this.ficha.setSexo(false);
	}
	
	public Boolean sexoEstaHabilitado() {
		return this.ficha.getSexo();
	}
	
	public void habilitarColor() {
		this.ficha.setColor(true);
	}
	
	public void deshabilitarColor() {
		this.ficha.setColor(false);
	}
	
	public Boolean colorEstaHabilitado() {
		return this.ficha.getColor();
	}
	
	public void habilitarSenias() {
		this.ficha.setSenias(true);
	}
	
	public void deshabilitarSenias() {
		this.ficha.setSenias(false);
	}
	
	public Boolean seniasEstaHabilitado() {
		return this.ficha.getSenias();
	}
	
	public void habilitarImagen() {
		this.ficha.setImagen(true);
	}
	
	public void deshabilitarImagen() {
		this.ficha.setImagen(false);
	}
	
	public Boolean imagenEstaHabilitado() {
		return this.ficha.getImagen();
	}
	
	public void habilitarDuenio() {
		this.ficha.setDuenio(true);
	}
	
	public void deshabilitarDuenio() {
		this.ficha.setDuenio(false);
	}
	
	public Boolean duenioEstaHabilitado() {
		return this.ficha.getDuenio();
	}
	
	public void habilitarVeterinario() {
		this.ficha.setVeterinario(true);
	}
	
	public void deshabilitarVeterinario() {
		this.ficha.setVeterinario(false);
	}
	
	public Boolean veterinarioEstaHabilitado() {
		return this.ficha.getVeterinario();
	}

	public String getFichaPublicaSTR() {
		String result = "";
		String lineSep = System.lineSeparator();
		result += this.nombreEstaHabilitado() ? "Nombre: " + this.getNombre() + lineSep : "";
		result += this.especieEstaHabilitado() ? " Especie: " + this.getEspecie() + lineSep : "";
		result += this.razaEstaHabilitado() ? " Raza: " + this.getNombre() + lineSep : "";
		result += this.sexoEstaHabilitado() ? " Sexo: " + this.getSexo() + lineSep : "";
		result += this.colorEstaHabilitado() ? " Color: " + this.getColor() + lineSep : "";
		result += this.seniasEstaHabilitado() ? " Señas: " + this.getSenias() + lineSep : "";
		result += this.fechaEstaHabilitado() ? " Fecha de Nacimiento: " + this.getFechaNacimiento() + lineSep : "";
		result += this.imagenEstaHabilitado() ? " Imagen: " + this.getImagen() + lineSep : "";
		result += this.duenioEstaHabilitado() && this.getDuenio() != null ? " Duenio: " + this.getDuenio().getNombre() + " " + this.getDuenio().getApellido() + lineSep : "";
		result += this.veterinarioEstaHabilitado() && this.getVeterinario() != null ? " Veterinario: " + this.getVeterinario().getNombre() + " " + this.getVeterinario().getApellido() + lineSep : "";
		return result;
	}

}
