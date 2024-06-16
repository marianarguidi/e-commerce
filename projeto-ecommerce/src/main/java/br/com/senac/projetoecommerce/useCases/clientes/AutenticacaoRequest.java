package br.com.senac.projetoecommerce.useCases.clientes;

import org.antlr.v4.runtime.misc.NotNull;


public class AutenticacaoRequest {

    /*@NotNull(message = "O campo 'email' é obrigatorio")*/
    private String email;

    /*@NotNull(message = "O campo 'senha' é obrigatorio")*/
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}