package ttps.spring.dao;

import java.util.List;

import ttps.spring.dao.IGenericDAO;
import ttps.spring.model.Mascota;
import ttps.spring.model.Veterinario;

public interface IMascotaDAO extends IGenericDAO<Mascota> {
	public Mascota encontrar(String slug);
	public void agregarVeterinario(Mascota mascota, Veterinario veterinario);
	public void removerVeterinario(Mascota mascota);
	void actualizarMascota(Mascota mascota, String imagen);
	public List<Mascota> recuperarMascotas();
	public List<Mascota> recuperarMascotasExtraviadas();
	public List<Mascota> recuperarMascotasPorDuenio(long id);
	public List<Mascota> recuperarMascotasPorVeterinario(long id);
	public List<Mascota> recuperarMascotasPorDuenioCriteria(long id, String criteria, String search);
	public List<Mascota> recuperarMascotasPorVeterinarioCriteria(long id, String criteria, String search);
}
