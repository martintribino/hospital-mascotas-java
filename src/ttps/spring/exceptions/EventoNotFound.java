package ttps.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EventoNotFound extends RuntimeException {

	/**
	 * EventoNotFound
	 */
	private static final long serialVersionUID = -3611038454681942837L;

	public EventoNotFound(String exception) {
        super(exception);
	}

}
