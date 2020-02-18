package ttps.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class TurnoNoValidoException extends RuntimeException {

	/**
	 * TurnoNoValidoException
	 */
	private static final long serialVersionUID = -868466232942291538L;

	public TurnoNoValidoException(String exception) {
        super(exception);
	}

}
