package br.com.involves.menu;

public interface IMenu {

	void solicitarDados();

	void exibeComandosValidos();

	String leComandoUsuario();

	void exibeErro(Exception e);

	void exibeSolicitacaoDeComando();

}
