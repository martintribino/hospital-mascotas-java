package ttps.spring.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDAO<T> {

	/**
	 * Generic DAO interface
	 */

	T guardar(T entity);
	T actualizar(T entity);
	Boolean existe(Serializable id);
	Boolean existe(T entity);
	T encontrar(Serializable id);
	void eliminar(T entity);
	T eliminar(Serializable id);
	List<T> listar(String columnOrder, String order);
}
