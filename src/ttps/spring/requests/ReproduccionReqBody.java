package ttps.spring.requests;

import ttps.spring.model.Reproduccion;

public class ReproduccionReqBody extends EventoReqBody {

	private Reproduccion evento;

	public ReproduccionReqBody() {
		// TODO Auto-generated constructor stub
	}

	public Reproduccion getEvento() {
		return evento;
	}

	public void setEvento(Reproduccion evento) {
		this.evento = evento;
	}

}
