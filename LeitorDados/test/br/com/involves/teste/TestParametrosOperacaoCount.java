package br.com.involves.teste;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.involves.operacoes.OperadorCount;

public class TestParametrosOperacaoCount {

	@Test(expected = IllegalArgumentException.class)
	public void argumentosInsuficientes() {
		OperadorCount operador = new OperadorCount();
		List<String> argumentos = new ArrayList<>();
		argumentos.add("COUNT");
		operador.setArgumentos(argumentos);
		operador.validarArgumentos();
	}

	@Test(expected = IllegalArgumentException.class)
	public void opcaoDeComandoNaoReconhecido() {
		OperadorCount operador = new OperadorCount();
		List<String> argumentos = new ArrayList<>();
		argumentos.add("COUNT");
		argumentos.add("TODOS");
		operador.setArgumentos(argumentos);
		operador.validarArgumentos();
	}

	@Test(expected = IllegalArgumentException.class)
	public void listarTodosComArgumentosExcedentes() {
		OperadorCount operador = new OperadorCount();
		List<String> argumentos = new ArrayList<>();
		argumentos.add("COUNT");
		argumentos.add("*");
		argumentos.add("UF");
		operador.setArgumentos(argumentos);
		operador.validarArgumentos();
	}

	@Test(expected = IllegalArgumentException.class)
	public void argumentosInsuficientesParaDistinct() {
		OperadorCount operador = new OperadorCount();
		List<String> argumentos = new ArrayList<>();
		argumentos.add("COUNT");
		argumentos.add("DISTINCT");
		operador.setArgumentos(argumentos);
		operador.validarArgumentos();
	}

	// @Test(expected = IllegalArgumentException.class)
	// public void propriedadeNaoExisteNoArquivo() {
	// // testar se a propriedade existe
	// }

}
