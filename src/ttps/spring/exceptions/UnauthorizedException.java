package ttps.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

	/**
	 * UnauthorizedException
	 */
	private static final long serialVersionUID = 1021961146546608764L;

	public UnauthorizedException(String exception) {
        super(exception);
	}

}
