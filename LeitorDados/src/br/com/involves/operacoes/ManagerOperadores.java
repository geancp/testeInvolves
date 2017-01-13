package br.com.involves.operacoes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.involves.OperacaoNaoEncontradaException;
import br.com.involves.menu.MenuConsole;

public class ManagerOperadores {

	private static ManagerOperadores instance;
	private List<IOperacao> operadores;

	private ManagerOperadores() {
		operadores = new ArrayList<>();
		operadores.add(new OperadorCount());
		operadores.add(new OperadorFilter());
	}

	public static ManagerOperadores getInstance() {
		if (instance == null) {
			instance = new ManagerOperadores();
		}
		return instance;
	}

	public List<IOperacao> getOperadores() {
		return this.operadores;
	}

	public void executaOperacao(String comandoInformado) throws OperacaoNaoEncontradaException {
		boolean executouComSucesso = false;
		for(IOperacao operacao : this.operadores){
			if (comandoInformado != null && comandoInformado.toUpperCase().startsWith(operacao.getNomeComando())) {
				List<String> argumentos = Arrays.asList(comandoInformado.split(" "));
				operacao.setArgumentos(argumentos);
				try{
					operacao.validarArgumentos();
				} catch (IllegalArgumentException e) {
					MenuConsole.exibeErro(e);
					MenuConsole.exibeComandosValidos();
					return;
				}
				operacao.executar();
				executouComSucesso = true;
				break;
			}
		}
		if (!executouComSucesso) {
			throw new OperacaoNaoEncontradaException("A operação informada não foi reconhecida.");
		}
	}
}
