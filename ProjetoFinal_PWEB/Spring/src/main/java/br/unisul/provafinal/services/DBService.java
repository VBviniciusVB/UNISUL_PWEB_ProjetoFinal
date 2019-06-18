package br.unisul.provafinal.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisul.provafinal.domain.Livro;
import br.unisul.provafinal.domain.Cliente;
import br.unisul.provafinal.domain.Emprestimo;
import br.unisul.provafinal.domain.EmprestimoPedido;
import br.unisul.provafinal.repositories.ClienteRepository;
import br.unisul.provafinal.repositories.LivroRepository;
import br.unisul.provafinal.repositories.EmprestimoRepository;
import br.unisul.provafinal.repositories.EmprestimoPedidoRepository;

@Service
public class DBService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EmprestimoPedidoRepository EmprestimoPedidoRepository;
	
	@Autowired
	private EmprestimoRepository EmprestimoRepository;
	
	public void inicializaBancoDeDados() throws ParseException {	
		
		Livro livro1 = new Livro(null, "Sherlock Holmes", "Mistério", 4, "Pratilheira de Lançamentos");
		Livro livro2 = new Livro(null, "Turma da Mônica", "Infantil", 7, "Pratilheira de Baixo");
		Livro livro3 = new Livro(null, "A Arte da Guerra", "Histórico", 2, "Pratilheira do Meio");
		
		livroRepository.saveAll(Arrays.asList(livro1,livro2,livro3));
		
		Cliente cliente1 = new Cliente(null, "Vinicius", "Aluno", "Masculino", 21);
		Cliente cliente2 = new Cliente(null, "Lisa", "Aluno", "Feminino", 41);
		Cliente cliente3 = new Cliente(null, "Dimas", "Professor", "Masculino", 34);
		Cliente cliente4 = new Cliente(null, "Charles", "Aluno", "Masculino", 8);
		
		clienteRepository.saveAll(Arrays.asList(cliente1,cliente2,cliente3,cliente4));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Emprestimo ped1 = new Emprestimo(null, cliente4, livro1, sdf.parse("06/07/2015 10:32"), sdf.parse("13/07/2015 10:32"));
		Emprestimo ped2 = new Emprestimo(null, cliente3, livro3, sdf.parse("09/07/2015 16:19"), sdf.parse("19/07/2015 16:19"));
		//cliente4.getEmprestimos().addAll(Arrays.asList(ped1));
		//cliente3.getEmprestimos().addAll(Arrays.asList(ped2));
		
		EmprestimoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		EmprestimoPedido ip1 = new EmprestimoPedido(ped1, livro1, 1);
		EmprestimoPedido ip2 = new EmprestimoPedido(ped2, livro3, 1);
		
		ped1.getItens().addAll(Arrays.asList(ip1));
		ped2.getItens().addAll(Arrays.asList(ip2));

		EmprestimoPedidoRepository.saveAll(Arrays.asList(ip1, ip2));
		
		
	}

}