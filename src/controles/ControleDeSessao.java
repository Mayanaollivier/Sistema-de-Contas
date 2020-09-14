package controles;

import java.util.ArrayList;
import java.util.List;

import entidades.Conta;
import entidades.Pessoa;
import entidades.SeguroDeVida;

public class ControleDeSessao {

	public static Conta contaLogada;
	public static Pessoa usuarioLogado;

	public static List<SeguroDeVida> listaSeguroVida = new ArrayList<>();

	public static List<String> listaOperacoes = new ArrayList<>();

	public static Conta getContaLogada() {
		return contaLogada;
	}

	public static void setContaLogada(Conta contaLogada) {
		ControleDeSessao.contaLogada = contaLogada;
	}

	public static Pessoa getUsuarioLogado() {
		return usuarioLogado;
	}

	public static void setUsuarioLogado(Pessoa usuarioLogado) {
		ControleDeSessao.usuarioLogado = usuarioLogado;
	}

}
