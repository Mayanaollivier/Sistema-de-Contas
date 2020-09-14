package visual.componentes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import controles.ControleDeSessao;
import entidades.Cliente;
import entidades.Conta;
import entidades.Funcionario;
import entidades.Pessoa;
import entidades.SeguroDeVida;
import repositorios.RepositorioClientes;
import repositorios.RepositorioDeContas;
import repositorios.RepositorioFuncionario;
import repositorios.RepositorioSeguro;
import util.Funcoes;

public class Relatorios {

	public static void comprovanteSaldo() {
		

		System.out.println("|===========================================|");
		System.out.println("|             COMPROVANTE SALDO             |");
		System.out.println("|===========================================|");
		System.out.println("|Data : " + Funcoes.formataData(new Date()));
		System.out.println("|Saldo atual : " + ControleDeSessao.contaLogada.getSaldo());

	}

	public static void tributacaoCCorrente() {

		int qtd_saque = ControleDeSessao.contaLogada.getQtd_saque();
		int qtd_deposito = ControleDeSessao.contaLogada.getQtd_deposito();
		int qtd_transferencia = ControleDeSessao.contaLogada.getQtd_tranferencia();
		double total = (qtd_saque * 0.10) + (qtd_deposito * 0.10) + (qtd_transferencia * 0.20);
		double valorSeg = 0;
		double valorTrib = 0;

		RepositorioSeguro repSeguro = new RepositorioSeguro();
		Map<String, SeguroDeVida> mapaDeAssegurados = RepositorioSeguro.carregaMapa();

		if (mapaDeAssegurados.get(ControleDeSessao.contaLogada.getCpf_titular()) != null) {
			valorSeg = mapaDeAssegurados.get(ControleDeSessao.contaLogada.getCpf_titular()).getValor();
			valorTrib = valorSeg * 0.20;
		}

		System.out.println("|===========================================|");
		System.out.println("|            TRIBUTAÇÃO DA CONTA            |");
		System.out.println("|===========================================|");
		System.out.println("|Saque R$0.10                               |");
		System.out.println("|Depósito R$0.10                            |");
		System.out.println("|Transferência R$0.20                       |");
		System.out.println("|Seguro de vida 20%                         |");
		System.out.println("|===========================================|");
		System.out.println("|Quantidade de saques realizados : " + qtd_saque);
		System.out.println("|Quantidade de depósitos realizados : " + qtd_deposito);
		System.out.println("|Quantidade de transfêrencias realizadas : " + qtd_transferencia);
		System.out.println("|Tributo de Seguro de Vida : " + valorTrib);
		System.out.println("|Total gasto em tributos : " + (total + valorTrib));
		System.out.println("|===========================================|");
	}

	public static void rendimentoDaPoupanca(int dias, double valorTotal) {

		System.out.println("|===========================================|");
		System.out.println("|           RENDIMENTO POUPANÇA             |");
		System.out.println("|===========================================|");
		System.out.println("|Taxa de Rendimento: 0.5 %                  |");
		System.out.println("|Periodo : " + dias);
		System.out.println("|Valor final da simulação : " + valorTotal);
		System.out.println("|===========================================|");
	}

	public static void taxaSeguroDeVida() {

		RepositorioSeguro repSeguro = new RepositorioSeguro();
		Map<String, SeguroDeVida> mapaDeAssegurados = RepositorioSeguro.carregaMapa();

		if (mapaDeAssegurados.get(ControleDeSessao.contaLogada.getCpf_titular()) != null) {
			double valor = mapaDeAssegurados.get(ControleDeSessao.contaLogada.getCpf_titular()).getValor();

			System.out.println("|===========================================|");
			System.out.println("|              SEGURO DE VIDA               |");
			System.out.println("|===========================================|");
			System.out.println("|Taxa de Tributação : 20.0 %                |");
			System.out.println("|Valor Aplicado R$: " + valor);
			System.out.println("|-------------------------------------------|");
			System.out.println("|Valor após tributação R$: " + (valor - (valor * 0.20)));
			System.out.println("|===========================================|");

		} else {
			System.out.println("Você não possui Seguro de Vida contratados !");
		}
	}

