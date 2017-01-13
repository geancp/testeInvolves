package br.com.involves.Exceptions;


import br.com.involves.menu.MenuConsole;

public class ArquivoCSVException extends Exception {

	private static final long serialVersionUID = -6804653044218134026L;

	public ArquivoCSVException(String msg) {
		super(msg);
		MenuConsole.exibeErro(this);
	}

	public ArquivoCSVException(String msg, Exception e) {
		super(msg, e);
		MenuConsole.exibeErro(this);
	}

}
