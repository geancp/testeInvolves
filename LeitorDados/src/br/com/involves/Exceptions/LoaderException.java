package br.com.involves.Exceptions;

public class LoaderException extends Exception {

	private static final long serialVersionUID = -3200368310902974936L;

	public LoaderException(ArquivoCSVException e) {
		super(e);
	}


}
