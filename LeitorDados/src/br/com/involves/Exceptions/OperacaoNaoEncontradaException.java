package br.com.involves.Exceptions;

public class OperacaoNaoEncontradaException extends Exception {

	private static final long serialVersionUID = 3453114818951305979L;

	public OperacaoNaoEncontradaException(String msg) {
		super(msg);
	}

	public OperacaoNaoEncontradaException(String msg, Exception e) {
		super(msg, e);
	}
}
