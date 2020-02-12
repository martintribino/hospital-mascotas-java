package ttps.spring.requests;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import ttps.spring.helpers.GenericHelper;
import ttps.spring.model.Solicitud;

public class SolicitudReqBody {

	@Column(name="slug", insertable = true, updatable = false, nullable = false)
	private String slug;
	@Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = GenericHelper.LOCALDATETIME_FORMAT)
	private Date fecha;
	private Solicitud.Estados estado;

	public String getSlug() {
		return slug;
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

}
