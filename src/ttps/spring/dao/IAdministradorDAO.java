package ttps.spring.dao;

import java.util.List;

import ttps.spring.model.Administrador;

public interface IAdministradorDAO extends IGenericDAO<Administrador> {
	public List<Administrador> recuperarAdministrador(String userName);
	public List<Administrador> recuperarAdministradores();
}
