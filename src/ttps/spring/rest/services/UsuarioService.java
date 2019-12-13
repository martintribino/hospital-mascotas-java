package ttps.spring.rest.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttps.spring.dao.IUsuarioDAO;
import ttps.spring.model.Usuario;

@Service
public class UsuarioService {

	@Autowired
    private IUsuarioDAO usuarioRepository;

	//retorna un Usuario por username
	public List<Usuario> recuperarUsuario(String userName) {
        return usuarioRepository.recuperarUsuario(userName);
    }

	//retorna un Usuario por username
	public Usuario recuperarUsuarioPorNombre(String userName) {
		List<Usuario> lista = this.recuperarUsuario(userName);
		Usuario usuario = null;
		if (!lista.isEmpty()) {
			usuario = lista.get(0);
		}
        return usuario;
    }

	//retorna si un Usuario existe
	public Boolean existe(Usuario usuario) {
        List<Usuario> listaUsuarios = this.recuperarUsuario(usuario.getNombreUsuario());
        return !listaUsuarios.isEmpty();
    }

	//actualiza un usuario existente
	public Usuario actualizar(Usuario usuario) {
        return usuarioRepository.actualizar(usuario);
    }

}
