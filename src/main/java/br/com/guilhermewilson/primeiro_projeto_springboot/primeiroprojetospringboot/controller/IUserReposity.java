package br.com.guilhermewilson.primeiro_projeto_springboot.primeiroprojetospringboot.controller;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserReposity extends JpaRepository<UserModel, UUID> {

    // jeito certo: findBy + NomeDoCampo
    UserModel findByUsername(String username);
}


/*O JpaRepository já traz vários métodos prontos para manipulação do banco de dados, como save, delete, findAll, findById, etc.
podemos usar o JPA junto com H2 que usa SQL, assim podemos usar para simulações de amarzenamento e manipulações de dados em tabelas. */
