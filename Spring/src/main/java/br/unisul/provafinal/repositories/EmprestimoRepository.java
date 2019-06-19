package br.unisul.provafinal.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.unisul.provafinal.domain.Cliente;
import br.unisul.provafinal.domain.Emprestimo;
import br.unisul.provafinal.domain.EmprestimoPedido;
import br.unisul.provafinal.domain.Livro;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {
	@Transactional(readOnly=true)
	List<Emprestimo> findByCliente(Cliente cliente);


}
