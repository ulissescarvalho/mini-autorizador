package br.com.vr.miniautorizador.assembler;

import br.com.vr.miniautorizador.dto.entrada.CartaoRequestDTO;
import br.com.vr.miniautorizador.dto.saida.CartaoResponseDTO;
import br.com.vr.miniautorizador.model.Cartao;
import br.com.vr.miniautorizador.util.CryptoUtils;

public class CartaoAssembler {

	public CartaoResponseDTO montarCartaoResponseDTO(Cartao cartao) {
		CartaoResponseDTO response = new CartaoResponseDTO();

		response.setNumeroCartao(cartao.getNumeroCartao());
		response.setSenha(CryptoUtils.decryptPassword(cartao.getSenha()));

		return response;
	}

	public Cartao criarCartaoRequestDTOToEntity(CartaoRequestDTO dto) {
		Cartao cartao = new Cartao();

		cartao.setNumeroCartao(dto.getNumeroCartao());
		cartao.setSenha(CryptoUtils.encryptPassword(dto.getSenha()));

		return cartao;
	}

}
