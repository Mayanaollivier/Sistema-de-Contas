package entidades;

import java.text.Collator;
import java.util.Locale;

public abstract class Pessoa implements Comparable<Pessoa> {
	protected String nome;
	protected String cpf;
	protected String senha;

	public Pessoa(String nome, String cpf, String senha) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int compareTo(Pessoa n) {
		Collator cot = Collator.getInstance(new Locale("pt", "BR"));
		if (n != null)
			return cot.compare(this.getNome(), n.getNome());
		else
			return 0;
	}
}
