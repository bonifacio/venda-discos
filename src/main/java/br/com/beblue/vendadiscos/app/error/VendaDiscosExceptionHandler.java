package br.com.beblue.vendadiscos.app.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.beblue.vendadiscos.domain.exception.BusinessException;

@ControllerAdvice
public class VendaDiscosExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Message> businessException(BusinessException e) {
		
		return new ResponseEntity<>(new Message(e.getMessage()), HttpStatus.BAD_REQUEST);
	}
}
