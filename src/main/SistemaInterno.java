package main;

import java.util.Scanner;
import controles.ControlePrincipal;
import repositorios.RepositorioDeOperacoes;
import visual.TelaLogin;

public class SistemaInterno {

	public static void main(String[] args) {

		Scanner leitora = new Scanner(System.in);
		int resp;

		ControlePrincipal.imprimeCabecalho();
		TelaLogin.main(args);
		System.out.println("|===========================================|");
		System.out.println("|Deseja encerrar o sistema?  [1]Sim [2]Não  |");
		resp = leitora.nextInt();

		switch (resp) {
		case 1:
			ControlePrincipal.imprimeRodape();
			RepositorioDeOperacoes.salvar();

			break;
		case 2:
			main(args);

			break;

		default:
			ControlePrincipal.imprimeRodape();
			RepositorioDeOperacoes.salvar();

			break;
		}
	}
}
