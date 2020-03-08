package ttps.spring.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ficha_publica")
public class FichaPublica implements Serializable {

	/**
	 * FichaPublica
	 */
	private static final long serialVersionUID = 7361138098231092876L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@Column(name="slug", insertable = true, updatable = false, nullable = false)
	private String slug;
	private Boolean nombre;
	private Boolean especie;
	private Boolean raza;
	private Boolean sexo;
	private Boolean color;
	private Boolean senias;
	private Boolean fechaNacimiento;
	private Boolean imagen;
	private Boolean duenio;
	private Boolean veterinario;
	@JsonIgnore
	@OneToOne(mappedBy="ficha")
	private Mascota mascota;

	public FichaPublica() {
		this.setNombre(true);
		this.setEspecie(true);
		this.setRaza(true);
		this.setSexo(false);
		this.setColor(false);
		this.setSenias(false);
		this.setFechaNacimiento(false);
		this.setImagen(false);
		this.setDuenio(false);
		this.setVeterinario(false);
		UUID uuid = UUID.randomUUID();
		this.setSlug(uuid.toString());
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

	public Boolean getNombre() {
		return nombre;
	}

	public void setNombre(Boolean nombre) {
		this.nombre = nombre;
	}

	public Boolean getEspecie() {
		return especie;
	}

	public void setEspecie(Boolean especie) {
		this.especie = especie;
	}

	public Boolean getRaza() {
		return raza;
	}

	public void setRaza(Boolean raza) {
		this.raza = raza;
	}

	public Boolean getSexo() {
		return sexo;
	}

	public void setSexo(Boolean sexo) {
		this.sexo = sexo;
	}

	public Boolean getColor() {
		return color;
	}

	public void setColor(Boolean color) {
		this.color = color;
	}

	public Boolean getSenias() {
		return senias;
	}

	public void setSenias(Boolean senias) {
		this.senias = senias;
	}

	public Boolean getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Boolean fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Boolean getImagen() {
		return imagen;
	}

	public void setImagen(Boolean imagen) {
		this.imagen = imagen;
	}

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	public Boolean getDuenio() {
		return duenio;
	}

	public void setDuenio(Boolean duenio) {
		this.duenio = duenio;
	}

	public Boolean getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Boolean veterinario) {
		this.veterinario = veterinario;
	}

}
