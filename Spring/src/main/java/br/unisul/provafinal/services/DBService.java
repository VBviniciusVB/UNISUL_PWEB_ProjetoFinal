package br.unisul.provafinal.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.provafinal.domain.Livro;
import br.unisul.provafinal.domain.Cliente;
import br.unisul.provafinal.domain.Emprestimo;
import br.unisul.provafinal.repositories.ClienteRepository;
import br.unisul.provafinal.repositories.LivroRepository;
import br.unisul.provafinal.repositories.EmprestimoRepository;

@Service
public class DBService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	
	@Autowired
	private EmprestimoRepository EmprestimoRepository;
	
	public void inicializaBancoDeDados() throws ParseException {	
		
		Livro livro1 = new Livro(null, "Sherlock Holmes", "Conto", 4, "2");
		Livro livro2 = new Livro(null, "Turma da Mônica", "Comédia", 7, "13");
		Livro livro3 = new Livro(null, "A Arte da Guerra", "Tragédia", 2, "20");

		Cliente cliente1 = new Cliente(null, "Vinicius", "Aluno", "Masculino", 21);
		Cliente cliente2 = new Cliente(null, "Lisa", "Aluno", "Feminino", 41);
		Cliente cliente3 = new Cliente(null, "Dimas", "Professor", "Masculino", 34);
		Cliente cliente4 = new Cliente(null, "Charles", "Aluno", "Masculino", 8);
		
		livroRepository.saveAll(Arrays.asList(livro1,livro2,livro3));
		clienteRepository.saveAll(Arrays.asList(cliente1,cliente2,cliente3,cliente4));
		
		Emprestimo ped1 = new Emprestimo(null, cliente1, livro1,"06/07/2015","13/07/2015");
		Emprestimo ped2 = new Emprestimo(null, cliente2, livro2,"09/07/2015","19/07/2015");
		
		cliente1.getEmprestimos().addAll(Arrays.asList(ped1));
		cliente2.getEmprestimos().addAll(Arrays.asList(ped2));
		livro1.getEmprestimos().addAll(Arrays.asList(ped1));
		livro2.getEmprestimos().addAll(Arrays.asList(ped2));
		

		EmprestimoRepository.saveAll(Arrays.asList(ped1, ped2));	
		
//		EmprestimoPedido ip1 = new EmprestimoPedido(ped1, livro1, cliente4, 1);
//		EmprestimoPedido ip2 = new EmprestimoPedido(ped2, livro3, cliente3, 1);
//		
//		ped1.getItens().addAll(Arrays.asList(ip1));
//		ped2.getItens().addAll(Arrays.asList(ip2));
//
//		EmprestimoPedidoRepository.saveAll(Arrays.asList(ip1, ip2));
		
		
	}

}