package ttps.spring.implementation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ttps.spring.dao.IGenericDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class GenericDAOHibJPA<T> implements Serializable, IGenericDAO<T> {

	/**
	 * Generic DAO implementation
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager entityManager;
	private Class<T> entityClass;
	
	public GenericDAOHibJPA(Class<T> clase) {
		this.entityClass = clase;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public T guardar(T entity) {
		this.getEntityManager().persist(entity);
		return entity;
	}

	@Override
	public T actualizar(T entity) {
		this.getEntityManager().merge(entity);
		return entity;
	}

	@Override
	public T encontrar(Serializable id) {
		T objReturn = (T) this.getEntityManager().find(this.entityClass, id);
		return objReturn;
	}

	@Override
	public Boolean existe(T entity) {
		return this.getEntityManager().contains(entity);
	}

	@Override
	public Boolean existe(Serializable id) {
		return this.encontrar(id) != null;
	}

	@Override
	public void eliminar(T entity) {
		this.getEntityManager().remove(
				this.getEntityManager().contains(entity) ? entity :
						this.getEntityManager().merge(entity));
	}
	
	public T eliminar(Serializable id) {
		 T entity = this.getEntityManager().find(this.entityClass, id);
		 if (entity != null) {
			 this.eliminar(entity);
		 }
		 return entity;
	}

	@Override
	public List<T> listar(String columnOrder, String order) {
		List<T> list = new ArrayList<T>();
		String strQuery = String.format("SELECT m FROM %s m ORDER BY m.%s %s",
				this.entityClass.getSimpleName(), columnOrder, order);
		Query q = this.getEntityManager().createQuery(strQuery);
		list = (List<T>) q.getResultList();
		return list;
	}

	@Override
	public List<T> ejecutarQuery(String query) {
		List<T> list = new ArrayList<T>();
		System.out.println(query);
		System.out.println(this.getEntityManager());
		Query q = this.getEntityManager().createQuery(query);
		System.out.println(q);
		list = (List<T>) q.getResultList();
		return list;
	}

}
