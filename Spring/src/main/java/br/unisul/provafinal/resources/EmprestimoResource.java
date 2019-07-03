package br.unisul.provafinal.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.unisul.provafinal.domain.Cliente;
import br.unisul.provafinal.domain.Emprestimo;
import br.unisul.provafinal.domain.Livro;
import br.unisul.provafinal.dtos.ClienteDTO;
import br.unisul.provafinal.dtos.EmprestimoDTO;
import br.unisul.provafinal.resources.utils.URL;
import br.unisul.provafinal.services.ClienteService;
import br.unisul.provafinal.services.EmprestimoService;

@RestController
@RequestMapping(value="/emprestimos")
public class EmprestimoResource {

	@Autowired
	private EmprestimoService service;

	@Autowired
	private ClienteService servicecliente;
	
	//Buscar por ID
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Emprestimo obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//LISTAR TODAS
	@RequestMapping(value="/busca", method=RequestMethod.GET)
	public ResponseEntity<List<EmprestimoDTO>> findAll(@RequestParam(required = false) String nome) {
		List<Emprestimo> lista;
		if (StringUtils.hasText(nome)) {
			lista = service.findPorNome(nome);
		} else {
			lista = service.findAll();
		}
		List<EmprestimoDTO> listaDTO = lista.stream().map(emp -> new EmprestimoDTO(emp)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);
	}
	
	//INSERIR
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Emprestimo obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	// ATUALIZAR
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Emprestimo obj, @PathVariable Integer id){
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
			public ResponseEntity<List<EmprestimoDTO>> findAll() {
				List<Emprestimo> lista = service.findAll();

				List<EmprestimoDTO> listaDTO = new ArrayList<EmprestimoDTO>();
				for (Emprestimo e : lista) {
					listaDTO.add(new EmprestimoDTO(e));
				}
				return ResponseEntity.ok().body(listaDTO);
			}

	

	
}