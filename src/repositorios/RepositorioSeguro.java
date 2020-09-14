package repositorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import controles.ControleDeSessao;
import entidades.SeguroDeVida;

public class RepositorioSeguro {

	public static List<SeguroDeVida> carregaLista() {
		List<SeguroDeVida> listaDeAssegurados = new ArrayList<>();
		try {
			Scanner leitor = new Scanner(new File("SeguroVida.txt"));
			while (leitor.hasNextLine()) {

				String[] data = leitor.nextLine().split(",");
				String valor = data[0], cpf = data[1];

				SeguroDeVida seguro = new SeguroDeVida(Double.parseDouble(valor), cpf);
				listaDeAssegurados.add(seguro);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return listaDeAssegurados;
	}

	public static Map<String, SeguroDeVida> carregaMapa() {

		Map<String, SeguroDeVida> mapaDeAssegurados = new HashMap<>();

		for (SeguroDeVida sv : carregaLista()) {
			mapaDeAssegurados.put(sv.getCpf(), sv);
		}

		return mapaDeAssegurados;
	}

	public static void salvar() {

		PrintStream stream;
		File arquivo = new File("SeguroVida.txt");

		try {

			stream = new PrintStream(arquivo);

			for (SeguroDeVida sv : ControleDeSessao.listaSeguroVida) {
				stream.println(sv.getValor() + "," + sv.getCpf());
			}

			stream.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}
	}
}
