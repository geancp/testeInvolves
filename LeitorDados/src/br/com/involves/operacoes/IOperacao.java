package br.com.involves.operacoes;

import java.util.List;

public interface IOperacao {

	String getNomeComando();

	String getDescricaoDeUso();

	void executar();

	void setArgumentos(List<String> args);

	void validarArgumentos() throws IllegalArgumentException;
}
