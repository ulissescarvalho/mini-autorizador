package br.com.vr.miniautorizador.dto.saida;

import java.io.Serializable;

public class CartaoResponseDTO implements Serializable {

	private static final long serialVersionUID = 8758834439709784924L;

	private String numeroCartao;
	private String senha;

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
