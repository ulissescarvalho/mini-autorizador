package br.com.vr.miniautorizador.dto.entrada;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransacaoRequestDTO implements Serializable {

	private static final long serialVersionUID = 8363847446839667945L;

	@Pattern(regexp = "[\\d]{16}", message = "Somente numeros")
	@Size(min = 16, max = 16, message = "Tamanho do cartao deve ser de 16 numeros")
	private String numeroCartao;

	@NotBlank
	private String senha;

	@Min(0)
	private BigDecimal valor;

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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
