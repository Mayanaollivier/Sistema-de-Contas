package visual.componentes;

import java.util.Scanner;

public class MenuDeSimulacao {

	
	
	public static void solicitaSimulacao() {

		Scanner leitor = new Scanner(System.in);

		System.out.print("|Entre com o valor da simulação: ");
		double valor = leitor.nextDouble();
		
		System.out.print("|Entre com o qt de dias: ");
		int dias = leitor.nextInt();	
		
		double total = valor * 0.005 * dias;
		Relatorios.rendimentoDaPoupanca(dias, total);
		
		
		
	}
}
