package br.com.involves.operacoes;

import java.util.ArrayList;
import java.util.List;

import br.com.involves.modelo.Coluna;
import br.com.involves.modelo.DadoEntity;
import br.com.involves.modelo.dados.Dados;

public class OperadorFilter implements IOperacao {

	private List<String> argumentos;
	private Coluna propriedadeSelecionada;

	@Override
	public String getNomeComando() {
		return "FILTER";
	}

	@Override
	public String getDescricaoDeUso() {
		StringBuilder descricao = new StringBuilder();
		descricao.append("FILTER [nome da propriedade] [valor da propriedade] \n");
		descricao.append(
				"\tRetorna todas as linhas cujo o valor da propriedade seja concidente ao informado no parametro");
		return descricao.toString();
	}

	@Override
	public void executar() {
		filtrarResultados();
	}

	@Override
	public void setArgumentos(List<String> args) {
		this.argumentos = args;
		this.propriedadeSelecionada = null;
	}

	@Override
	public void validarArgumentos() throws IllegalArgumentException {
		if (this.argumentos.size() != 3) {
			throw new IllegalArgumentException("O Comando \"FILTER\" necessita de uma propriedade e um valor.");
		}
		for (Coluna c : Dados.getInstance().getColunas()) {
			if (c.getNomeColuna().equalsIgnoreCase(this.argumentos.get(1))) {
				propriedadeSelecionada = c;
				break;
			}
		}
		if (this.propriedadeSelecionada == null) {
			throw new IllegalArgumentException("A propriedade selecionada não existe na fonte de dados.");
		}
	}

	private void filtrarResultados() {
		List<List<String>> resultado = new ArrayList<>();
		for (DadoEntity de : Dados.getInstance().getLinhas()) {
			if (this.argumentos.get(2)
					.equalsIgnoreCase(de.getInformacoes().get(this.propriedadeSelecionada.getPosicao()))) {

				resultado.add(de.getInformacoes());
			}
		}

		if (!resultado.isEmpty()) {
			StringBuilder linha = new StringBuilder();

			for (int i = 0; i < Dados.getInstance().getColunas().size(); i++) {
				if (i > 0) {
					linha.append(", ");
				}
				linha.append(Dados.getInstance().getColunas().get(i).getNomeColuna());
			}
			System.out.println(linha.toString());

			for (List<String> item : resultado) {
				linha = new StringBuilder();

				for (int i = 0; i < item.size(); i++) {
					if (i > 0) {
						linha.append(", ");
					}
					linha.append(item.get(i));
				}
				System.out.println(linha.toString());
			}
		}
	}
}
