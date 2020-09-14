package visual;

import java.util.Date;
import java.util.Map;
import java.util.Scanner;

import controles.ControleDeSessao;
import controles.ControlePrincipal;
import entidades.Cliente;
import entidades.Conta;
import entidades.Funcionario;
import entidades.SeguroDeVida;
import entidades.exception.SaldoException;
import repositorios.RepositorioClientes;
import repositorios.RepositorioDeContas;
import repositorios.RepositorioFuncionario;
import repositorios.RepositorioSeguro;
import util.Funcoes;
import visual.componentes.CabecalhoUsuario;
import visual.componentes.ConfirmaTransferencia;
import visual.componentes.MenuDeAcao;
import visual.componentes.MenuDeMovimentacao;
import visual.componentes.MenuDeRelatorios;
import visual.componentes.MenuDeSeguro;
import visual.componentes.MenuDeSimulacao;
import visual.componentes.Relatorios;

public class TelaPresidente {

	public static void main(String[] args) {

		CabecalhoUsuario.imprimir(ControleDeSessao.getUsuarioLogado().getNome(), "Presidente");
		MenuDeAcao.imprimir();
		lerAcao();

	}

	public static void lerAcao() {

		Scanner leitor = new Scanner(System.in);
		int acao = leitor.nextInt();

		switch (acao) {
		case 1:
			MenuDeMovimentacao.imprimir();
			lerMovimentacao();
			break;
		case 2:
			MenuDeRelatorios.imprimirPresidente();
			lerRelatorio();
			break;
		case 3:
			MenuDeSeguro.imprimir();
			lerSeguro();
			break;

		default:
			System.out.println("Número Inválido");
			break;
		}

	}

	public static void lerSeguro() {
		Scanner leitor = new Scanner(System.in);
		int acao = leitor.nextInt();

		switch (acao) {
		case 1:
			contratarSeguro();
			break;
		case 2:
			Relatorios.taxaSeguroDeVida();
			break;
		default:
			System.out.println("Número Inválido");
			break;
		}

	}

	private static void contratarSeguro() {

		Scanner leitor = new Scanner(System.in);
		RepositorioSeguro repSeguro = new RepositorioSeguro();
		double valor;

		Map<String, SeguroDeVida> mapaDeAssegurados = RepositorioSeguro.carregaMapa();

		if (mapaDeAssegurados.get(ControleDeSessao.contaLogada.getCpf_titular()) == null) {
			System.out.println("Entre com o valor do seguro : ");
			valor = leitor.nextDouble();
			SeguroDeVida seguro = new SeguroDeVida(valor, ControleDeSessao.contaLogada.getCpf_titular());
			ControleDeSessao.listaSeguroVida.add(seguro);
			repSeguro.salvar();

		} else {
			System.out.println("Você já possui seguro contratado !");
		}
	}

	public static void lerMovimentacao() {

		Scanner leitor = new Scanner(System.in);
		int acao = leitor.nextInt();

		switch (acao) {
		case 1:
			solicitaSaque();
			break;
		case 2:
			solicitaDeposito();
			break;
		case 3:
			solicitaTransferencia();
			break;
		default:
			System.out.println("Número Inválido");
			break;
		}

	}

	public static void lerRelatorio() {

		Scanner leitor = new Scanner(System.in);
		int acao = leitor.nextInt();

		switch (acao) {
		case 1:
			Relatorios.comprovanteSaldo();
			break;
		case 2:
			Relatorios.tributacaoCCorrente();

			break;
		case 3:
			MenuDeSimulacao.solicitaSimulacao();

			break;

		case 4:
			Relatorios.relatorioDiretor();
			break;

		case 5:
			Relatorios.relatorioPresidente();
			break;
		default:
			System.out.println("Número Inválido");
			break;
		}

	}

	public static void solicitaSaque() {

		Scanner leitor = new Scanner(System.in);
		Scanner leitorResp = new Scanner(System.in);
		RepositorioDeContas repositorioContas = new RepositorioDeContas();
		String data = Funcoes.formataDataEHora(new Date());

		System.out.print("|Entre com o valor do saque: ");
		double valor = leitor.nextDouble();

		try {
			ControleDeSessao.getContaLogada().saca(valor);
			ControlePrincipal.imprimiLinhaPontilhada();
			repositorioContas.atualizarInformacoes(ControleDeSessao.getContaLogada(), "Saque");
			System.out.println("|Saque efetuado.Saldo atual " + ControleDeSessao.getContaLogada().getSaldo());
			ControleDeSessao.listaOperacoes.add("Titular : " + ControleDeSessao.usuarioLogado.getNome() + " Sacou : R$"
					+ valor + " Data : " + data);

		} catch (SaldoException e) {
			System.out.println(e.getMessage());
			while (true) {
				System.out.println("|Deseja tentar novamente ? [1]SIM / [2] NÃO ");
				int resposta = leitorResp.nextInt();
				if (resposta == 1) {
					solicitaSaque();
					break;
				} else if (resposta == 2) {
					break;
				} else {
					System.out.println("Resposta inválida");
				}
			}

		}

	}

