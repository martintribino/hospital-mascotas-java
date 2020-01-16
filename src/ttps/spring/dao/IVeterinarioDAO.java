package ttps.spring.dao;

import java.util.List;

import ttps.spring.dao.IGenericDAO;
import ttps.spring.model.Veterinario;

public interface IVeterinarioDAO extends IGenericDAO<Veterinario> {
	public List<Veterinario> recuperarVeterinariosXValidacion(Boolean validados);
	public List<Veterinario> recuperarVeterinarios();
}
