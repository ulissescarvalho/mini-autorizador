package br.com.vr.miniautorizador.mapper;

import br.com.vr.miniautorizador.dto.entrada.CartaoRequestDTO;
import br.com.vr.miniautorizador.dto.saida.CartaoResponseDTO;
import br.com.vr.miniautorizador.model.Cartao;

public class CartaoAssembler {

	public CartaoResponseDTO montarCartaoResponseDTO(Cartao cartao) {
		CartaoResponseDTO response = new CartaoResponseDTO();

		response.setNumeroCartao(cartao.getNumeroCartao());
		response.setSenha(cartao.getSenha());

		return response;
	}

	public Cartao criarCartaoRequestDTOToEntity(CartaoRequestDTO dto) {
		Cartao cartao = new Cartao();

		cartao.setNumeroCartao(dto.getNumeroCartao());
		cartao.setSenha(dto.getSenha());

		return cartao;
	}

}
