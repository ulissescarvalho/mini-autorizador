package br.com.vr.miniautorizador.dto.entrada;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CartaoRequestDTO implements Serializable {

	private static final long serialVersionUID = -3522233499036335235L;

	@Pattern(regexp = "[\\d]{16}", message = "Somente numeros")
	@Size(min = 16, max = 16, message = "Tamanho do cartao deve ser de 16 numeros")
	private String numeroCartao;

	@NotBlank
	private String senha;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

}
