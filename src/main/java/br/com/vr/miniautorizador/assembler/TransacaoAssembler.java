package br.com.vr.miniautorizador.assembler;

import br.com.vr.miniautorizador.dto.entrada.TransacaoRequestDTO;
import br.com.vr.miniautorizador.model.Transacao;
import br.com.vr.miniautorizador.util.CryptoUtils;

public class TransacaoAssembler {

	public Transacao montarTransacaoRequestDTOToModel(TransacaoRequestDTO dto) {
		Transacao transacao = new Transacao();

		transacao.setNumeroCartao(dto.getNumeroCartao());
		transacao.setSenha(CryptoUtils.encryptPassword(dto.getSenha()));
		transacao.setValor(dto.getValor());

		return transacao;
	}

}
