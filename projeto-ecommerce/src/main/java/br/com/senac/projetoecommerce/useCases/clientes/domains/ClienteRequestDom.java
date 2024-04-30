package br.com.senac.projetoecommerce.useCases.clientes.domains;

import br.com.senac.projetoecommerce.useCases.enderecos.domains.EnderecosRequestDom;

public class ClienteRequestDom {

    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;
    private EnderecosRequestDom enderecos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public EnderecosRequestDom getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(EnderecosRequestDom enderecos) {
        this.enderecos = enderecos;
    }
}
