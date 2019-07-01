package br.unisul.provafinal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.unisul.provafinal.domain.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
	
	@Query("SELECT livro FROM Livro livro WHERE livro.nome LIKE %:nome%")
	List<Livro> findLikeNome(String nome);
	
}
