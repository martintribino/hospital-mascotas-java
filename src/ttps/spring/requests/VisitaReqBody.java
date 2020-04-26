package ttps.spring.requests;

import ttps.spring.model.Visita;

public class VisitaReqBody extends EventoReqBody {

	private Visita evento;

	public VisitaReqBody() {
		// TODO Auto-generated constructor stub
	}

	public Visita getEvento() {
		return evento;
	}

	public void setEvento(Visita evento) {
		this.evento = evento;
	}

}
