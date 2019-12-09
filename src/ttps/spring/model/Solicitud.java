package ttps.spring.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="solicitud")
public class Solicitud {

	/**
	 * Clase Estado Solicitud
	 */

	public static enum Estados {
	  ESPERA,
	  APROBADO,
	  RECHAZADO
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	private Date fecha;
	private Solicitud.Estados estado;
	@ManyToOne(optional = true)
	@JoinColumn(name="mascota_id")
	@JsonIgnore
	private Mascota mascota;
	@ManyToOne(optional = true)
	@JoinColumn(name="veterinario_id")
	@JsonIgnore
	private Veterinario veterinario;
	
	public Solicitud() {
		Calendar today = Calendar.getInstance();
		today.clear(Calendar.HOUR); today.clear(Calendar.MINUTE); today.clear(Calendar.SECOND);
		Date todayDate = today.getTime();
		this.setFecha(todayDate);
		this.setEstado(Solicitud.Estados.ESPERA);
		this.setMascota(new Mascota());
		this.setVeterinario(new Veterinario());
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
