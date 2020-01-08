package ttps.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class MethodArgumentNotValidException extends RuntimeException {

	/**
	 * MethodArgumentNotValidException
	 */
	private static final long serialVersionUID = 6629770277232125795L;

	public MethodArgumentNotValidException(String exception) {
        super(exception);
    }

}
