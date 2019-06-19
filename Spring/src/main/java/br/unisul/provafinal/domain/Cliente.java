package br.unisul.provafinal.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	//@OneToMany(mappedBy="livro")
	//private List<Emprestimo> emprestimos = new ArrayList<>();

	// 1 - ID
	// 2 - Nome
	// 3 - Tipo (Professor ou Aluno)
	// 4 - Sexo (Masculino ou Feminino)
	// 5 - Idade
	
	private String nome;
	//@OneToMany(mappedBy="livro")
	//private List<TipoLivro> area = new ArrayList<>();
	private String tipo;
	private String sexo;
	private Integer idade;

	
	// Fim das variáveis

	@JsonIgnore
	@OneToMany(mappedBy="cliente")
	private List<Emprestimo> emprestimos = new ArrayList<>();
	
	//
	
	public Cliente() {
		
	}

	public Cliente(Integer id, String nome, String tipo, String sexo, Integer idade) 
			 {
		super();
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.sexo = sexo;
		this.idade = idade;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	
	
	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}