package ttps.spring.dao;

import java.util.List;

import ttps.spring.dao.IGenericDAO;
import ttps.spring.model.Mascota;
import ttps.spring.model.Veterinario;

public interface IMascotaDAO extends IGenericDAO<Mascota> {
	public Mascota encontrar(String slug);
	public void agregarVeterinario(Mascota mascota, Veterinario veterinario);
	public void removerVeterinario(Mascota mascota);
	public List<Mascota> recuperarMascotas();
	public List<Mascota> recuperarMascotasPorDuenio(long id);
	public List<Mascota> recuperarMascotasPorVeterinario(long id);
}
