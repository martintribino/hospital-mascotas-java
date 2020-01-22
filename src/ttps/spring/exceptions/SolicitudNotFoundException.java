package ttps.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SolicitudNotFoundException extends RuntimeException {

	/**
	 * SolicitudNotFoundException
	 */
	private static final long serialVersionUID = 7157287992462633145L;

	public SolicitudNotFoundException(String exception) {
        super(exception);
	}

}
