package br.com.vr.miniautorizador.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.vr.miniautorizador.assembler.CartaoAssembler;
import br.com.vr.miniautorizador.dto.entrada.CartaoRequestDTO;
import br.com.vr.miniautorizador.dto.saida.CartaoResponseDTO;
import br.com.vr.miniautorizador.excecao.MiniAutorizadorNegocioException;
import br.com.vr.miniautorizador.excecao.CartaoRuntimeException;
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
		Cartao model = assembler.criarCartaoRequestDTOToModel(dto);
		cartaoRepository.findById(model.getNumeroCartao()).ifPresentOrElse((c) -> {
			throw new CartaoRuntimeException("Cartao ja existente");
		}, () -> {
			configurarNovoCartao(model);
			cartaoRepository.insert(model);
		});
		return Optional.of(assembler.montarCartaoResponseDTO(model));
	}

	private void configurarNovoCartao(Cartao cartao) {
		cartao.setSaldo(new BigDecimal("500.0"));
	}

	public BigDecimal recuperarSaldo(String numeroCartao) throws MiniAutorizadorNegocioException {
		Optional<Cartao> optCartao = Optional.ofNullable(cartaoRepository.findById(numeroCartao))
				.orElseThrow(() -> new MiniAutorizadorNegocioException("Cartao nao encontrado"));

		return optCartao.get().getSaldo();
	}

}
