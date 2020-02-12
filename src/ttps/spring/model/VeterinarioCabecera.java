package ttps.spring.model;

import java.io.Serializable;
import java.util.Date;

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
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import ttps.spring.helpers.GenericHelper;

@Entity
@Table( name="solicitud",
		indexes={@Index(name="veterinario_cabecera_fk",columnList="mascota_id,veterinario_id",unique=true)}
)
public class VeterinarioCabecera implements Serializable {

	/**
	 * VeterinarioCabecera
	 */
	private static final long serialVersionUID = 1831047107500535957L;

	public static enum Estados {
		ACTIVO,
		INACTIVO,
		BORRADO
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@Column(name="slug", insertable = true, updatable = false, nullable = false)
	private String slug;
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_creacion", updatable = false) 
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = GenericHelper.LOCALDATETIME_FORMAT)
	private Date fechaCreacion;
	@UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fecha_actualizacion") 
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = GenericHelper.LOCALDATETIME_FORMAT)
	private Date fechaActualizacion;
	private VeterinarioCabecera.Estados estado;
	@ManyToOne(optional = true)
	@JoinColumn(name="mascota_id", nullable = true)
	private Mascota mascota;
	@ManyToOne(optional = true)
	@JoinColumn(name="veterinario_id", nullable = true)
	private Veterinario veterinario;

	public VeterinarioCabecera() {
		this.setEstado(VeterinarioCabecera.Estados.ACTIVO);
		this.setMascota(new Mascota());
		this.setVeterinario(new Veterinario());
		this.generarSlug();
	}

	public VeterinarioCabecera(
			VeterinarioCabecera.Estados estado,
			Mascota mascota,
			Veterinario veterinario
		) {
		this.setEstado(estado);
		this.setMascota(mascota);
		this.setVeterinario(veterinario);
		this.generarSlug();
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

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public VeterinarioCabecera.Estados getEstado() {
		return estado;
	}

	public void setEstado(VeterinarioCabecera.Estados estado) {
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

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

}
