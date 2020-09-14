package entidades;

public class ContaPoupanca extends Conta {

	


	public ContaPoupanca(String cpf_titular, int numero, int qtd_saque, int qtd_deposito, int qtd_tranferencia,
			double saldo, String agencia, String tipo) {
		super(cpf_titular, numero, qtd_saque, qtd_deposito, qtd_tranferencia, saldo, agencia, tipo);
		
	}

	public double getTaxa() {
		return 0.5;
	}
	
	public String getTipo() {
		return "Conta Poupança";
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Conta [cpf_titular=" + cpf_titular + ", numero=" + numero + ", saldo=" + saldo + ", agencia=" + agencia
				+ ", tipo=" + tipo + "]";
	}
}
