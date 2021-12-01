package com.projeto.Controladores.Excecoes;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.projeto.Servicos.Excecoes.ErroRegistroInexistente;

@ControllerAdvice
public class ManipuladorExcecoesControlodores {
	
	@ExceptionHandler(ErroRegistroInexistente.class)
	public ResponseEntity<ErroPadrao> registroNaoExite(ErroRegistroInexistente e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErroPadrao err = new ErroPadrao();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setErro("Registro inexistente");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
