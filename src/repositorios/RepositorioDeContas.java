package repositorios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import entidades.Conta;
import entidades.ContaCorrente;
import entidades.ContaPoupanca;
import entidades.Pessoa;

public class RepositorioDeContas {
	Conta conta;
	Pessoa titular;

	public List<Conta> carrega() {

		List<Conta> listaConta = new ArrayList<Conta>();

		try {

			Scanner leitor = new Scanner(new File("contas.txt"));

			while (leitor.hasNextLine()) {

				String[] data = leitor.nextLine().split(",");

				String tipoConta = data[0];
				String numero = data[1];
				String agencia = data[2];
				String saldo = data[3];
				String cpf = data[4];
				
				
				String[] qtd_saque = data[5].split("=");
				String[] qtd_deposito = data[6].split("=");
				String[] qtd_transferencia = data[7].split("=");

				if (tipoConta.equals("Conta Corrente")) {
					conta = new ContaCorrente(cpf, Integer.parseInt(numero), Integer.parseInt(qtd_saque[1]),
							Integer.parseInt(qtd_deposito[1]), Integer.parseInt(qtd_transferencia[1]), Double.parseDouble(saldo),
							agencia, tipoConta);

				} else if (tipoConta.equals("Conta Poupança")) {
					conta = new ContaPoupanca(cpf, Integer.parseInt(numero), Integer.parseInt(qtd_saque[1]),
							Integer.parseInt(qtd_deposito[1]), Integer.parseInt(qtd_transferencia[1]), Double.parseDouble(saldo),
							agencia, tipoConta);
				}

				listaConta.add(conta);
			}
			leitor.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return listaConta;
	}

	public Map<String, Conta> carregaMapa() {

		Map<String, Conta> mapaDeContas = new HashMap<>();

		for (Conta conta : carrega()) {
			mapaDeContas.put(conta.getCpf_titular(), conta);
			//mapaDeContas.put(String.valueOf(conta.getAgencia()), conta);
		}

		return mapaDeContas;
	}

	public void atualizarInformacoes(Conta conta, String operacao) {

		String arquivo = "contas.txt";
		String arquivoTmp = "contas-tmp";
		int auxiliar;

		BufferedWriter writer;

		BufferedReader reader;

		try {

			writer = new BufferedWriter(new FileWriter(arquivoTmp));

			reader = new BufferedReader(new FileReader(arquivo));

			String linha;

			while ((linha = reader.readLine()) != null) {

				String[] data = linha.split(",");

				if (data[4].equals(conta.getCpf_titular())) {
					linha = linha.replace(data[3], String.valueOf(conta.getSaldo()));
					
					if (operacao.equals("Saque")) {
						String[] saque = data[5].split("=");
						auxiliar = conta.getQtd_saque() +1;
						linha = linha.replace(data[5],saque[0] + "=" + auxiliar );
						
						
					} if (operacao.equals("Depósito")) {
						String[] deposito = data[6].split("=");
						auxiliar = conta.getQtd_deposito() +1;
						linha = linha.replace(data[6],deposito[0] + "=" + auxiliar );
						
						
					} if (operacao.equals("Transferência")) {
						String[] transferencia = data[7].split("=");
						auxiliar = conta.getQtd_tranferencia() +1;
						linha = linha.replace(data[7],transferencia[0] + "=" + auxiliar );
						
						
					}
					

				}

				writer.write(linha + "\n");

			}

			writer.close();

			reader.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

		new File(arquivo).delete();

		new File(arquivoTmp).renameTo(new File(arquivo));

	}

}
