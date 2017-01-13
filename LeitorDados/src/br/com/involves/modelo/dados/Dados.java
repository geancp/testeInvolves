package br.com.involves.modelo.dados;

import java.util.ArrayList;
import java.util.List;

import br.com.involves.modelo.Coluna;
import br.com.involves.modelo.DadoEntity;

public class Dados {

	private static Dados instance;

	private List<Coluna> colunas;
	private List<DadoEntity> linhas;

	private Dados() {
		this.colunas = new ArrayList<>();
		this.linhas = new ArrayList<>();
	}

	public static synchronized Dados getInstance() {
		if (instance == null) {
			instance = new Dados();
		}
		return instance;
	}

	public boolean isDadosCarregados() {
		return !colunas.isEmpty();
	}

	public void limparDados() {
		this.colunas.clear();
		this.linhas.clear();
	}

	public List<Coluna> getColunas() {
		return colunas;
	}

	public void setColunas(List<Coluna> colunas) {
		this.colunas = colunas;
	}

	public List<DadoEntity> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<DadoEntity> linhas) {
		this.linhas = linhas;
	}
}
