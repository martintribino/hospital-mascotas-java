package ttps.spring.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import ttps.spring.helpers.GenericHelper;

@Entity
@Table(name="evento")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_evento", length=20)
public class Evento implements Serializable {

	/**
	 * Clase abstracta Evento
	 */
	private static final long serialVersionUID = 1222226124362981093L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@Column(name="tipo_evento", insertable = false, updatable = false)
	@JsonProperty("tipo_evento")
	private String tipoEvento;
	@Column(name="slug", insertable = true, updatable = false, nullable = false)
	private String slug;
	private String descripcion;
	@JsonIgnore
	@OneToOne(
			optional = false,
			fetch = FetchType.EAGER,
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private Turno turno;
	@ManyToOne(optional = false)
	@JoinColumn(name="veterinario_id")
	@JsonIgnore
	private Veterinario veterinario;
	@ManyToOne(optional = false)
	@JoinColumn(name="mascota_id")
	@JsonIgnore
	private Mascota mascota;

	public Evento() {
		this.setDescripcion("");
		this.setVeterinario(null);
		this.setMascota(null);
		this.generarSlug();
		this.setTurno(new Turno());
	}

	public Evento(
			LocalDate fecha,
			LocalTime inicio,
			LocalTime fin,
			String descripcion,
			Veterinario veterinario,
			Mascota mascota) {
		this.setDescripcion(descripcion);
		this.setVeterinario(veterinario);
		this.setMascota(mascota);
		this.generarSlug();
		this.setTurno(new Turno(fecha, inicio, fin, Turno.Estados.DISPONIBLE, this));
	}

	private void generarSlug() {
		this.slug = GenericHelper.slugToday();
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Veterinario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	public String getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(String tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

}
