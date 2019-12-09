package ttps.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	/**
	 * UserNotFoundException
	 */
	private static final long serialVersionUID = 4759163418221122290L;

	public UserNotFoundException(String exception) {
        super(exception);
	}

}
