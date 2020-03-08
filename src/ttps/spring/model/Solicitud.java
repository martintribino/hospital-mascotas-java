package ttps.spring.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import ttps.spring.helpers.GenericHelper;

@Entity
@Table( name="solicitud",
		indexes={@Index(name="solicitud_fk",columnList="mascota_id,veterinario_id",unique=true)}
)
public class Solicitud implements Serializable {
	/**
	 * Clase Estado Solicitud
	 */
	private static final long serialVersionUID = -3348782479275251828L;

	public static enum Estados {
		ESPERA,
	  APROBADO,
	  RECHAZADO
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@Column(name="slug", insertable = true, updatable = false, nullable = false)
	private String slug;
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha", updatable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = GenericHelper.LOCALDATETIME_FORMAT)
	private Date fecha;
	private Solicitud.Estados estado;
	@ManyToOne(optional = true)
	@JoinColumn(name="mascota_id", nullable = true)
	private Mascota mascota;
	@ManyToOne(optional = true)
	@JoinColumn(name="veterinario_id", nullable = true)
	private Veterinario veterinario;
	
	public Solicitud() {
		this.setEstado(Solicitud.Estados.ESPERA);
		this.setMascota(new Mascota());
		this.setVeterinario(new Veterinario());
		UUID uuid = UUID.randomUUID();
		this.setSlug(uuid.toString());
	}
	
	public Solicitud(
			Date fecha,
			Solicitud.Estados estado,
			Mascota mascota,
			Veterinario veterinario) {
		this.setFecha(fecha);
		this.setEstado(estado);
		this.setMascota(mascota);
		this.setVeterinario(veterinario);
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

	public void setSlug(String slg) {
		this.slug = slg;
	}

	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Solicitud.Estados getEstado() {
		return estado;
	}
	public void setEstado(Solicitud.Estados estado) {
		this.estado = estado;
	}

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	public Veterinario getVeterinario() {
		return veterinario;
	}

	public void setVeterinario(Veterinario veterinario) {
		this.veterinario = veterinario;
	}

}
