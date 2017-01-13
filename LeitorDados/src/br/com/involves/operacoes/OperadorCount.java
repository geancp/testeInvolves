package br.com.involves.operacoes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.involves.modelo.Coluna;
import br.com.involves.modelo.dados.Dados;

public class OperadorCount implements IOperacao {

	private static final String LISTAR_TODOS = "*";
	private static final String DISTINCT = "DISTINCT";

	private List<String> argumentos;
	private Coluna propriedadeSelecionada;

	@Override
	public String getNomeComando() {
		return "COUNT";
	}

	@Override
	public String getDescricaoDeUso() {
		StringBuilder descricao = new StringBuilder();
		descricao.append("COUNT * \n");
		descricao.append("\tRetorna o numero total de linhas de dados da fonte de dados \n");
		descricao.append("COUNT DISTINCT [nome da propriedade] \n");
		descricao.append(
				"\tRetorna o numero total de linhas agrupadas pelo nome da propriedade contida na fonte de dados");
		return descricao.toString();
	}

	@Override
	public void executar() {
		switch (this.argumentos.get(1).toUpperCase()) {
		case LISTAR_TODOS:
			executarCountAll();
			break;
		case DISTINCT:
			executarCountDistinct();
			break;
		}
	}

	@Override
	public void setArgumentos(List<String> args) {
		this.argumentos = args;
		this.propriedadeSelecionada = null;
	}

	@Override
	public void validarArgumentos() throws IllegalArgumentException {
		if (this.argumentos.size() < 2) {
			throw new IllegalArgumentException("Você deve fornecer um argumento para o comando COUNT.");
		}
		if (!LISTAR_TODOS.equals(this.argumentos.get(1)) && !DISTINCT.equalsIgnoreCase(this.argumentos.get(1))) {
			throw new IllegalArgumentException("Opção não reconhecida.");
		}
		if (LISTAR_TODOS.equals(this.argumentos.get(1)) && this.argumentos.size() > 2) {
			throw new IllegalArgumentException("O Comando \"COUNT *\" não admite argumentos adicionais.");
		}
		if (DISTINCT.equalsIgnoreCase(this.argumentos.get(1)) && this.argumentos.size() < 3) {
			throw new IllegalArgumentException("O Comando \"COUNT DISTINCT\" necessita de uma propriedade.");
		}
		if (DISTINCT.equalsIgnoreCase(this.argumentos.get(1))) {
			for (Coluna c : Dados.getInstance().getColunas()) {
				if (c.getNomeColuna().equalsIgnoreCase(this.argumentos.get(2))) {
					propriedadeSelecionada = c;
					break;
				}
			}
			if (this.propriedadeSelecionada == null) {
				throw new IllegalArgumentException("A propriedade selecionada não existe na fonte de dados.");
			}
		}
	}

	private void executarCountAll() {
		System.out
				.println(String.format("O número de total registros é de: %d", Dados.getInstance().getLinhas().size()));
	}

	private void executarCountDistinct() {
		Map<String, Integer> resultado = new HashMap<>();

		Dados.getInstance().getLinhas().forEach(de -> {
			String valor = de.getInformacoes().get(this.propriedadeSelecionada.getPosicao());
			if (resultado.containsKey(valor)) {
				resultado.replace(valor, resultado.get(valor) + 1);
			} else {
				resultado.put(valor, 1);
			}
		});

		System.out.println("O número de total registros agrupados é:");
		resultado.forEach((k, v) -> System.out.println("Valor : " + k + " Total : " + v));
	}
}
