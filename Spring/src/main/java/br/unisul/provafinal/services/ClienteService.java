package br.unisul.provafinal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.provafinal.domain.Cliente;
import br.unisul.provafinal.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository rep;
	
	//BUSCAR POR ID
		public Cliente find (Integer id) {
			Optional<Cliente> obj = rep.findById(id);
			return obj.orElse(null);
		}
	
		//INSERIR
		public Cliente insert (Cliente obj) {
			obj.setId(null);
			return rep.save(obj);
		}

		//ATUALIZAR
		public Cliente update (Cliente obj) {
			find(obj.getId());
			return rep.save(obj);
		}

		//DELETAR
		public void delete (Integer id) {
			find(id);
			rep.deleteById(id);
		}
		
		//LISTAR TODAS
		public List<Cliente> findAll(){
			return rep.findAll();
		}

		
		//BUSCAR POR NOME ((((NOVO))))	
		public List<Cliente> findByName(String nome){
			List<Cliente> list = rep.findLikeNome(nome);
			return list;
		}


}