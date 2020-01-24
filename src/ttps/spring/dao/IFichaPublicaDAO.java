package ttps.spring.dao;

import ttps.spring.model.FichaPublica;

public interface IFichaPublicaDAO extends IGenericDAO<FichaPublica> {
	public FichaPublica encontrar(String slug);
}
