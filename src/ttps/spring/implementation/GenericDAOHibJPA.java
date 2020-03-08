package ttps.spring.implementation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ttps.spring.dao.IGenericDAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class GenericDAOHibJPA<T> implements Serializable, IGenericDAO<T> {

	/**
	 * GenericDAOHibJPA
	 */
	private static final long serialVersionUID = 6813210425398282761L;
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
	public T encontrar(Long id) {
		T objReturn = (T) this.getEntityManager().find(this.entityClass, id);
		return objReturn;
	}

	@Override
	public T obtenerReferencia(Long id) {
		T objReturn = (T) this.getEntityManager().getReference(this.entityClass, id);
		return objReturn;
	}

	@Override
	public Boolean existe(T entity) {
		return this.getEntityManager().contains(entity);
	}

	@Override
	public Boolean existe(Long id) {
		return this.encontrar(id) != null;
	}

	@Override
	public void eliminar(T entity) {
		this.getEntityManager().remove(
				this.getEntityManager().contains(entity) ? entity :
						this.getEntityManager().merge(entity));
	}
	
	public T eliminar(Long id) {
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
		TypedQuery<T> q = this.getEntityManager().createQuery(strQuery, this.entityClass);
		list = (List<T>) q.getResultList();
		return list;
	}

}
