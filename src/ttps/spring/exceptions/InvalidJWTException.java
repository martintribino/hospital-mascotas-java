package ttps.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.jsonwebtoken.JwtException;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class InvalidJWTException extends JwtException {
    /**
	 * InvalidJWTException
	 */
	private static final long serialVersionUID = -8576112525288199425L;

	public InvalidJWTException(String message) {
        super(message);
    }

    public InvalidJWTException(String message, Throwable cause) {
        super(message, cause);
    }

}
