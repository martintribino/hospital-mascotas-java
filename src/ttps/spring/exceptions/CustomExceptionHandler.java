package ttps.spring.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("User Not Found", details);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(UnauthorizedException.class)
    public final ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Unauthorized", details);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.UNAUTHORIZED);
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
	
	@ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Bad Request", details);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("Argument not valid.", details);
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

}
