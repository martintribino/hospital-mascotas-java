package ttps.spring.requests;

import ttps.spring.model.Desparasitacion;

public class DesparasitacionReqBody extends EventoReqBody {

	private Desparasitacion evento;

	public DesparasitacionReqBody() {
		// TODO Auto-generated constructor stub
	}

	public Desparasitacion getEvento() {
		return evento;
	}

	public void setEvento(Desparasitacion evento) {
		this.evento = evento;
	}

}