	public static void solicitaDeposito() {

		Scanner leitor = new Scanner(System.in);
		Scanner leitorResp = new Scanner(System.in);
		RepositorioDeContas repositorio = new RepositorioDeContas();
		String data = Funcoes.formataDataEHora(new Date());

		System.out.print("|Entre com o valor do depósito: ");
		double valor = leitor.nextDouble();

		try {
			ControleDeSessao.getContaLogada().deposita(valor);
			ControlePrincipal.imprimiLinhaPontilhada();
			repositorio.atualizarInformacoes(ControleDeSessao.getContaLogada(), "Depósito");
			System.out.println("|Depósito efetuado.Saldo atual " + ControleDeSessao.getContaLogada().getSaldo());
			ControleDeSessao.listaOperacoes.add("Titular : " + ControleDeSessao.usuarioLogado.getNome()
					+ " Depositou : R$" + valor + " Data : " + data);

		} catch (Exception e) {
			ControlePrincipal.imprimiLinhaPontilhada();
			System.out.println(e.getMessage());
			ControlePrincipal.imprimiLinhaPontilhada();
			while (true) {
				System.out.println("|Deseja tentar novamente ? [1]SIM / [2] NÃO ");
				ControlePrincipal.imprimiLinhaPontilhada();
				int resposta = leitorResp.nextInt();
				if (resposta == 1) {
					solicitaDeposito();
					break;
				} else if (resposta == 2) {
					break;
				} else {
					System.out.println("Resposta inválida");
				}
			}

		}

	}

	public static void executaTransferencia(Conta destino, double valor) {

		RepositorioDeContas repositorio = new RepositorioDeContas();
		Scanner leitor = new Scanner(System.in);
		String data = Funcoes.formataDataEHora(new Date());

		int confirmacao = leitor.nextInt();

		if (confirmacao == 1) {
			try {
				ControleDeSessao.getContaLogada().transfere(destino, valor);
				repositorio.atualizarInformacoes(ControleDeSessao.getContaLogada(), "Transferência");
				repositorio.atualizarInformacoes(destino, " ");
				System.out.println("Transferência efetuada com sucesso.");
				System.out.println("|Saldo atual: " + ControleDeSessao.getContaLogada().getSaldo());
				ControleDeSessao.listaOperacoes.add("Titular : " + ControleDeSessao.usuarioLogado.getNome()
						+ " Transferiu : R$" + valor + " Data : " + data);

			} catch (Exception e) {
				System.out.println(e.getMessage());

				while (true) {
					System.out.println("Deseja tentar novamente ? [1]SIM / [2] NÃO ");
					confirmacao = leitor.nextInt();
					if (confirmacao == 1) {
						solicitaTransferencia();
						break;
					} else if (confirmacao == 2) {
						break;
					} else {
						System.out.println("Resposta inválida");
					}
				}
			}
		} else {
			System.exit(0);
		}
	}

	public static void solicitaTransferencia() {

		RepositorioDeContas repositorioContas = new RepositorioDeContas();
		RepositorioClientes repositorioClientes = new RepositorioClientes();
		RepositorioFuncionario repositorioFuncionario = new RepositorioFuncionario();

		Scanner leitor = new Scanner(System.in);
		System.out.println("|Entre com o CPF do titular: ");
		String cpf = leitor.next();
		System.out.print("|Entre com o valor da transferência: ");
		double valor = leitor.nextDouble();

		Conta destino = repositorioContas.carregaMapa().get(cpf);

		if (destino != null) {

			Funcionario funcionario = repositorioFuncionario.carregaMapa().get(cpf);
			Cliente cliente = repositorioClientes.carregaMapa().get(cpf);

			if (funcionario != null) {
				ConfirmaTransferencia.imprimir(funcionario, destino);
				executaTransferencia(destino, valor);
			}

			else if (cliente != null) {
				ConfirmaTransferencia.imprimir(cliente, destino);
				executaTransferencia(destino, valor);
			}
		} else {
			System.out.println("Conta inválida !");
			System.out.println("Deseja tentar novamente ? [1] SIM [2]NÃO");
			int resp;
			resp = leitor.nextInt();
			switch (resp) {

			case 1:
				solicitaTransferencia();
				break;

			case 2:
				break;

			default:
				break;
			}
		}
	}
}
