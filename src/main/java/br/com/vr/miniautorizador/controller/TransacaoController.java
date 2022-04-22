package br.com.vr.miniautorizador.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vr.miniautorizador.dto.entrada.TransacaoRequestDTO;
import br.com.vr.miniautorizador.excecao.MiniAutorizadorNegocioException;
import br.com.vr.miniautorizador.service.TransacaoService;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

	private final TransacaoService service;

	public TransacaoController(TransacaoService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<?> realizarTransacao(@Valid @RequestBody TransacaoRequestDTO dto) {
		try {
			service.realizarTransacao(dto);
			return ResponseEntity.ok("Sucesso").status(HttpStatus.CREATED).build();
		} catch (MiniAutorizadorNegocioException e) {
			return ResponseEntity.ok(e.getMessage()).status(HttpStatus.BAD_REQUEST).build();
		}
	}

}
