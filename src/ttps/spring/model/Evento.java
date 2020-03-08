package ttps.spring.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="evento")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_evento", discriminatorType=DiscriminatorType.STRING, length=20)
public class Evento implements Serializable {

	/**
	 * Clase abstracta Evento
	 */
	private static final long serialVersionUID = 1222226124362981093L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@Column(name="slug", unique = true, updatable = true)
    @JsonProperty(access = Access.READ_ONLY)
	private String slug;
	@JsonIgnore
	@Column(name="tipo_evento", insertable = false, updatable = false)
	@JsonProperty("tipo_evento")
	private String tipoEvento;
    @Size(min = 5, max = 20, message = "tipo debe tener entre 5 y 20 caracteres")
	@JsonProperty("tipo")
	private String tipo;
    @Size(min = 10, max = 100, message = "descripcion debe tener entre 10 y 100 caracteres")
	private String descripcion;
	@JsonIgnore
	@OneToOne(
			optional = false,
			fetch = FetchType.EAGER,
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private Turno turno;
	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name="mascota_id")
	private Mascota mascota;

	public Evento() {
		UUID uuid = UUID.randomUUID();
		this.setSlug(uuid.toString());
		this.setTipo("Evento");
		this.setDescripcion("Descripcion Evento");
		this.setMascota(null);
		this.setTurno(new Turno());
	}

	public Evento(
			LocalDate fecha,
			LocalTime inicio,
			LocalTime fin,
			String tipo,
			String descripcion,
			Mascota mascota) {
		UUID uuid = UUID.randomUUID();
		this.setSlug(uuid.toString());
		this.setTipo(tipo);
		this.setDescripcion(descripcion);
		this.setMascota(mascota);
		this.setTurno(new Turno(fecha, inicio, fin, Turno.Estados.RESERVADO, this));
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

}
