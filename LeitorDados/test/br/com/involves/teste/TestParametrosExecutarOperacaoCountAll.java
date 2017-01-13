package br.com.involves.teste;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import br.com.involves.OperacaoNaoEncontradaException;
import br.com.involves.operacoes.ManagerOperadores;

public class TestParametrosExecutarOperacaoCountAll {

	@Rule
	public TemporaryFolder pastaTemp = new TemporaryFolder();

	private final ByteArrayOutputStream systemOut = new ByteArrayOutputStream();

	private static final String ARQUIVO_CVS = "CountAll.csv";
	private File arquivo;

	@Before
	public void setUp() {
		try {
			// Setando a saida do system.out
			System.setOut(new PrintStream(systemOut));

			// preparando os dados
			this.arquivo = pastaTemp.newFile(ARQUIVO_CVS);

			PrintWriter gravarDados = new PrintWriter(this.arquivo);
			gravarDados.println("Estado, Cidade, polulacao");
			gravarDados.println("SC, Laguna, 100000");
			gravarDados.println("SC, Joinville, 400000");
			gravarDados.println("SC, Blumenau, 200000");
			gravarDados.println("PR, Curitiba, 1000000");
			gravarDados.println("PR, Londrina, 400000");
			gravarDados.println("SP, Bonito, 1000000");
			gravarDados.println("RJ, Rio de Janeiro, 20000000");
			gravarDados.println("PI, Teresina, 500000");
			gravarDados.flush();
			gravarDados.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() {
		// Limpando sa saida do System.out
		System.setOut(null);

		if (this.arquivo.exists()) {
			this.arquivo.delete();
		}
	}

	@Test
	public void countAll() throws Exception {
		try {
			Class<?> cls = Class.forName("br.com.involves.modelo.loader.LoaderCSV");
			Object instancia = cls.newInstance();

			Method method = cls.getDeclaredMethod("lerInformacoesArquivo", Path.class);
			method.setAccessible(true);
			method.invoke(instancia, Paths.get(this.arquivo.getPath()));

		} catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException |NoSuchMethodException| SecurityException| InstantiationException e) {
			throw new Exception(e);
		}

		try {
			ManagerOperadores.getInstance().executaOperacao("COUNT *");
		} catch (OperacaoNaoEncontradaException e) {
			throw new Exception(e);
		}
		Assert.assertTrue(systemOut.toString().startsWith("O número de total registros é de: 8"));
	}
}
