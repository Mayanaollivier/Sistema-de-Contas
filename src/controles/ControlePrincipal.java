package controles;

import java.util.Date;
import util.Funcoes;

public class ControlePrincipal {

	public static void imprimeCabecalho() {
		System.out.println("|===========================================|");
		System.out.println("|           Bem vindo ao Banco 7            |");
		System.out.println("|===========================================|");
	}

	public static void imprimeRodape() {
		System.out.println("|===========================================|");
		System.out.println("| Volte sempre!!" + "                 " + Funcoes.formataData(new Date()) + " |");
		System.out.println("|===========================================|");
	}

	public static void imprimiLinhaPontilhada() {
		System.out.println("|-------------------------------------------|");
	}

}
