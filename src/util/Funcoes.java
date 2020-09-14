package util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date; 

public class Funcoes {

	public static String formataData(Date data) {
		SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
		
		return formatado.format(data);

	}

	public static String formataDataEHora(Date data) {
		SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy'-'HH:mm:ss");
	
		return formatado.format(data);
	}
	
	
}
