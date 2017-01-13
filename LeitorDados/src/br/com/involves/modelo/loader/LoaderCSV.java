package br.com.involves.modelo.loader;

import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.involves.Exceptions.ArquivoCSVException;
import br.com.involves.Exceptions.LoaderException;
import br.com.involves.menu.MenuConsole;
import br.com.involves.modelo.Coluna;
import br.com.involves.modelo.DadoEntity;
import br.com.involves.modelo.dados.Dados;

public class LoaderCSV implements ILoader{

	private String delimitador = ",";

	@Override
	public void carregarInformacoes() throws LoaderException {
		Path arquivo = this.carregarArquivo();
		this.determinarDelimitador();

		try {
			this.lerInformacoesArquivo(arquivo);
		} catch (ArquivoCSVException e) {
			throw new LoaderException(e);
		}
	}

	private Path carregarArquivo() {
		Path arquivo = null;

		String pasta = MenuConsole.solicitarEntradaUsuario("Informe a pasta contendo os arquivos de dados:");
		try {
			Paths.get(pasta);
		} catch (FileSystemNotFoundException e) {
			Exception e1 = new Exception("Caminho invádo! Tente novamente.", e);
			MenuConsole.exibeErro(e1);
		}

		String nomeDoArquivo = MenuConsole.solicitarEntradaUsuario("Informe o nome do arquivo de dados:");
		try {
			arquivo = Paths.get(pasta, nomeDoArquivo);
		} catch (FileSystemNotFoundException e) {
			Exception e1 = new Exception("Arquivo invádo! Tente novamente.", e);
			MenuConsole.exibeErro(e1);
		}
		
		return arquivo;
	}

	private void determinarDelimitador() {
		setDelimitador(MenuConsole.solicitarEntradaUsuario("Informe o sepadador de campos do arquivo de dados:"));
	}

	private void lerInformacoesArquivo(Path arquivo) throws ArquivoCSVException {
		try{
			List<String> linhas = Files.readAllLines(arquivo);

			this.defineColunasDosDados(linhas);

			this.leDadosArquivo(linhas);

		} catch (IOException e) {
			MenuConsole.exibeErro(new ArquivoCSVException("Erro ao ler o arquivo.", e));
		} catch (SecurityException e) {
			MenuConsole.exibeErro(new ArquivoCSVException("Voce não tem permissão para ler o arquivo.", e));
		}
	}

	private void leDadosArquivo(List<String> linhas) throws ArquivoCSVException {
		List<DadoEntity> dadosEntity = new ArrayList<>();

		for (int i = 0; i < linhas.size(); i++) {
			String[] infos = linhas.get(i).split(getDelimitador());

			DadoEntity de = new DadoEntity();
			de.setInformacoes(Arrays.asList(infos));

			if (de.getInformacoes().size() != Dados.getInstance().getColunas().size()) {
				Dados.getInstance().limparDados();
				throw new ArquivoCSVException(
						String.format(
								"O número de colunas da linha %s não corresponde ao número de colunas do arquivo.", i));
			}

			dadosEntity.add(de);
		}
		Dados.getInstance().setLinhas(dadosEntity);
	}

	private void defineColunasDosDados(List<String> linhas) throws ArquivoCSVException {
		if (linhas != null && !linhas.isEmpty()) {
			String[] infos = linhas.get(0).split(getDelimitador());

			List<Coluna> colunas = new ArrayList<>();
			for (int i = 0; i < infos.length; i++) {
				Coluna coluna = new Coluna(infos[i], i);
				colunas.add(coluna);
			}
			Dados.getInstance().setColunas(colunas);

			linhas.remove(0);
		} else {
			throw new ArquivoCSVException("O arquivo informado não possui informações");
		}
	}

	private String getDelimitador() {
		return this.delimitador;
	}

	public void setDelimitador(String delimitador) {
		this.delimitador = delimitador;
	}
}
