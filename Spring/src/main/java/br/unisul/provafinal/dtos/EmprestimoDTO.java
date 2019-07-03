package br.unisul.provafinal.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.unisul.provafinal.domain.Cliente;
import br.unisul.provafinal.domain.Emprestimo;
import br.unisul.provafinal.domain.Livro;

public class EmprestimoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(message="É preciso ter um cliente")
	private Cliente cliente;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(message="É preciso ter um livro")
	private Livro livro;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(message="É preciso ter uma data de aquisicao")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private String dataemprestimo;
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private String datadevolucao;
	
	public EmprestimoDTO() {
	}

	public EmprestimoDTO(Emprestimo obj) {
		id = obj.getId();
		cliente = obj.getCliente();
		livro = obj.getLivro();
		dataemprestimo = obj.getDataemprestimo();
		datadevolucao = obj.getDatadevolucao();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getDataemprestimo() {
		return dataemprestimo;
	}

	public void setDataemprestimo(String dataemprestimo) {
		this.dataemprestimo = dataemprestimo;
	}

	public String getDatadevolucao() {
		return datadevolucao;
	}

	public void setDatadevolucao(String datadevolucao) {
		this.datadevolucao = datadevolucao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
}