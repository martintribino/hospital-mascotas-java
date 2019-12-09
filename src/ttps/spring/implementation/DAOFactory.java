package ttps.spring.implementation;

import org.springframework.beans.factory.annotation.Autowired;

public class DAOFactory {

	@Autowired
	private static AdministradorDAOHibJPA adminDAO;
	
	public static AdministradorDAOHibJPA getAdministradorDAO() {
		return adminDAO;
	}
	
	public static DuenioDAOHibJPA getDuenioDAO() {
		return new DuenioDAOHibJPA();
	}
	
	public static EventoDAOHibJPA getEventoDAO() {
		return new EventoDAOHibJPA();
	}

	public static MascotaDAOHibJPA getMacotaDAO() {
		return new MascotaDAOHibJPA();
	}

	public static SolicitudDAOHibJPA getSolicitudDAO() {
		return new SolicitudDAOHibJPA();
	}

	public static VeterinarioDAOHibJPA getVeterinarioDAO() {
		return new VeterinarioDAOHibJPA();
	}
}
