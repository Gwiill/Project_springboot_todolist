package br.com.guilhermewilson.primeiro_projeto_springboot.primeiroprojetospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Modificador
 * public
 * private
 * protected
 */

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private IUserReposity userReposity;

    @PostMapping("/")
    public UserModel create(@RequestBody UserModel userModel) {
      var userCreated = this.userReposity.save(userModel);
      return userCreated;
    }
}
