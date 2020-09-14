package visual.componentes;

import entidades.Conta;
import entidades.Pessoa;

public class ConfirmaTransferencia {
	
	public static void imprimir(Pessoa usuario,Conta tipoConta) {
		System.out.println("|===========================================|");
		System.out.println("|   	  Dados conta destino		    |");
		System.out.println("|===========================================|");
		System.out.println("|Nome : " + usuario.getNome());
		System.out.println("|Agência : " + tipoConta.getAgencia());
		System.out.println("|Número Conta : " + tipoConta.getNumero());
		System.out.println("|===========================================|");
		System.out.println("|Confirma a operação ? [1] SIM / [2] NÃO    |");
		System.out.println("|===========================================|");
		
	}
}
