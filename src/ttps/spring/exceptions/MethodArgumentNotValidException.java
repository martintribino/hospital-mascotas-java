package ttps.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.jsonwebtoken.JwtException;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class MethodArgumentNotValidException extends JwtException {

	/**
	 * MethodArgumentNotValidException
	 */
	private static final long serialVersionUID = 6629770277232125795L;

	public MethodArgumentNotValidException(String message) {
        super(message);
    }

    public MethodArgumentNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

}
