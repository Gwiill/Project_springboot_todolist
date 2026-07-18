package br.com.guilhermewilson.primeiro_projeto_springboot.primeiroprojetospringboot.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_users")
public class UserModel {


@Id
@GeneratedValue(generator = "UUID") /*Aqui geramos ID's de de forma que sejam mais únicos, e de certa forma mais seguros*/
private UUID id;

    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;  /*Com esta @CreationTimestamp, registramos quando um log ou usuário é criado*/
    
}
