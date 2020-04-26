package ttps.spring.helpers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import ttps.spring.dao.IAdministradorDAO;
import ttps.spring.dao.IDuenioDAO;
import ttps.spring.dao.IEventoDAO;
import ttps.spring.dao.IMascotaDAO;
import ttps.spring.dao.ISolicitudDAO;
import ttps.spring.dao.IUsuarioDAO;
import ttps.spring.dao.IVeterinarioDAO;
import ttps.spring.model.Administrador;
import ttps.spring.model.Duenio;
import ttps.spring.model.Encrypt;
import ttps.spring.model.Enfermedad;
import ttps.spring.model.Evento;
import ttps.spring.model.Intervencion;
import ttps.spring.model.Mascota;
import ttps.spring.model.Recordatorio;
import ttps.spring.model.Reproduccion;
import ttps.spring.model.Solicitud;
import ttps.spring.model.Turno;
import ttps.spring.model.Vacuna;
import ttps.spring.model.Veterinario;
import ttps.spring.model.Visita;
import ttps.spring.rest.services.StorageService;


@Component
@EnableConfigurationProperties(StorageProperties.class)
public class InitialDataApplication implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger log = LoggerFactory.getLogger(InitialDataApplication.class);

	@Autowired
    private IAdministradorDAO adminRepository;
	@Autowired
    private IVeterinarioDAO vetRepository;
	@Autowired
    private IDuenioDAO dueRepository;
	@Autowired
    private IMascotaDAO mascRepository;
	@Autowired
    private ISolicitudDAO solRepository;
	@Autowired
    private IUsuarioDAO usuRepository;
	@Autowired
    private IEventoDAO evRepository;
	@Autowired
	private StorageService storageService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		storageService.init();
		String pass = Encrypt.encode("admin123");
		//administrador
		Administrador a = new Administrador(
				"admin", "Administrador", "Admin Ape", pass, "admin@admin.ad",
				12365487, 123654, "Domicilio"
			);
		if(usuRepository.recuperarUsuarioPorNombre("admin") == null) {
			// guardar un administrador
			try
			{
				adminRepository.guardar(a);
				log.info("------------------------------------");
				log.info("El Administrador fue creado");
				log.info("------------------------------------");
			}
			catch(Exception ex)
			{
				log.info("------------------------------------");
				log.info("El Administrador ya existe");
				log.info("------------------------------------");
			}
		}
		pass = Encrypt.encode("vet123");
		//veterinarios
		Veterinario v1 = new Veterinario(
				"vet1", "Veterinario", "Vet1 Ape", pass, "vet@vet.ve", 12360487, 123054,
				"Domicilio", "Nombre clinica", "Domicilio Clinica", true
			);
		Veterinario v2 = new Veterinario(
				"vet2", "Veterinario2", "Vet2 Ape", pass, "vet2@vet.ve", 12360287, 223054,
				"Domicilio", "Nombre clinica", "Domicilio Clinica", false
			);
		// guardar veterinarios
		Boolean existeV1 = usuRepository.recuperarUsuarioPorNombre("vet1") == null;
		Boolean existeV2 = usuRepository.recuperarUsuarioPorNombre("vet2") == null;
		try
		{
			if(existeV1)
				vetRepository.guardar(v1);
			if(existeV2)
				vetRepository.guardar(v2);
			log.info("------------------------------------");
			log.info("Veterinarios creados");
			log.info("------------------------------------");
		}
		catch(Exception ex)
		{
			log.info("------------------------------------");
			log.info("Veterinarios existentes");
			log.info("------------------------------------");
		}
		pass = Encrypt.encode("due123");
		//Duenios
		Duenio d1 = new Duenio(
				"due1", "Duenio 1", "Due1 Ape", pass, "due@due.due", 12365387, 1234554,
				"Domicilio", new ArrayList<Mascota>()
			);
		Duenio d2 = new Duenio(
				"due2", "Duenio 2", "Due2 Ape", pass, "due2@due.due", 15565387, 1288554,
				"Domicilio2", new ArrayList<Mascota>()
			);
		Boolean existsDue1 = usuRepository.recuperarUsuarioPorNombre("due1") != null;
		Boolean existsDue2 = usuRepository.recuperarUsuarioPorNombre("due2") != null;
		// guardar duenios
		try
		{
			if(!existsDue1)
				dueRepository.guardar(d1);
			if(!existsDue2)
				dueRepository.guardar(d2);
			log.info("------------------------------------");
			log.info("Dueños creados");
			log.info("------------------------------------");
		}
		catch(Exception ex)
		{
			log.info("------------------------------------");
			log.info("Dueños existentes");
			log.info("------------------------------------");
		}
		Date today = Calendar.getInstance().getTime();
		Boolean existsMasc1 = false;
		if(!existsDue1 && !existsDue2) {
			// Si due1 y due2 on habian sido creados
			// guardar mascotas
			//Mascotas
			Mascota m1 = new Mascota(
					"Masc 1", today, "Pointer", "Pointer", "Masculino", "marrón",
					"", "", false, d1
				);
			m1.setVeterinario(v1);
			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			today = Calendar.getInstance().getTime();
			Mascota m2 = new Mascota(
					"Masc 2", today, "Pointer2", "Pointer2", "Masculino", "marrón",
					"", "", false, d1
				);
			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			today = Calendar.getInstance().getTime();
			Mascota m3 = new Mascota(
					"Masc 3", today, "Salchicha", "Salchicha", "Femenino", "blanca",
					"", "", false, d1
				);
			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			today = Calendar.getInstance().getTime();
			Mascota m4 = new Mascota(
					"Masc 4", today, "Salchicha2", "Salchicha2", "Femenino", "negra",
					"", "", false, d1
				);
			try
			{
				m1.setExtraviada(true);
				mascRepository.guardar(m1);
				existsMasc1 = true;
				m2.setExtraviada(true);
				mascRepository.guardar(m2);
				mascRepository.guardar(m3);
				mascRepository.guardar(m4);
				log.info("------------------------------------");
				log.info("Mascotas creadas");
				log.info("------------------------------------");
				// guardar solicitudes
				Solicitud s1 = new Solicitud();
				s1.setMascota(m2);
				s1.setVeterinario(v1);
				solRepository.guardar(s1);
				log.info("------------------------------------");
				log.info("Solicitudes creadas");
				log.info("------------------------------------");
			}
			catch(Exception ex)
			{
				log.info("------------------------------------");
				log.info("Mascotas y Solicitudes existentes");
				log.info("------------------------------------");
			}
			if(existsMasc1 && existeV1) {
				LocalDate fechaAyer;
				LocalDate fecha;
				LocalDate fechaMañana;
				LocalTime inicio = LocalTime.of(9, 30);
				LocalTime fin = LocalTime.of(9, 59);
				if(LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY) {
					fechaAyer = LocalDate.now().minusDays(2);
					fecha = LocalDate.now().plusDays(1);
					fechaMañana = LocalDate.now().plusDays(2);
				} else if(LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY) {
					fechaAyer = LocalDate.now().minusDays(1);
					fecha = LocalDate.now().plusDays(2);
					fechaMañana = LocalDate.now().plusDays(3);
				} else {
					fecha = LocalDate.now();
					fechaAyer = LocalDate.now().minusDays(1);
					fechaMañana = LocalDate.now().plusDays(1);
				}
				Evento enf = new Enfermedad(
						fecha, inicio, fin, "Enfermedad", m1
					);
				try
				{
					evRepository.guardar(enf);
					log.info("------------------------------------");
					log.info("Enfermedad creada");
					log.info("------------------------------------");
				}
				catch(Exception ex)
				{
					log.info("------------------------------------");
					log.info("Enfermedad existente");
					log.info("------------------------------------");
				}
				inicio = LocalTime.of(10, 00);
				fin = LocalTime.of(10, 29);
				Evento inter = new Intervencion(
						fecha, inicio, fin, "Intervencion",  m1
					);
				try
				{
					evRepository.guardar(inter);
					log.info("------------------------------------");
					log.info("Intervencion creada");
					log.info("------------------------------------");
				}
				catch(Exception ex)
				{
					log.info("------------------------------------");
					log.info("Intervencion existente");
					log.info("------------------------------------");
				}
				Reproduccion rep = new Reproduccion(
						fechaMañana, inicio, fin, "Reproduccion", m1, new Date(),2
					);
				try
				{
					evRepository.guardar(rep);
					log.info("------------------------------------");
					log.info("Reproduccion creada");
					log.info("------------------------------------");
				}
				catch(Exception ex)
				{
					log.info("------------------------------------");
					log.info("Reproduccion existente");
					log.info("------------------------------------");
				}
				inicio = LocalTime.of(11, 00);
				fin = LocalTime.of(11, 29);
				Vacuna vacuna = new Vacuna(
						fechaMañana, inicio, fin, "Vacuna numero 1", m1
					);
				try
				{
					evRepository.guardar(vacuna);
					log.info("------------------------------------");
					log.info("Vacuna creada");
					log.info("------------------------------------");
				}
				catch(Exception ex)
				{
					log.info("------------------------------------");
					log.info("Vacuna existente");
					log.info("------------------------------------");
				}
				inicio = LocalTime.of(11, 30);
				fin = LocalTime.of(11, 59);
				Recordatorio recordatorio = new Recordatorio(
						fechaMañana, inicio, fin, "Recordatorio", m1
					);
				try
				{
					evRepository.guardar(recordatorio);
					log.info("------------------------------------");
					log.info("Recordatorio creada");
					log.info("------------------------------------");
				}
				catch(Exception ex)
				{
					log.info("------------------------------------");
					log.info("Recordatorio existente");
					log.info("------------------------------------");
				}
				Vacuna vacuna2 = new Vacuna(
						fechaAyer, inicio, fin, "Vacuna numero 2", m1
					);
				if (vacuna2 != null && vacuna2.getTurno() != null )
					vacuna2.getTurno().setEstado(Turno.Estados.NOCONCURRIO);
				try
				{
					evRepository.guardar(vacuna2);
					log.info("------------------------------------");
					log.info("Vacuna 2 creada");
					log.info("------------------------------------");
				}
				catch(Exception ex)
				{
					log.info("------------------------------------");
					log.info("Vacuna 2 existente");
					log.info("------------------------------------");
				}
				inicio = LocalTime.of(11, 30);
				fin = LocalTime.of(11, 59);
				Visita visita = new Visita(
						fechaAyer, inicio, fin, "Visita nro 1", m1, "peso", "motivo", "diag", "ind"
					);
				if (visita != null && visita.getTurno() != null )
					visita.getTurno().setEstado(Turno.Estados.CANCELADO);
				try
				{
					evRepository.guardar(visita);
					log.info("------------------------------------");
					log.info("Visita creada");
					log.info("------------------------------------");
				}
				catch(Exception ex)
				{
					log.info("------------------------------------");
					log.info("Visita existente");
					log.info("------------------------------------");
				}
			}
		}
	}

}
