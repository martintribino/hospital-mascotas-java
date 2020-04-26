package ttps.spring.requests;

import ttps.spring.model.Enfermedad;

public class EnfermedadReqBody extends EventoReqBody {

	private Enfermedad evento;

	public EnfermedadReqBody() {
		// TODO Auto-generated constructor stub
	}

	public Enfermedad getEvento() {
		return evento;
	}

	public void setEvento(Enfermedad evento) {
		this.evento = evento;
	}

}
