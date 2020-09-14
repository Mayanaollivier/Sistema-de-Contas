package entidades;

import entidades.exception.SaldoException;

public class ContaCorrente extends Conta {

	

	public ContaCorrente(String cpf_titular, int numero, int qtd_saque, int qtd_deposito, int qtd_tranferencia,
			double saldo, String agencia, String tipo) {
		super(cpf_titular, numero, qtd_saque, qtd_deposito, qtd_tranferencia, saldo, agencia, tipo);
		
	}

	public String getTipo() {
		return "Conta Corrente";
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public void saca(double valor) throws SaldoException {
		if (this.saldo < valor) {
			throw new SaldoException("Saldo insuficiente,saldo atual é " + this.getSaldo());
		} else if (valor <= 0) {
			throw new SaldoException("Entre com valor maior que (0) zero");

		} else {

			this.saldo = this.saldo - valor - 0.10;
		}
	}

	@Override
	public void deposita(double valor) throws Exception {
		if (valor <= 0) {
			throw new Exception("Entre com valor maior que (0) zero");
		}
		this.saldo = this.saldo + valor - 0.10;
	}

	@Override
	public void transfere(Conta destino, double valor) throws Exception {
		if (this.saldo < valor) {
			throw new SaldoException("Saldo insuficiente,saldo atual é " + this.getSaldo());
		} else if (valor <= 0) {
			throw new SaldoException("Entre com valor maior que (0) zero");

		} else {

			this.saldo = this.saldo - valor - 0.20;
		}

		destino.saldo = destino.saldo + valor;

	}
	@Override
	public String toString() {
		return "Conta [cpf_titular=" + cpf_titular + ", numero=" + numero + ", saldo=" + saldo + ", agencia=" + agencia
				+ ", tipo=" + tipo + "]";
	}
	

}
