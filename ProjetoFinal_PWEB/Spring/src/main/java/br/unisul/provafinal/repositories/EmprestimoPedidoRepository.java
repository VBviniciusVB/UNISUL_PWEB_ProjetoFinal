package br.unisul.provafinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unisul.provafinal.domain.EmprestimoPedido;

@Repository
public interface EmprestimoPedidoRepository extends JpaRepository<EmprestimoPedido, Integer> {

}
