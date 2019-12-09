package ttps.spring.dao;

import java.util.List;

import ttps.spring.model.Mascota;

public interface IMascotaDAO extends IGenericDAO<Mascota> {
	public List<Mascota> recuperarMascotas();
	public List<Mascota> recuperarMascotasPorDuenio(long id);
	public List<Mascota> recuperarMascotasPorVeterinario(long id);
}
