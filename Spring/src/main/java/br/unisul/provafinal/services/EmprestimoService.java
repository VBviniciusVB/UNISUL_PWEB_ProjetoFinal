package br.unisul.provafinal.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.provafinal.domain.Cliente;
import br.unisul.provafinal.domain.Emprestimo;
import br.unisul.provafinal.domain.EmprestimoPedido;
import br.unisul.provafinal.domain.Livro;
import br.unisul.provafinal.repositories.ClienteRepository;
import br.unisul.provafinal.repositories.EmprestimoPedidoRepository;
import br.unisul.provafinal.repositories.EmprestimoRepository;
import br.unisul.provafinal.repositories.LivroRepository;

@Service
public class EmprestimoService {

	@Autowired
	private EmprestimoRepository repo;
	
	@Autowired
	private EmprestimoPedidoRepository emprestimoPedidoRepository;
	
	@Autowired
	private LivroService livroService;
	
	@Autowired
	private ClienteService clienteService;
	

	public Emprestimo buscar(Integer id) {
		Optional<Emprestimo> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
	public Emprestimo insert(Emprestimo obj) {
		obj.setId(null);
//setar data
		obj.setLivro(livroService.find(obj.getLivro().getId()));
		obj = repo.save(obj);
		
		for (EmprestimoPedido ip : obj.getItens()) {
			ip.setCliente(clienteService.find(ip.getCliente().getId()));
			ip.setEmprestimo(obj);
		}
		emprestimoPedidoRepository.saveAll(obj.getItens());
		return obj;
	}
	
	//LISTAR TODAS
	public List<Emprestimo> findAll(){
		return repo.findAll();
	}
	
	public List<Emprestimo> findByCliente(Integer idCliente) {
		Cliente cliente = clienteService.find(idCliente);
		return repo.findByCliente(cliente);
	}

}