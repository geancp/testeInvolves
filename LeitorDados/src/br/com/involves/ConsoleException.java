package br.com.involves;


import br.com.involves.menu.MenuConsole;

public class ConsoleException extends Exception {

	private static final long serialVersionUID = -6804653044218134026L;

	public ConsoleException(String msg, Exception e) {
		super(msg, e);
		MenuConsole.exibeErro(this);
	}

}
