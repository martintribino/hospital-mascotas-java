package ttps.spring.model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="mascota")
public class Mascota implements Serializable {

	/**
	 * Clase Mascota
	 */

	private static final long serialVersionUID = 1L;

	//Constantes para habilitar/deshabilitar metodos para mostrar en la ficha publica
	@JsonIgnore
	public static final String METODO_NOMBRE 	  = "getNombreParaFicha";
	public static final String METODO_FECHA  	  = "getFechaNacimientoParaFicha";
	public static final String METODO_ESPECIE 	  = "getEspecieParaFicha";
	public static final String METODO_RAZA 		  = "getRazaParaFicha";
	public static final String METODO_SEXO 		  = "getSexoParaFicha";
	public static final String METODO_COLOR 	  = "getColorParaFicha";
	public static final String METODO_SENIAS 	  = "getSeniasParaFicha";
	public static final String METODO_IMAGEN 	  = "getImagenParaFicha";
	public static final String METODO_DUENIO 	  = "getDuenioParaFicha";
	public static final String METODO_VETERINARIO = "getVeterinarioParaFicha";

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
    @Size(min = 4, max = 12, message = "nombre debe tener entre 2 y 40 caracteres")
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
	@JsonProperty("fecha_nacimiento")
	@Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Past(message = "Por favor proporcione una fecha de nacimiento válida")
	//@NotBlank(message = "Por favor proporcione una fecha de nacimiento")
	private Date fechaNacimiento;
	private String imagen;
	@Column(name="metodos_publicos")
	@ElementCollection(targetClass=String.class, fetch = FetchType.EAGER)
	@JsonProperty("metodos_publicos")
	@JsonIgnore
	private List<String> metodosPublicos;
	@ManyToOne(optional = true)
	@JoinColumn(name="duenio_id")
	@JsonIgnore
	private Duenio duenio;
	@ManyToOne(optional = true)
	@JoinColumn(name="veterinario_id")
	@JsonIgnore
	private Veterinario veterinario;
	@OneToMany(mappedBy="mascota")
	@JsonIgnore
	private List<Evento> eventos;
	@OneToMany(mappedBy="mascota")
	@JsonIgnore
	private List<Solicitud> solicitudes;

	public  Mascota() {
		this.setNombre("");
		this.setFechaNacimiento(Calendar.getInstance().getTime());
		this.setEspecie("");
		this.setRaza("");
		this.setSenias("");
		this.setColor("");
		this.setSexo("");
		this.setImagen("");
		this.setDuenio(null);
		this.setVeterinario(null);
		this.setSolicitudes(new ArrayList<Solicitud>());
		this.metodosPublicos = new ArrayList<String>();
		this.setEventos(new ArrayList<Evento>());
		this.habilitarNombre();
		this.habilitarEspecie();
		this.habilitarRaza();
		this.habilitarFecha();
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
		this.setNombre(nombre);
		this.setFechaNacimiento(fechaNacimiento);
		this.setEspecie(especie);
		this.setRaza(raza);
		this.setSenias(senias);
		this.setColor(color);
		this.setSexo(sexo);
		this.setImagen(imagen);
		this.metodosPublicos = new ArrayList<String>();
		this.setDuenio(duenio);
		this.setVeterinario(veterinario);
		this.setSolicitudes(new ArrayList<Solicitud>());
		this.setEventos(new ArrayList<Evento>());
		this.habilitarNombre();
		this.habilitarEspecie();
		this.habilitarRaza();
		this.habilitarFecha();
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
		this.metodosPublicos.add(Mascota.METODO_NOMBRE);
	}
	
	public void deshabilitarNombre() {
		this.metodosPublicos.remove(Mascota.METODO_NOMBRE);
	}
	
	public Boolean nombreEstaHabilitado() {
		return this.metodosPublicos.contains(Mascota.METODO_NOMBRE);
	}

	@JsonIgnore
	public String getNombreParaFicha() {
		return "Nommbre: " + this.getNombre();
	}

	@JsonIgnore
	public List<String> getMetodosPublicos() {
		return metodosPublicos;
	}

	public void setMetodosPublicos(List<String> metodosPublicos) {
		this.metodosPublicos = metodosPublicos;
	}
	
	public void habilitarFecha() {
		this.metodosPublicos.add(Mascota.METODO_FECHA);
	}
	
	public void deshabilitarFecha() {
		this.metodosPublicos.remove(Mascota.METODO_FECHA);
	}
	
	public Boolean fechaEstaHabilitado() {
		return this.metodosPublicos.contains(Mascota.METODO_FECHA);
	}

	@JsonIgnore
	public String getFechaNacimientoParaFicha(String nom) {
		return "Fecha: " + this.getFechaNacimiento();
	}
	
