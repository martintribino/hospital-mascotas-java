package ttps.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class DuplicateEntityException extends RuntimeException {

	/**
	 * DuplicateEntityException
	 */
	private static final long serialVersionUID = -8930017468382236111L;

	public DuplicateEntityException(String exception) {
        super(exception);
    }

}