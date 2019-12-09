package ttps.spring.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.DiscriminatorType;

@Entity
@Table(name="evento")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_evento", discriminatorType = DiscriminatorType.STRING)
public class Evento {

	/**
	 * Clase abstracta Evento
	 */

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@Column(name="tipo_evento", insertable = false, updatable = false)
	@JsonProperty("tipo_evento")
	private String tipoEvento;
	private Date fecha;
	private String descripcion;
	private Boolean concurrio;
	@ManyToOne(optional = false)
	@JoinColumn(name="veterinario_id")
	@JsonIgnore
	private Veterinario veterinario;
	@ManyToOne(optional = false)
	@JoinColumn(name="mascota_id")
	@JsonIgnore
	private Mascota mascota;

	public Evento() {
		this.setFecha(Calendar.getInstance().getTime());
		this.setDescripcion("");
		this.setConcurrio(false);
		this.setVeterinario(null);
		this.setMascota(null);
	}

	public Evento(
			Date fecha,
			String descripcion,
			Boolean concurrio,
			Veterinario veterinario,
			Mascota mascota) {
		this.setFecha(fecha);
		this.setDescripcion(descripcion);
		this.setConcurrio(concurrio);
		this.setVeterinario(veterinario);
		this.setMascota(mascota);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getConcurrio() {
		return concurrio;
	}

	public void setConcurrio(Boolean concurrio) {
		this.concurrio = concurrio;
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

}
