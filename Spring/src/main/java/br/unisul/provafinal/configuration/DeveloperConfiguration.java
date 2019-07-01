package br.unisul.provafinal.configuration;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.unisul.provafinal.services.DBService;

@Configuration
public class DeveloperConfiguration {
	
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean inicializaBancoDeDados() throws ParseException {
		if (!strategy.equals("create")) {
			return false;
		}
		dbService.inicializaBancoDeDados();
		return true;
	}
}