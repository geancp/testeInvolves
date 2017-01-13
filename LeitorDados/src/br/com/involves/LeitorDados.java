package br.com.involves;

import br.com.involves.menu.MenuConsole;
import br.com.involves.modelo.dados.Dados;
import br.com.involves.modelo.loader.ILoader;
import br.com.involves.modelo.loader.LoaderCSV;
import br.com.involves.operacoes.ManagerOperadores;

public class LeitorDados {

	public static void main(String[] args) {
		LeitorDados ld = new LeitorDados();
		ld.iniciar();
	}

	private void iniciar() {
		boolean continuarExecucao = true;

		while (continuarExecucao) {
			if (!Dados.getInstance().isDadosCarregados()) {
				ILoader loader = new LoaderCSV();
				try {
					loader.carregarInformacoes();
				} catch (ArquivoCSVException e) {
					continue;
				}
			}

			MenuConsole.exibeSolicitacaoDeComando();
			
			try{
				ManagerOperadores.getInstance().executaOperacao(MenuConsole.leEntradaUsuario());
			} catch(OperacaoNaoEncontradaException e){
				MenuConsole.exibeErro(e);
				MenuConsole.exibeComandosValidos();
			}

			if ("N".equalsIgnoreCase(MenuConsole.solicitarEntradaUsuario("Deseja executar outra operação? (S/N):"))) {
				continuarExecucao = false;
			}
		}
	}

}
