package visual.componentes;

import entidades.Conta;
import entidades.Pessoa;

public class ConfirmaTransferencia {
	
	public static void imprimir(Pessoa usuario,Conta tipoConta) {
		System.out.println("|===========================================|");
		System.out.println("|   	  Dados conta destino		    |");
		System.out.println("|===========================================|");
		System.out.println("|Nome : " + usuario.getNome());
		System.out.println("|Ag�ncia : " + tipoConta.getAgencia());
		System.out.println("|N�mero Conta : " + tipoConta.getNumero());
		System.out.println("|===========================================|");
		System.out.println("|Confirma a opera��o ? [1] SIM / [2] N�O    |");
		System.out.println("|===========================================|");
		
	}
}
