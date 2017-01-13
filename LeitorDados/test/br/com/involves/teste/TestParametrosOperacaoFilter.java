package br.com.involves.teste;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.com.involves.operacoes.OperadorFilter;

public class TestParametrosOperacaoFilter {

	@Test(expected = IllegalArgumentException.class)
	public void argumentosInsuficientes() {
		OperadorFilter operador = new OperadorFilter();
		List<String> argumentos = new ArrayList<>();
		argumentos.add("FILTER");
		operador.setArgumentos(argumentos);
		operador.validarArgumentos();
	}

	// @Test(expected = IllegalArgumentException.class)
	// public void propriedadeNaoExisteNoArquivo() {
	// // testar se a propriedade existe
	// }

}
