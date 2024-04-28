package br.com.senac.projetoecommerce.controllers;

import br.com.senac.projetoecommerce.entitys.Usuario;
import br.com.senac.projetoecommerce.useCases.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

        @Autowired
        private LoginService authenticationService;

        @PostMapping("/login")
        public String login(@RequestBody Usuario usuario) {
            if (authenticationService.autenticar(usuario)) {
                return "Login bem-sucedido!"; // ou redirecionar para outra página
            } else {
                return "Credenciais inválidas. Tente novamente ou cadastre-se.";
            }
        }
}
