package br.unisul.provafinal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.unisul.provafinal.domain.Cliente;
import br.unisul.provafinal.domain.Emprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {
	
	@Transactional(readOnly=true)
	public List<Emprestimo> findAllByOrderById();
	
	@Transactional(readOnly=true)
	List<Emprestimo> findByCliente(Cliente cliente);
	
	@Transactional(readOnly=true)
	@Query("SELECT e FROM Emprestimo e left join e.cliente c join e.livro l WHERE c.nome like %:nome% or l.nome like %:nome% ORDER BY e.id")
	public List<Emprestimo> findPorNome(@Param("nome") String nome);
}