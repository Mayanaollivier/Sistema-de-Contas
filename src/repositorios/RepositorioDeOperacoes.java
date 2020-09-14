package repositorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import controles.ControleDeSessao;

public class RepositorioDeOperacoes {

	public static void salvar() {

		PrintStream stream;
		SimpleDateFormat formatado = new SimpleDateFormat("dd-MM-yyyy'-'HH-mm-ss");
		String data = formatado.format(new Date());
		
		File arquivo = new File("operacoes" + data + ".txt");

		try {

			stream = new PrintStream(arquivo);

			for (String operacao : ControleDeSessao.listaOperacoes) {
				stream.println(operacao);
				stream.println("------------------------------------------------------------------");
			}

			stream.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}
	}
}
