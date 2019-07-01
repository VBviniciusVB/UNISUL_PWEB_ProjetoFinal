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
	@Query("SELECT obj FROM Emprestimo obj WHERE obj.cliente.id = :clienteId ORDER BY obj.id")
	public List<Emprestimo> findEmprestimos(@Param("clienteId") Integer cliente_id);
	
	


}
