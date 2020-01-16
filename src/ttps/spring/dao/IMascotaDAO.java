package ttps.spring.dao;

import java.util.List;

import ttps.spring.dao.IGenericDAO;
import ttps.spring.model.Mascota;

public interface IMascotaDAO extends IGenericDAO<Mascota> {
	public Mascota encontrar(String slug);
	public List<Mascota> recuperarMascotas();
	public List<Mascota> recuperarMascotasPorDuenio(long id);
	public List<Mascota> recuperarMascotasPorVeterinario(long id);
}
