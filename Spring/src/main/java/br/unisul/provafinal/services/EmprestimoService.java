package br.unisul.provafinal.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

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
	private EmprestimoRepository repo;
	
	@Autowired
	private ClienteRepository repocliente;
	
	@Autowired
	private LivroRepository repolivro;
	
	
	@Autowired
	private LivroService livroService;
	
	@Autowired
	private ClienteService clienteService;
	

	public Emprestimo buscar(Integer id) {
		Optional<Emprestimo> obj = repo.findById(id);
		return obj.orElse(null);
	}
	

	public List<Emprestimo> findByCliente(Integer clienteId) {
		return repo.findEmprestimos(clienteId);
	}
	

	
	public Emprestimo insert(Emprestimo obj) {
		obj.setId(null);
		//obj.setCliente(clienteService.find(1));
		//JOptionPane.showMessageDialog(null, "##############Teste"+clienteService.find(obj.getCliente().getId()));
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		//
		obj.setLivro(livroService.find(obj.getLivro().getId()));
		//obj.getDatadevolucao();
		//obj.getDataemprestimo();
		
		//emprestimoPedidoRepository.saveAll(obj.getItens());
		
		obj = repo.save(obj);
		return obj;
		
		//return obj;
	}
	
	//LISTAR TODAS
	public List<Emprestimo> findAll(){
		return repo.findAll();
	}
	


	

}