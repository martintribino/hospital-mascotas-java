package ttps.spring.responses;

public class UsuarioResponse {

	private String nombreUsuario;
	private String role;
	private String token;
	private String imagen;
	
	public UsuarioResponse() {
		this.nombreUsuario = "";
		this.role = "";
		this.token = "";
		this.imagen = "";
	}
	
	public UsuarioResponse(String nombre, String role, String imagen) {
		this.setNombreUsuario(nombre);
		this.setRole(role);
		this.setImagen(imagen);;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
