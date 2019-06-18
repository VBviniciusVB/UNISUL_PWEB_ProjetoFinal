package br.unisul.provafinal.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.unisul.provafinal.domain.Livro;
import br.unisul.provafinal.dtos.LivroDTO;
import br.unisul.provafinal.services.LivroService;
import br.unisul.provafinal.resources.utils.URL;

@RestController
	@RequestMapping(value="/livros")
	public class LivroResource {
		
		@Autowired
		private LivroService service;

		//GET - Buscar por ID
		//GET - Buscar por Nome
		//POST - Inserir
		//PUT - Atualizar
		//DELETE - Deletar
		
		//BUSCAR POR ID
		@RequestMapping(value="/{id}",method=RequestMethod.GET)
		public ResponseEntity<Livro> find(@PathVariable Integer id){
			Livro obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		}
				
		//BUSCAR POR NOME ((((NOVO))))
		@RequestMapping(value="/busca",method=RequestMethod.GET)
		public ResponseEntity<List<Livro>> find(@RequestParam(value="nome", defaultValue="")String nome){
			List <Livro> list = service.findByName(URL.decodeParam(nome));
			return ResponseEntity.ok().body(list);
		}

		//INSERIR
		@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Void>insert(@RequestBody Livro obj){
			obj = service.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
					path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}

		//ATUALIZAR
		@RequestMapping(value="/{id}", method=RequestMethod.PUT)
		public ResponseEntity<Void> update(@RequestBody Livro obj, @PathVariable Integer id){
			obj.setId(id);
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
		}


		//EXCLUIR
		@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
		public ResponseEntity<Void> delete(@PathVariable Integer id) {
			service.delete(id);
			return ResponseEntity.noContent().build();
		}

		//LISTAR TODAS
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<LivroDTO>> findAll() {
			List<Livro> lista = service.findAll();

			List<LivroDTO> listaDTO = new ArrayList<LivroDTO>();
			for (Livro e : lista) {
				listaDTO.add(new LivroDTO(e));
			}
			return ResponseEntity.ok().body(listaDTO);
		}
	
}