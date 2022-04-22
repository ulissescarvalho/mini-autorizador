package br.com.vr.miniautorizador.excecao.mapper;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.vr.miniautorizador.controller.CartaoController;
import br.com.vr.miniautorizador.excecao.CartaoRuntimeException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = { CartaoController.class })
public class CartaoControllerExceptionMapper extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { CartaoRuntimeException.class })
	public ResponseEntity<?> toResponse() {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartao ja existente");
	}
}
