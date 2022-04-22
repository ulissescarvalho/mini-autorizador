package br.com.vr.miniautorizador.excecao;

public class CartaoRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -5753326687472489822L;

	public CartaoRuntimeException(String erro) {
		super(erro);
	}

	public CartaoRuntimeException(String erro, Throwable exception) {
		super(erro, exception);
	}

}