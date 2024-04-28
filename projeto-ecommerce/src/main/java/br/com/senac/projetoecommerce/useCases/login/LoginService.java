package br.com.senac.projetoecommerce.useCases.login;

import br.com.senac.projetoecommerce.entitys.Usuario;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
        public boolean autenticar(Usuario usuario) {
            return "admin".equals(usuario.getUsername()) && "123456".equals(usuario.getPassword());
        }

}
