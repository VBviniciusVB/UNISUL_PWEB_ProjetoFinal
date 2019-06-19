package br.unisul.provafinal.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EmprestimoPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private EmprestimoPedidoPK id = new EmprestimoPedidoPK();

	private Integer quantidade;

	public EmprestimoPedido() {
	}

	public EmprestimoPedido (Emprestimo emprestimo, Livro livro, Cliente cliente, Integer quantidade) {
		super();
		id.setEmprestimo(emprestimo);
		id.setLivro(livro);
		id.setCliente(cliente);
		this.quantidade = quantidade;
	}

    @JsonIgnore
	public Emprestimo getEmprestimo() {
		return id.getEmprestimo();
	}
    
    public void setEmprestimo (Emprestimo emprestimo) {
		id.setEmprestimo(emprestimo);
	}
    
    //
    
    public void setLivro(Livro livro) {
		id.setLivro(livro);
	}

	public Livro getLivro() {
		return id.getLivro();
	}
	
    public void setCliente(Cliente cliente) {
		id.setCliente(cliente);
	}

	public Cliente getCliente() {
		return id.getCliente();
	}
	
	//
	
    public EmprestimoPedidoPK getId() {
		return id;
	}

	public void setId(EmprestimoPedidoPK id) {
		this.id = id;
	}
	
	//

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
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
		EmprestimoPedido other = (EmprestimoPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(", Livro: ");
		builder.append(getLivro().getNome());
		builder.append(", Cliente: ");
		builder.append(getCliente().getNome());
		builder.append(", Quantidade: ");
		builder.append(getQuantidade());
		builder.append("\n");
		return builder.toString();
	}
	
	
}