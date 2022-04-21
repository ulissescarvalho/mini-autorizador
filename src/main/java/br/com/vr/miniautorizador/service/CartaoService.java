package br.com.vr.miniautorizador.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.vr.miniautorizador.dto.entrada.CartaoRequestDTO;
import br.com.vr.miniautorizador.dto.saida.CartaoResponseDTO;
import br.com.vr.miniautorizador.mapper.CartaoAssembler;
import br.com.vr.miniautorizador.model.Cartao;
import br.com.vr.miniautorizador.repository.CartaoRepository;

@Service
public class CartaoService {

	private final CartaoRepository cartaoRepository;

	public CartaoService(CartaoRepository cartaoRepository) {
		this.cartaoRepository = cartaoRepository;
	}

	public Optional<CartaoResponseDTO> create(CartaoRequestDTO dto) {
		CartaoAssembler assembler = new CartaoAssembler();
		Cartao model = assembler.criarCartaoRequestDTOToEntity(dto);
		cartaoRepository.findById(model.getNumeroCartao()).ifPresentOrElse((c) -> {
			throw new RuntimeException("Cartao ja existente");
		}, () -> {
			Cartao cartao = configurarNovoCartao(model);
			cartaoRepository.insert(cartao);
		});
		return Optional.of(assembler.montarCartaoResponseDTO(model));
	}

	private Cartao configurarNovoCartao(Cartao cartao) {
		cartao.setSaldo(new BigDecimal("500.0"));
		return cartao;
	}

	public BigDecimal recuperarSaldo(String numeroCartao) {
		// TODO verificar se cartao existe
		return cartaoRepository.findById(numeroCartao).get().getSaldo();
	}

}
