package ttps.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class MascotaNotFoundException extends RuntimeException {

	/**
	 * MascotaNotFoundException
	 */
	private static final long serialVersionUID = 8445696547678153184L;

	public MascotaNotFoundException(String exception) {
        super(exception);
	}

}
