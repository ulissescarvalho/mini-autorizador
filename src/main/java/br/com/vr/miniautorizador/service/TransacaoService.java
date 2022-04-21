package br.com.vr.miniautorizador.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.vr.miniautorizador.assembler.TransacaoAssembler;
import br.com.vr.miniautorizador.dto.entrada.TransacaoRequestDTO;
import br.com.vr.miniautorizador.model.Cartao;
import br.com.vr.miniautorizador.model.Transacao;
import br.com.vr.miniautorizador.repository.CartaoRepository;
import br.com.vr.miniautorizador.repository.TransacaoRepository;
import br.com.vr.miniautorizador.util.CryptoUtils;

@Service
public class TransacaoService {

	private final TransacaoRepository transacaoRepository;
	private final CartaoRepository cartaoRepository;

	public TransacaoService(TransacaoRepository transacaoRepository, CartaoRepository cartaoRepository) {
		this.transacaoRepository = transacaoRepository;
		this.cartaoRepository = cartaoRepository;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void realizarTransacao(TransacaoRequestDTO dto) throws Exception {
		TransacaoAssembler assembler = new TransacaoAssembler();
		Optional<Cartao> optCartao = recuperarCartao(dto);

		Transacao transacao = assembler.montarTransacaoRequestDTOToModel(dto);
		validarSenha(optCartao, transacao);
		validarSaldo(optCartao, transacao);

		optCartao.ifPresent((cartao -> {
			atualizarSaldo(cartao, dto.getValor());
			Transacao transacaoPersistida = salvarTransacao(transacao, cartao);
			salvarCartao(transacaoPersistida, cartao);
		}));
	}

	private Transacao salvarTransacao(Transacao transacao, Cartao cartao) {
		transacao.setSaldoAtual(cartao.getSaldo());
		return transacaoRepository.insert(transacao);
	}

	private void salvarCartao(Transacao transacao, Cartao cartao) {
		cartao.getTransacoes().add(transacao);
		cartaoRepository.save(cartao);
	}

	private Cartao atualizarSaldo(Cartao cartao, BigDecimal valorTransacao) {
		cartao.setSaldo(cartao.getSaldo().subtract(valorTransacao));
		return cartao;
	}

	private Optional<Cartao> recuperarCartao(TransacaoRequestDTO dto) {
		return Optional.ofNullable(cartaoRepository.findById(dto.getNumeroCartao())
				.orElseThrow(() -> new RuntimeException("Cartao nao encontrado")));
	}

	private void validarSenha(Optional<Cartao> optionalCartao, Transacao transacao) throws Exception {
		optionalCartao.filter(cartao -> CryptoUtils.decryptPassword(cartao.getSenha())
				.equals(CryptoUtils.decryptPassword(transacao.getSenha()))).orElseThrow(Exception::new);
	}

	private void validarSaldo(Optional<Cartao> cartao, Transacao transacao) throws Exception {
		cartao.filter(c -> transacao.getValor().compareTo(c.getSaldo()) < 0).orElseThrow(Exception::new);
	}

}
