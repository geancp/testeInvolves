package br.com.involves.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import br.com.involves.ConsoleException;
import br.com.involves.operacoes.ManagerOperadores;

public class MenuConsole {

	public static String solicitarEntradaUsuario(String msg) {
		System.out.print(msg);
		return MenuConsole.leEntradaUsuario();
	}

	public static String leEntradaUsuario() {
		String comando = null;
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader in = new BufferedReader(isr);
			comando = in.readLine();
		} catch (IOException e) {
			MenuConsole.exibeErro(new ConsoleException("Erro ao obter informa��es do usu�rio.", e));
		}
		return comando;
	}

	public static void exibeComandosValidos() {
		System.out.println("As opera��es permitidas s�o:");
		ManagerOperadores.getInstance().getOperadores().stream()
				.forEach(operador -> {
					System.out.println(operador.getDescricaoDeUso());
				});
	}

	public static void exibeErro(Exception e) {
		System.out.println(e.getMessage());
	}

	public static void exibeSolicitacaoDeComando() {
		System.out.print("Comando: ");
	}

}
