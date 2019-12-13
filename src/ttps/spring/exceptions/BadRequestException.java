package ttps.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.jsonwebtoken.JwtException;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends JwtException {

	/**
	 * BadRequestException
	 */
	private static final long serialVersionUID = 1735632041083681949L;

	public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