	public void habilitarEspecie() {
		this.metodosPublicos.add(Mascota.METODO_ESPECIE);
	}
	
	public void deshabilitarEspecie() {
		this.metodosPublicos.remove(Mascota.METODO_ESPECIE);
	}
	
	public Boolean especieEstaHabilitado() {
		return this.metodosPublicos.contains(Mascota.METODO_ESPECIE);
	}

	@JsonIgnore
	public String getEspecieParaFicha() {
		return "Especie: " + this.getEspecie();
	}
	
	public void habilitarRaza() {
		this.metodosPublicos.add(Mascota.METODO_RAZA);
	}
	
	public void deshabilitarRaza() {
		this.metodosPublicos.remove(Mascota.METODO_RAZA);
	}
	
	public Boolean razaEstaHabilitado() {
		return this.metodosPublicos.contains(Mascota.METODO_RAZA);
	}

	@JsonIgnore
	public String getRazaParaFicha() {
		return "Raza: " + this.getRaza();
	}
	
	public void habilitarSexo() {
		this.metodosPublicos.add(Mascota.METODO_SEXO);
	}
	
	public void deshabilitarSexo() {
		this.metodosPublicos.remove(Mascota.METODO_SEXO);
	}
	
	public Boolean sexoEstaHabilitado() {
		return this.metodosPublicos.contains(Mascota.METODO_SEXO);
	}

	@JsonIgnore
	public String getSexoParaFicha() {
		return "Sexo: " + this.getSexo();
	}
	
	public void habilitarColor() {
		this.metodosPublicos.add(Mascota.METODO_COLOR);
	}
	
	public void deshabilitarColor() {
		this.metodosPublicos.remove(Mascota.METODO_COLOR);
	}
	
	public Boolean colorEstaHabilitado() {
		return this.metodosPublicos.contains(Mascota.METODO_COLOR);
	}

	@JsonIgnore
	public String getColorParaFicha() {
		return "Color: " + this.getColor();
	}
	
	public void habilitarSenias() {
		this.metodosPublicos.add(Mascota.METODO_SENIAS);
	}
	
	public void deshabilitarSenias() {
		this.metodosPublicos.remove(Mascota.METODO_SENIAS);
	}
	
	public Boolean seniaEstaHabilitado() {
		return this.metodosPublicos.contains(Mascota.METODO_SENIAS);
	}

	@JsonIgnore
	public String getSeniasParaFicha() {
		return "Señas: " + this.getSenias();
	}
	
	public void habilitarImagen() {
		this.metodosPublicos.add(Mascota.METODO_IMAGEN);
	}
	
	public void deshabilitarImagen() {
		this.metodosPublicos.remove(Mascota.METODO_IMAGEN);
	}
	
	public Boolean imagenEstaHabilitado() {
		return this.metodosPublicos.contains(Mascota.METODO_IMAGEN);
	}

	@JsonIgnore
	public String getImagenParaFicha() {
		String img = "";
		if (this.getImagen() != null)
			img = "Imagen: " + this.getImagen();
		return img;
	}
	
	public void habilitarDuenio() {
		this.metodosPublicos.add(Mascota.METODO_DUENIO);
	}
	
	public void deshabilitarDuenio() {
		this.metodosPublicos.remove(Mascota.METODO_DUENIO);
	}
	
	public Boolean duenioEstaHabilitado() {
		return this.metodosPublicos.contains(Mascota.METODO_DUENIO);
	}

	@JsonIgnore
	public String getDuenioParaFicha() {
		String duenioStr = "";
		if (this.getDuenio() != null) {
			duenio = this.getDuenio();
			duenioStr = String.format("Dueño: %s %s", duenio.getNombre(), duenio.getApellido());
		}
		return duenioStr;
	}
	
	public void habilitarVeterinario() {
		this.metodosPublicos.add(Mascota.METODO_VETERINARIO);
	}
	
	public void deshabilitarVeterinario() {
		this.metodosPublicos.remove(Mascota.METODO_VETERINARIO);
	}
	
	public Boolean veterinarioEstaHabilitado() {
		return this.metodosPublicos.contains(Mascota.METODO_VETERINARIO);
	}

	@JsonIgnore
	public String getVeterinarioParaFicha() {
		String vetStr = "";
		if (this.getVeterinario() != null) {
			Veterinario vet = this.getVeterinario();
			vetStr = String.format("Veterinario: %s %s", vet.getNombre(), vet.getApellido());
		}
		return vetStr;
	}

	@JsonIgnore
	public String getFichaPublica() {
		String fichaPublica = "";
		if(this.metodosPublicos.size() == 0)
			return "No tiene";
		for (String method : this.metodosPublicos) {
			try {
				Method met = Mascota.class.getMethod(method);
				fichaPublica += met.invoke(this);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return fichaPublica;
	}

}
