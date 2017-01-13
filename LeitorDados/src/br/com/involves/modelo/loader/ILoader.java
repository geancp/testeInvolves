package br.com.involves.modelo.loader;

import br.com.involves.Exceptions.LoaderException;

@FunctionalInterface
public interface ILoader {

	void carregarInformacoes() throws LoaderException;

}
