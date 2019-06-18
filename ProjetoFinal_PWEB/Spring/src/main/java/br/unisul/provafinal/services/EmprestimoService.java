package br.unisul.provafinal.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.provafinal.domain.Cliente;
import br.unisul.provafinal.domain.Emprestimo;
import br.unisul.provafinal.domain.Livro;
import br.unisul.provafinal.repositories.ClienteRepository;
import br.unisul.provafinal.repositories.EmprestimoRepository;
import br.unisul.provafinal.repositories.LivroRepository;

@Service
public class EmprestimoService {

	@Autowired
	private EmprestimoRepository emprestimoService;
	
	@Autowired
	private LivroService livroService;
	
	@Autowired
	private ClienteService clienteService;
	

	public Emprestimo buscar(Integer id) {
		Optional<Emprestimo> obj = emprestimoService.findById(id);
		return obj.orElse(null);
	}
	
	public Emprestimo insert(Emprestimo obj) {
		obj.setId(null);
		obj.setDataemprestimo(new Date());
		obj.setDatadevolucao(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj = emprestimoService.save(obj);
		// AKI
		return obj;
	}
	
	public List<Emprestimo> findByCliente(Integer idCliente) {
		Cliente cliente = clienteService.find(idCliente);
		return emprestimoService.findByCliente(cliente);
	}

}