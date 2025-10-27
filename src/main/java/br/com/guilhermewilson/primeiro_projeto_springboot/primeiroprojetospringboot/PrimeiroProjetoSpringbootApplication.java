package br.com.guilhermewilson.primeiro_projeto_springboot.primeiroprojetospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.guilhermewilson")		/*Essa annotation especifica por qual pacote base deve começar o escaneamento dos processos do SpringBoot*/
public class PrimeiroProjetoSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimeiroProjetoSpringbootApplication.class, args);
	}

}
