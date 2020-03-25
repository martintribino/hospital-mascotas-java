package ttps.spring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
	@OneToMany(mappedBy="veterinario", cascade = CascadeType.ALL, orphanRemoval = false)
	@JsonIgnore
	private List<Mascota> mascotas;
	@OneToMany(mappedBy="veterinario", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Solicitud> solicitudes;

	public Veterinario() {
		super();
		this.setNombreClinica("");
		this.setDomicilioClinica("");
		this.setValidado(false);
		this.setSolicitudes(new ArrayList<Solicitud>());
		this.setMascotas(new ArrayList<Mascota>());
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
		this.setSolicitudes(new ArrayList<Solicitud>());
		this.setMascotas(new ArrayList<Mascota>());
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
		this.setMascotas(new ArrayList<Mascota>());
		this.setSolicitudes(new ArrayList<Solicitud>());
	}

	public Veterinario(
			String nombre,
			String apellido,
			String email,
			int dni,
			int telefono,
			String domicilio,
			Usuario usuario,
			String nombreClinica,
			String domicilioClinica,
			Boolean validado) {
		super(
				nombre,
				apellido,
				email,
				dni,
				telefono,
				domicilio,
				usuario
		);
		this.setNombreClinica(nombreClinica);
		this.setDomicilioClinica(domicilioClinica);
		this.setValidado(validado);
		this.setMascotas(new ArrayList<Mascota>());
		this.setSolicitudes(new ArrayList<Solicitud>());
	}

	public List<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(List<Solicitud> arrayList) {
		this.solicitudes = arrayList;
	}

	public List<Mascota> getMascotas() {
		return mascotas;
	}

	public void setMascotas(List<Mascota> mascotas) {
		this.mascotas = mascotas;
	}

	public void addMascota(Mascota mascota) {
	    if (this.mascotas.contains(mascota))
	        return ;
	    this.mascotas.add(mascota);
		mascota.setVeterinario(this);
	}

	public void removeMascota(Mascota mascota) {
	    if (!mascotas.contains(mascota))
	        return ;
		this.mascotas.remove(mascota);
		mascota.setVeterinario(null);
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

}
