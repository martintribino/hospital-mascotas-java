package ttps.spring.exceptions;

public class StorageFileNotFoundException extends StorageException {

	/**
	 * StorageFileNotFoundException
	 */
	private static final long serialVersionUID = 2869697295077404175L;

	public StorageFileNotFoundException(String message) {
		super(message);
	}

	public StorageFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
