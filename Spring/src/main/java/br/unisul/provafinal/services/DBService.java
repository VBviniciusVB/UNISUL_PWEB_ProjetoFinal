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
		
		Livro livro1 = new Livro(null, "Sherlock Holmes", "Conto", 4, "2", 0);
		Livro livro2 = new Livro(null, "Turma da Mônica", "Comédia", 7, "13", 0);
		Livro livro3 = new Livro(null, "A Arte da Guerra", "Tragédia", 2, "20", 0);

		Cliente cliente1 = new Cliente(null, "Vinicius", "Aluno", "Masculino", 21, 0);
		Cliente cliente2 = new Cliente(null, "Lisa", "Aluno", "Feminino", 41, 0);
		Cliente cliente3 = new Cliente(null, "Dimas", "Professor", "Masculino", 34, 0);
		Cliente cliente4 = new Cliente(null, "Charles", "Aluno", "Masculino", 8, 0);
		
		livroRepository.saveAll(Arrays.asList(livro1,livro2,livro3));
		clienteRepository.saveAll(Arrays.asList(cliente1,cliente2,cliente3,cliente4));
		
		Emprestimo ped1 = new Emprestimo(null, cliente1, livro1,"14/06/2019" ,"21/06/2019");
		Emprestimo ped2 = new Emprestimo(null, cliente2, livro2,"09/07/2019" ,"16/07/2019");
		
		EmprestimoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		livro1.setAlugados(livro1.getAlugados()+1);
		livro2.setAlugados(livro2.getAlugados()+1);
		
		livroRepository.saveAll(Arrays.asList(livro1,livro2,livro3));
		
		cliente1.setAlugou(cliente1.getAlugou()+1);
		cliente2.setAlugou(cliente2.getAlugou()+1);
		
		clienteRepository.saveAll(Arrays.asList(cliente1,cliente2,cliente3,cliente4));
		
		//cliente1.getEmprestimos().addAll(Arrays.asList(ped1));
		//cliente2.getEmprestimos().addAll(Arrays.asList(ped2));
		//livro1.getEmprestimos().addAll(Arrays.asList(ped1));
		//livro2.getEmprestimos().addAll(Arrays.asList(ped2));

		//EmprestimoPedidoRepository.saveAll(Arrays.asList(ip1, ip2));

	}
}