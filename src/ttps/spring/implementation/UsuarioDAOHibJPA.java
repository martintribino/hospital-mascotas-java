package ttps.spring.implementation;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttps.spring.dao.IUsuarioDAO;
import ttps.spring.model.Usuario;

@Repository
@Transactional
public class UsuarioDAOHibJPA extends GenericDAOHibJPA<Usuario>
implements IUsuarioDAO {

	/**
	 * Usuario DAO Hibernate JPA Implementation
	 */
	
	private static final long serialVersionUID = 1L;

	public UsuarioDAOHibJPA() {
		super(Usuario.class);
	}

	@Override
	public List<Usuario> recuperarUsuario(String userName) {
		Query consulta = this.getEntityManager()
				.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsu");
		consulta.setParameter("nombreUsu", userName);
		return (List<Usuario>) consulta.getResultList();
	}

	@Override
	public List<Usuario> recuperarUsuarios() {
		return (List<Usuario>) this.listar("id", "desc");
	}

	@Override
	public Usuario recuperarUsuarioPorNombre(String userName) {
		List<Usuario> lista = this.recuperarUsuario(userName);
		Usuario usuario = null;
		if (!lista.isEmpty()) {
			usuario = lista.get(0);
		}
        return usuario;
	}

}
