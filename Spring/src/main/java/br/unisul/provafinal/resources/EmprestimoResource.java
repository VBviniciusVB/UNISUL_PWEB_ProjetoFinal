package br.unisul.provafinal.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.unisul.provafinal.domain.Cliente;
import br.unisul.provafinal.domain.Emprestimo;
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
		Emprestimo obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//INSERIR
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Emprestimo obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
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

	
	
	// BUSCAR POR NOME DO CLIENTE
	@RequestMapping(value="/{clienteId}/cliente",method=RequestMethod.GET)
	ResponseEntity<List<Emprestimo>> findByCliente(@PathVariable Integer clienteId) {
		List<Emprestimo> list = service.findByCliente(clienteId);
		return ResponseEntity.ok().body(list);
	}
	

	
}