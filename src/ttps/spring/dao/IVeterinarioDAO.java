package ttps.spring.dao;

import java.util.List;

import ttps.spring.model.Veterinario;

public interface IVeterinarioDAO extends IGenericDAO<Veterinario> {
	public List<Veterinario> recuperarVeterinario(String userName);
	public List<Veterinario> recuperarVeterinarios();
}
