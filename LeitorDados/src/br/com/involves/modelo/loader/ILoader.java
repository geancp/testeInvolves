package br.com.involves.modelo.loader;

import br.com.involves.ArquivoCSVException;

@FunctionalInterface
public interface ILoader {

	void carregarInformacoes() throws ArquivoCSVException;

}
