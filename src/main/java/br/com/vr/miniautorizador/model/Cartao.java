package br.com.vr.miniautorizador.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cartao implements Serializable {

	private static final long serialVersionUID = 1080394407153377372L;

	@Id
	@Pattern(regexp = "[\\d]{16}", message = "Somente numeros")
	@Size(min = 16, max = 16, message = "Tamanho do cartao deve ser de 16 numeros")
	private String numeroCartao;

	@NotBlank
	private String senha;
	private BigDecimal saldo;

	@DBRef
	@JsonIgnore
	private List<Transacao> transacoes = new ArrayList<>();

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

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}

}
