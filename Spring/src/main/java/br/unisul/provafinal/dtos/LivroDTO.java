package br.unisul.provafinal.dtos;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import br.unisul.provafinal.domain.Livro;

public class LivroDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="O livro deve ter um nome")
	private String nome;
	
	private String area;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=1, message="O livro deve ter pelo menos 1 em estoque")
	private Integer estoque;
	
	private String local;

	public LivroDTO() {
	}

	public LivroDTO(Livro obj) {
		id = obj.getId();
		nome = obj.getNome();
		area = obj.getArea();
		estoque = obj.getEstoque();
		local = obj.getLocal();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
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

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
}