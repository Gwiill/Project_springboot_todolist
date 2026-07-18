package br.com.guilhermewilson.primeiro_projeto_springboot.primeiroprojetospringboot.todolist.user;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController                            /*@RestController - Classe do tipo Controller para controlar a APIREST, indentificando a anotação e configurando em cima dela mesma, processa a requisição e retorna para o usuário*/
@RequestMapping("/primeiraController")    /*@RequestMapping - funciona quando o SPRING executa ele cria uma rota com base no conteúdo da @Request, e toda vez que acessa-la, ele irá acessar a Controller*/
public class PrimeiraController {


    @GetMapping("/primeiroMetodo/{id}")    /*As annotations são funções que disponibilizam facilidades, que funcionam por debaixo dos panos, facilitando a vida do desenvolvedor.*/
    public String primeiroMetodo(@PathVariable String id) {
        return "O parametro é: " + id;                                                                
    }

    @GetMapping("/metodoComQueryParams")
    public String metodoComQueryParams(@RequestParam String id) {  /*Passando parâmetros um por vez*/
        return "O parametro com metodoComQueryParams é " + id;
    }
    
@GetMapping("/metodoComQueryParams2")
    public String metodoComQueryParams2(@RequestParam Map<String, String> allParams) { /*Com allParams passamos de forma mais dinâmica e otimizada, podemos usar no lugar de apenas "id"*/
        return "O parametro com metodoComQueryParams2 é " + allParams.entrySet();

    }

}
