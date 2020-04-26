package ttps.spring.requests;

import ttps.spring.model.Recordatorio;

public class RecordatorioReqBody extends EventoReqBody {
	
	private Recordatorio evento;

	public RecordatorioReqBody() {
		// TODO Auto-generated constructor stub
	}

	public Recordatorio getEvento() {
		return evento;
	}

	public void setEvento(Recordatorio evento) {
		this.evento = evento;
	}

}
