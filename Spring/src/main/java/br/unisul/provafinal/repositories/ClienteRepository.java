package br.unisul.provafinal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.unisul.provafinal.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
//	@Transactional(readOnly=true)
//	@Query("SELECT cliente FROM Cliente cliente WHERE cliente.nome LIKE %:nome%")
//	List<Cliente> findLikeNome(String nome);
	
	@Transactional(readOnly=true)
	public List<Cliente> findAllByOrderByNome();
	
	@Query("SELECT cliente FROM Cliente cliente WHERE cliente.nome LIKE %:nome%")
	List<Cliente> findLikeNome(String nome);
}
