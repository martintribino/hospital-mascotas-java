package ttps.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserInvalidKeyException extends RuntimeException {

	/**
	 * UserInvalidKeyException
	 */
	private static final long serialVersionUID = 8018968152871484183L;

	public UserInvalidKeyException(String exception) {
        super(exception);
	}

}
