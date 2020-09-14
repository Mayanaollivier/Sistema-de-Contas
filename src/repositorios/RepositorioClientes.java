package repositorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import entidades.Cliente;

public class RepositorioClientes {

	public List<Cliente> carregaLista() {
		List<Cliente> listaDeClientes = new ArrayList<>();
		try {
			Scanner leitor = new Scanner(new File("clientes.txt"));
			while (leitor.hasNextLine()) {

				String[] data = leitor.nextLine().split(",");
				String nome = data[0], cpf = data[1], senha = data[2];
				

				Cliente cliente = new Cliente(nome, cpf, senha);
				listaDeClientes.add(cliente);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return listaDeClientes;

	}

	public Map<String, Cliente> carregaMapa() {

		Map<String, Cliente> mapaDeClientes = new HashMap<>();

		for (Cliente c : carregaLista()) {
			mapaDeClientes.put(c.getCpf(), c);
		}

		return mapaDeClientes;
	}

}
