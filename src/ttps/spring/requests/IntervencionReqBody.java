package ttps.spring.requests;

import ttps.spring.model.Intervencion;

public class IntervencionReqBody extends EventoReqBody {
	
	private Intervencion evento;

	public IntervencionReqBody() {
		// TODO Auto-generated constructor stub
	}

	public Intervencion getEvento() {
		return evento;
	}

	public void setEvento(Intervencion evento) {
		this.evento = evento;
	}

}
