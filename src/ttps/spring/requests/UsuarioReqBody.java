package ttps.spring.requests;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioReqBody {

    @Size(min = 2, max = 50, message = "nombre debe tener entre 2 y 50 caracteres")
	private String nombreUsuario;
	@JsonProperty("nombre_usuario_viejo")
	private String nombreUsuarioViejo;
	private String clave;
	@JsonProperty("confirmar_clave")
	private String confirmarClave;

	public UsuarioReqBody() {
		// TODO Auto-generated constructor stub
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getConfirmarClave() {
		return confirmarClave;
	}

	public void setConfirmarClave(String confirmarClave) {
		this.confirmarClave = confirmarClave;
	}

    public String getNombreUsuarioViejo() {
		return nombreUsuarioViejo;
	}

	public void setNombreUsuarioViejo(String nombreUsuarioViejo) {
		this.nombreUsuarioViejo = nombreUsuarioViejo;
	}

}
