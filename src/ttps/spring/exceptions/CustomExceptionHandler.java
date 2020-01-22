package ttps.spring.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

	@ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("User Not Found", details);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(MascotaNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleMascotaNotFoundException(MascotaNotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Mascota Not Found", details);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(SolicitudNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleSolicitudNotFoundException(SolicitudNotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Solicitud Not Found", details);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(UnauthorizedException.class)
    public final ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Unauthorized", details);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.UNAUTHORIZED);
    }

	@ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Bad Request", details);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(UserInvalidKeyException.class)
    public final ResponseEntity<ErrorResponse> handleUserInvalidKeyException(UserInvalidKeyException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("User invalid key", details);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(ExpiredJwtException.class)
    public final ResponseEntity<ErrorResponse> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Token expired", details);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.UNAUTHORIZED);
    }

	@ExceptionHandler(InvalidJWTException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidJWTException(InvalidJWTException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Token error", details);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.UNAUTHORIZED);
    }

	@ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Argument not valid", details);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler({
	    javax.validation.ConstraintViolationException.class,
	    org.hibernate.exception.ConstraintViolationException.class,
	    SQLIntegrityConstraintViolationException.class,
		DataIntegrityViolationException.class
		})
	public ResponseEntity<ErrorResponse> handleConstraintViolation(
	  ConstraintViolationException ex, WebRequest request) {
	    List<String> errors = new ArrayList<String>();
	    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
	        errors.add(violation.getRootBeanClass().getName() + " " + 
	          violation.getPropertyPath() + ": " + violation.getMessage());
	    }
        ErrorResponse error = new ErrorResponse("Constraint Violation.", errors);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatch(
	  MethodArgumentTypeMismatchException ex, WebRequest request) {
	    String errorMismatch = 
	      ex.getName() + " debería ser del tipo " + ex.getRequiredType().getName();
	    List<String> errors = new ArrayList<String>();
	    errors.add(errorMismatch);
        ErrorResponse error = new ErrorResponse("Argument mismatch", errors);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidFormatException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidFormat(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
	    List<String> errors = new ArrayList<String>();
	    errors.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Invalid format", errors);
        return new ResponseEntity<ErrorResponse>(error,HttpStatus.NOT_ACCEPTABLE);
    }

	@ExceptionHandler(PersistenceException.class)
    public final ResponseEntity<ErrorResponse> handlePersistenceException(PersistenceException ex, WebRequest request){
	    List<String> errors = new ArrayList<String>();
	    errors.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Persistence error.", errors);
        return new ResponseEntity<ErrorResponse>(error,HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(DuplicateEntityException.class)
    public final ResponseEntity<ErrorResponse> handleDuplicateEntityException(DuplicateEntityException ex, WebRequest request){
	    List<String> errors = new ArrayList<String>();
	    errors.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Duplicated Entity.", errors);
        return new ResponseEntity<ErrorResponse>(error,HttpStatus.CONFLICT);
    }

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
	  MissingServletRequestParameterException ex, HttpHeaders headers, 
	  HttpStatus status, WebRequest request) {
	    String errorMissing = ex.getParameterName() + " parameter is missing";
	    List<String> errors = new ArrayList<String>();
	    errors.add(errorMissing);
        ErrorResponse error = new ErrorResponse("Constraint Violation", errors);
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	    List<String> errors = new ArrayList<String>();
	    errors.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Not found", errors);
        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
    }

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<String>();
	    errors.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Invalid format", errors);
        return new ResponseEntity<Object>(error,HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		pageNotFoundLogger.warn(ex.getMessage());
		Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
		if (!CollectionUtils.isEmpty(supportedMethods)) {
			headers.setAllow(supportedMethods);
		}
		List<String> errors = new ArrayList<String>();
	    errors.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Method not supported", errors);
        return new ResponseEntity<Object>(error,headers,HttpStatus.NOT_ACCEPTABLE);
	}

}
