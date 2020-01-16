package ttps.spring.dao;

import java.util.List;

import ttps.spring.dao.IGenericDAO;
import ttps.spring.model.Duenio;

public interface IDuenioDAO extends IGenericDAO<Duenio> {
	public List<Duenio> recuperarDuenio(String userName);
	public List<Duenio> recuperarDuenios();
}
