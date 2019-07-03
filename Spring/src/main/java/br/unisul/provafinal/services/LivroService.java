package br.unisul.provafinal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.provafinal.domain.Livro;
import br.unisul.provafinal.repositories.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository rep;
	
	//BUSCAR POR ID
		public Livro find (Integer id) {
			Optional<Livro> obj = rep.findById(id);
			return obj.orElse(null);
		}
	
		//INSERIR
		public Livro insert (Livro obj) {
			obj.setId(null);
			obj.setAlugados(0);
			return rep.save(obj);
		}

		//ATUALIZAR
		public Livro update (Livro obj) {
			find(obj.getId());
			return rep.save(obj);
		}

		//DELETAR
		public void delete (Integer id) {
			find(id);
			rep.deleteById(id);
		}
		
		//LISTAR TODAS
		public List<Livro> findAll(){
			return rep.findAll();
		}
		
		//BUSCAR POR NOME ((((NOVO))))	
		public List<Livro> findByName(String nome){
			List<Livro> list = rep.findLikeNome(nome);
			return list;
		}
}