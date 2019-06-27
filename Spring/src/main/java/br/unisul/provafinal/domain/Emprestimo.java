package br.unisul.provafinal.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.unisul.provafinal.domain.Cliente;
import br.unisul.provafinal.domain.EmprestimoPedido;

@Entity
public class Emprestimo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String dataemprestimo;
	
	private String datadevolucao;

	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="livro_id")
	private Livro livro;
	
	@OneToMany(mappedBy="id.emprestimo")
	private Set<EmprestimoPedido> itens = new HashSet<>();

	
	public Emprestimo() {
	}

	public Emprestimo(Integer id, Cliente cliente, Livro livro, String dataemprestimo, String datadevolucao) {
		super();
		this.id = id;
		this.livro = livro;
		this.cliente = cliente;
		this.dataemprestimo = dataemprestimo;
		this.datadevolucao = datadevolucao;
	}
	
	public Set<EmprestimoPedido> getItens() {
		return itens;
	}

	public void setItens(Set<EmprestimoPedido> itens) {
		this.itens = itens;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		Emprestimo other = (Emprestimo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido número: ");
		builder.append(getId());
		builder.append(", Dia de Retirada: ");
		builder.append(sdf.format(getDataemprestimo()));
		builder.append(", Cliente: ");
		builder.append(getCliente().getNome());
		return builder.toString();
	}
	
}