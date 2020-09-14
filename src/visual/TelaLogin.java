package visual;

import java.util.Map;
import java.util.Scanner;

import controles.ControleDeSessao;
import controles.ControlePrincipal;
import entidades.Cliente;
import entidades.Conta;
import entidades.Funcionario;
import entidades.Pessoa;
import repositorios.RepositorioClientes;
import repositorios.RepositorioDeContas;
import repositorios.RepositorioFuncionario;
import repositorios.RepositorioSeguro;

public class TelaLogin {

	static RepositorioClientes repositorioClientes = new RepositorioClientes();
	static RepositorioFuncionario repositorioFuncionarios = new RepositorioFuncionario();
	static RepositorioDeContas repositorioContas = new RepositorioDeContas();

	static Map<String, Cliente> mapaCliente = repositorioClientes.carregaMapa();
	static Map<String, Funcionario> mapaDeFuncionarios = repositorioFuncionarios.carregaMapa();
	static Map<String, Conta> mapaDeContas = repositorioContas.carregaMapa();

	public static void main(String[] args) {

		Scanner leitor = new Scanner(System.in);

		System.out.print("|Entre com o seu cpf: ");
		String cpf = leitor.nextLine();

		ControlePrincipal.imprimiLinhaPontilhada();
		System.out.print("|Entre com a sua senha: ");
		String senha = leitor.nextLine();

		Cliente cliente = mapaCliente.get(cpf);
		Funcionario funcionario = mapaDeFuncionarios.get(cpf);
		Conta conta = mapaDeContas.get(cpf);

		if (cliente != null && cliente.getSenha().equals(senha)) {

			setaSecao(cliente, conta);

			TelaCliente.main(args);

		} else if (funcionario != null && funcionario.getSenha().equals(senha)) {
			setaSecao(funcionario, conta);

			if (funcionario.getCargo().equals("Diretor")) {
				TelaDiretor.main(args);
			}
			if (funcionario.getCargo().equals("Gerente")) {
				TelaGerente.main(args);
			}
			if (funcionario.getCargo().equals("Presidente")) {
				TelaPresidente.main(args);
			}
		} else {
			Scanner scan = new Scanner(System.in);
			int resp;

			System.out.println("|===========================================|");
			System.out.println("|            Login Inválido                 |");
			System.out.println("|Deseja tentar novamente: [1]Sim [2]Não     |");
			System.out.println("|===========================================|");
			resp = scan.nextInt();
			switch (resp) {
			case 1:
				main(args);
				break;
			case 2:
				break;

			default:
				break;
			}
		}
	}

	public static void setaSecao(Pessoa usuario, Conta conta) {
		ControleDeSessao.setUsuarioLogado(usuario);
		ControleDeSessao.setContaLogada(conta);
		ControleDeSessao.listaSeguroVida = RepositorioSeguro.carregaLista();
	}
}
