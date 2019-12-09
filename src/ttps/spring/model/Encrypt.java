package ttps.spring.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encrypt {
	private static BCryptPasswordEncoder bcryptInstance;
	
	public static BCryptPasswordEncoder getInstance() {
	    if (bcryptInstance == null) {
	        createInstance();
	    }
	    return bcryptInstance;
	}

	private static synchronized void createInstance() { // for multithreading
	    if (bcryptInstance == null) {
	    	bcryptInstance = new BCryptPasswordEncoder(9);
	    }
	}

	public static String encode(String clave){
		BCryptPasswordEncoder instance = Encrypt.getInstance();
		return instance.encode(clave);
	}

	public static Boolean matches(String clave, String claveCodificada){
		BCryptPasswordEncoder instance = Encrypt.getInstance();
		return instance.matches((CharSequence)clave, claveCodificada);
	}
}
