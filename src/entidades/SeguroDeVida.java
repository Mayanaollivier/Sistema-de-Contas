package entidades;

public class SeguroDeVida {

	double valor;
	String cpf;
	

	public SeguroDeVida(double valor, String cpf) {

		this.valor = valor;
		this.cpf = cpf;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
