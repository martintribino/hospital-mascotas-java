package ttps.spring.dao;

import java.util.List;

import ttps.spring.dao.IGenericDAO;
import ttps.spring.model.Usuario;

public interface IUsuarioDAO extends IGenericDAO<Usuario> {
	public List<Usuario> recuperarUsuario(String userName);
	public List<Usuario> recuperarUsuarios();
	public Usuario recuperarUsuarioPorNombre(String userName);
}
