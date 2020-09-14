package repositorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import entidades.Diretor;
import entidades.Funcionario;
import entidades.Gerente;
import entidades.Presidente;

public class RepositorioFuncionario {

	public List<Funcionario> carregaLista() {
		List<Funcionario> listaDeFuncionarios = new ArrayList<>();
		Funcionario funcionario = null;
		try {
			Scanner leitor = new Scanner(new File("funcionarios.txt"));
			while (leitor.hasNextLine()) {

				String[] data = leitor.nextLine().split(",");
				String tipo = data[0], nome = data[1], cpf = data[2], senha = data[3];

				if (tipo.equals("Gerente")) {

					funcionario = new Gerente(tipo, nome, cpf, senha);

				} else if (tipo.equals("Diretor")) {

					funcionario = new Diretor(tipo, nome, cpf, senha);
				} else if (tipo.equals("Presidente")) {

					funcionario = new Presidente(tipo, nome, cpf, senha);
				}

				listaDeFuncionarios.add(funcionario);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return listaDeFuncionarios;

	}

	public Map<String, Funcionario> carregaMapa() {

		Map<String, Funcionario> mapaDeFuncionarios = new HashMap<>();

		for (Funcionario f : carregaLista()) {
			mapaDeFuncionarios.put(f.getCpf(), f);
		}

		return mapaDeFuncionarios;
	}

}
