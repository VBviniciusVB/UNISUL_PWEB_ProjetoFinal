package br.unisul.provafinal.dtos;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import br.unisul.provafinal.domain.Cliente;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="O livro deve ter um nome")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(message="É obrigatório dizer se você é professor ou aluno")
	private String tipo;

	private String sexo;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=6, message="Para ser cliente deve ter no mínimo 6 anos de idade")
	private Integer idade;

	private Integer alugou;


	public ClienteDTO() {
	}

	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		tipo = obj.getTipo();
		sexo = obj.getSexo();
		idade = obj.getIdade();
		alugou = obj.getAlugou();
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

	public Integer getAlugou() {
		return alugou;
	}

	public void setAlugou(Integer alugou) {
		this.alugou = alugou;
	}
	
}