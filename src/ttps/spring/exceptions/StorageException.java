package ttps.spring.exceptions;

public class StorageException extends RuntimeException {

	/**
	 * StorageException
	 */
	private static final long serialVersionUID = 6498635218871926431L;

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}

}
