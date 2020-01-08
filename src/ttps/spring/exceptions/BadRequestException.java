package ttps.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	/**
	 * BadRequestException
	 */
	private static final long serialVersionUID = -6332060562761205774L;

	public BadRequestException(String exception) {
        super(exception);
    }

}