	public static void contasGerenciadas() {
		RepositorioDeContas repositorio = new RepositorioDeContas();
		RepositorioClientes rep_clientes = new RepositorioClientes();
		RepositorioFuncionario rep_funcionarios = new RepositorioFuncionario();

		int contador = 0;
		System.out.println("|===========================================|");
		System.out.println("|      RELATÓRIO DE CONTAS GERENCIADAS      |");
		System.out.println("|===========================================|");

		for (Conta conta : repositorio.carrega()) {

			Pessoa titular = rep_clientes.carregaMapa().get(conta.getCpf_titular());
			if (titular == null) {
				titular = rep_funcionarios.carregaMapa().get(conta.getCpf_titular());
			}

			if (conta.getAgencia().equals(ControleDeSessao.getContaLogada().getAgencia())) {

				contador++;
				System.out.println("|Titular : " + titular.getNome());
				System.out.println("|Número da Conta : " + conta.getNumero());
				System.out.println("|Agência : " + conta.getAgencia());
				System.out.println("|===========================================|");
			}

		}

		System.out.println("|Total de contas : " + contador);

	}

	public static void relatorioDiretor() {
		
		RepositorioClientes rep_clientes = new RepositorioClientes();
		RepositorioFuncionario rep_funcionarios = new RepositorioFuncionario();
		RepositorioDeContas rep_contas = new RepositorioDeContas();
		
		List<Pessoa> listaUsuarios = new ArrayList<>();
		
		for (Funcionario funcionario : rep_funcionarios.carregaLista()) {
			listaUsuarios.add(funcionario);
		}
		
		for (Cliente cliente : rep_clientes.carregaLista()) {
			listaUsuarios.add(cliente);
		}

		System.out.println("|===========================================|");
		System.out.println("|           RELATÓRIO DE USUÁRIOS           |");
		System.out.println("|===========================================|");

		Collections.sort(listaUsuarios);
		for (Pessoa usuario : listaUsuarios) {
			System.out.println("|Nome : " + usuario.getNome() + "\n|CPF : " + usuario.getCpf());
			System.out.println("|Agência : " + rep_contas.carregaMapa().get(usuario.getCpf()).getAgencia());
			System.out.println("|===========================================|");
		}

	}

	public static void relatorioPresidente() {

		RepositorioDeContas rep_contas = new RepositorioDeContas();
		int qtd_saque = 0;
		int qtd_deposito = 0;
		int qtd_transferencia = 0;
		double totalTributos = 0;
		double soma = 0;
		double totalAssegurados = 0;

		for (Conta conta : rep_contas.carrega()) {
			qtd_saque = conta.getQtd_saque();
			qtd_deposito = conta.getQtd_deposito();
			qtd_transferencia = conta.getQtd_tranferencia();
			totalTributos += (qtd_saque * 0.10) + (qtd_deposito * 0.10) + (qtd_transferencia * 0.20);
			soma += conta.getSaldo();
		}
		for (SeguroDeVida seguro : ControleDeSessao.listaSeguroVida) {
			totalAssegurados += seguro.getValor() * 0.20;
		}

		System.out.println("|===========================================|");
		System.out.println("|           RELATÓRIO DE CAPITAL            |");
		System.out.println("|===========================================|");
		System.out.println("|Nome Banco : Banco 7                       |");
		System.out.println("|-------------------------------------------|");
		System.out.println("|Total de Tributos de Movimentação R$ " + totalTributos);
		System.out.println("|Total de Seguro de Vida R$ " + totalAssegurados);
		System.out.println("|Total de Saldo das Contas R$ " + soma);
		System.out.println("|-------------------------------------------|");
		System.out.println("|Total de Capital :" + (soma + totalTributos + totalAssegurados));
		System.out.println("|===========================================|");
	}

}