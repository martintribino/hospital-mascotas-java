package ttps.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ttps.spring.requests.PersonaReqBody;

@Entity
@Table(name="veterinario")
@PrimaryKeyJoinColumn(name = "persona")
@DiscriminatorValue("veterinario")
public class Veterinario extends Persona {

	/**
	 * Clase Veterinario
	 */
	
	private static final long serialVersionUID = 1L;

	@Column(name="nombre_clinica")
	private String nombreClinica;
	@Column(name="domicilio_clinica")
	private String domicilioClinica;
	private Boolean validado;
	@OneToMany(mappedBy="veterinario")
	@JsonIgnore
	private List<Mascota> mascotas;
	@OneToMany(mappedBy="veterinario")
	@JsonIgnore
	private List<Solicitud> solicitudes;
	@OneToMany(mappedBy="veterinario")
	@JsonIgnore
	private List<Evento> eventos;
	
	public Veterinario() {
		super();
		this.setNombreClinica("");
		this.setDomicilioClinica("");
		this.setValidado(false);
		this.setEventos(new ArrayList<Evento>());
		this.setMascotas(new ArrayList<Mascota>());
		this.setSolicitudes(new ArrayList<Solicitud>());
	}
	
	public Veterinario(PersonaReqBody p) {
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
		this.setNombreClinica("");
		this.setDomicilioClinica("");
		this.setValidado(false);
		this.setEventos(new ArrayList<Evento>());
		this.setMascotas(new ArrayList<Mascota>());
		this.setSolicitudes(new ArrayList<Solicitud>());
	}
	
	public Veterinario(
			String nombreUsuario,
			String nombre,
			String apellido,
			String claveStr,
			String email,
			int dni,
			int telefono,
			String domicilio,
			String nombreClinica,
			String domicilioClinica,
			Boolean validado) {
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
		this.setNombreClinica(nombreClinica);
		this.setDomicilioClinica(domicilioClinica);
		this.setValidado(validado);
		this.setEventos(new ArrayList<Evento>());
		this.setMascotas(new ArrayList<Mascota>());
		this.setSolicitudes(new ArrayList<Solicitud>());
	}

	public List<Mascota> getMascotas() {
		return mascotas;
	}

	public void setMascotas(List<Mascota> mascotas) {
		this.mascotas = mascotas;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public String getDomicilioClinica() {
		return domicilioClinica;
	}

	public void setDomicilioClinica(String domicilio) {
		this.domicilioClinica = domicilio;
	}

	public Boolean getValidado() {
		return validado;
	}

	public void setValidado(Boolean validado) {
		this.validado = validado;
	}

	public String getNombreClinica() {
		return nombreClinica;
	}

	public void setNombreClinica(String nombreClinica) {
		this.nombreClinica = nombreClinica;
	}

	public List<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(List<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}

}
