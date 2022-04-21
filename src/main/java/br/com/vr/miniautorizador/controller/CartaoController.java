package br.com.vr.miniautorizador.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vr.miniautorizador.dto.entrada.CartaoRequestDTO;
import br.com.vr.miniautorizador.service.CartaoService;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

	private final CartaoService service;

	public CartaoController(CartaoService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<?> criarCartao(@Valid @RequestBody CartaoRequestDTO dto) {
		return ResponseEntity.ok(service.create(dto));
	}

	@GetMapping("{numeroCartao}")
	public ResponseEntity<BigDecimal> consultarSaldo(@PathVariable String numeroCartao) {
		BigDecimal saldo = service.recuperarSaldo(numeroCartao);
		return ResponseEntity.ok(saldo);
	}

}
