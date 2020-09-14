package entidades;

import entidades.exception.SaldoException;

public abstract class Conta {

	protected String cpf_titular;
	protected int numero;
	protected double saldo;
	protected String agencia;
	protected String tipo;
	protected int qtd_saque;
	protected int qtd_deposito;
	protected int qtd_tranferencia;
	protected double taxa = 0;

	public double getTaxa() {
		return taxa;
	}

	public String getCpf_Titular() {
		return cpf_titular;
	}

	public void setCpf_Titular(String cpf_titular) {
		this.cpf_titular = cpf_titular;
	}

	public Conta(String cpf_titular, int numero, int qtd_saque, int qtd_deposito, int qtd_tranferencia, double saldo,
			String agencia, String tipo) {
		super();
		this.cpf_titular = cpf_titular;
		this.numero = numero;
		this.qtd_saque = qtd_saque;
		this.qtd_deposito = qtd_deposito;
		this.qtd_tranferencia = qtd_tranferencia;
		this.saldo = saldo;
		this.agencia = agencia;
		this.tipo = tipo;
	}

	public int getQtd_saque() {
		return qtd_saque;
	}

	public void setQtd_saque(int qtd_saque) {
		this.qtd_saque = qtd_saque;
	}

	public int getQtd_deposito() {
		return qtd_deposito;
	}

	public void setQtd_deposito(int qtd_deposito) {
		this.qtd_deposito = qtd_deposito;
	}

	public int getQtd_tranferencia() {
		return qtd_tranferencia;
	}

	public void setQtd_tranferencia(int qtd_tranferencia) {
		this.qtd_tranferencia = qtd_tranferencia;
	}

	public void saca(double valor) throws SaldoException {
		if (this.saldo < valor) {
			throw new SaldoException("Saldo insuficiente,saldo atual é " + this.getSaldo());
		} else if (valor <= 0) {
			throw new SaldoException("Entre com valor maior que (0) zero");

		} else {

			this.saldo = this.saldo - valor;
		}
	}

	public void deposita(double valor) throws Exception {
		if (valor <= 0) {
			throw new Exception("Entre com valor maior que (0) zero");
		}
		this.saldo = this.saldo + valor;
	}

	public void transfere(Conta destino, double valor) throws Exception {
		try {
			this.saca(valor);
			destino.deposita(valor);
		} catch (Exception e) {
			throw e;
		}

	}

	/*
	 * public String imprimeConfirmaTransferencia() {
	 * 
	 * 
	 * }
	 */

	public String getCpf_titular() {
		return cpf_titular;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public double getSaldo() {
		return saldo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Conta [cpf_titular=" + cpf_titular + ", numero=" + numero + ", saldo=" + saldo + ", agencia=" + agencia
				+ ", tipo=" + tipo + "]";
	}

}
