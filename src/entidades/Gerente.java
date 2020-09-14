package entidades;

public class Gerente extends Funcionario {

	

	public Gerente(String cargo, String nome, String cpf, String senha) {
		super(cargo, nome, cpf, senha);
		
	}

	int agencia_gerenciada;
}
