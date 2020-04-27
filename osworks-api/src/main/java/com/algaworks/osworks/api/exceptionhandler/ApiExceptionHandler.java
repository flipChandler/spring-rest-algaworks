package com.algaworks.osworks.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	// QLQ CONTROLADOR QUE CAIR EM UMA EXCEPTION, VAI CHAMAR ESSA CLASSE
	//CUSTOMIZAR AS EXCEPTIONS DA API
	
	@Autowired
	private MessageSource messageSource;
	
	
	@Override // EXCEÇÕES DE CAMPOS NOT BLANK, NOT NULL.. 
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		var campos = new ArrayList<Problema.Campo>();
		
		for ( ObjectError error : ex.getBindingResult().getAllErrors() ) {
			String nomeCampo = ( (FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			campos.add(new Problema.Campo(nomeCampo, mensagem));
		}
		
		var problema = new Problema();
		problema.setStatus(status.value());
		problema.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente!");
		problema.setDataHora(LocalDateTime.now());	
		problema.setCampos(campos);

		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}
	

}
