package entidades;

public abstract class Funcionario extends Pessoa {

	private String cargo;

	public Funcionario(String cargo, String nome, String cpf, String senha) {
		super(nome, cpf, senha);
		this.cargo = cargo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}
