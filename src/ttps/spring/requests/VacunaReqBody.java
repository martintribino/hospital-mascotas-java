package ttps.spring.requests;

import ttps.spring.model.Vacuna;

public class VacunaReqBody extends EventoReqBody {

	private Vacuna evento;

	public VacunaReqBody() {
		// TODO Auto-generated constructor stub
	}

	public Vacuna getEvento() {
		return evento;
	}

	public void setEvento(Vacuna evento) {
		this.evento = evento;
	}

}
