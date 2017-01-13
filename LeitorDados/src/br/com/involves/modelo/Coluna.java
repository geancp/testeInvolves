package br.com.involves.modelo;

public class Coluna {

	private String nomeColuna;
	private Integer posicao;

	public Coluna(String nomeColuna, int posicao) {
		this.nomeColuna = nomeColuna;
		this.posicao = posicao;
	}

	public String getNomeColuna() {
		return nomeColuna;
	}

	public void setNomeColuna(String nomeColuna) {
		this.nomeColuna = nomeColuna;
	}

	public Integer getPosicao() {
		return posicao;
	}

	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}
}
