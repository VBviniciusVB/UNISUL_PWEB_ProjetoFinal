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

import br.unisul.provafinal.domain.Livro;

@Entity
public class Livro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	//@OneToMany(mappedBy="livro")
	//private List<Emprestimo> emprestimos = new ArrayList<>();
	
	// 1 - ID
	// 2 - Título(Nome) do Livro
	// 3 - Área(Tipo) do Livro
	// 4 - Número(Quantidade) do Livro
	// 5 - Localização na Biblioteca
	
	private String nome;

	private String area;
	private Integer estoque;
	private String local;
	
	// Fim das variáveis

	//@JsonIgnore
	//@OneToMany(mappedBy="livro")
	//private List<Emprestimo> emprestimos = new ArrayList<>();
	
	//
	
	public Livro() {
		
	}

	public Livro(Integer id, String nome, String area, Integer estoque, String local) 
			 {
		super();
		this.id = id;
		this.nome = nome;
		this.area = area;
		this.estoque = estoque;
		this.local = local;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
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
		Livro other = (Livro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}