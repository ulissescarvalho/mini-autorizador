package br.com.vr.miniautorizador.excecao;

public class MiniAutorizadorNegocioException extends Exception {

	private static final long serialVersionUID = 1257550323896325689L;

	public MiniAutorizadorNegocioException(String erro) {
		super(erro);
	}

	public MiniAutorizadorNegocioException(String erro, Throwable exception) {
		super(erro, exception);
	}

}