package ttps.spring.dao;

import java.util.List;

import ttps.spring.model.Administrador;
import ttps.spring.dao.IGenericDAO;

public interface IAdministradorDAO extends IGenericDAO<Administrador> {
	public List<Administrador> recuperarAdministradores();
}
