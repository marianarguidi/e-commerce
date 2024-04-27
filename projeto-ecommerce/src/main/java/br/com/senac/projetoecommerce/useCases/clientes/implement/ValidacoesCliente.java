package br.com.senac.projetoecommerce.useCases.clientes.implement;

import br.com.senac.projetoecommerce.useCases.clientes.domains.ClienteRequestDom;

import java.util.ArrayList;
import java.util.List;

public class ValidacoesCliente {

    public static List<String> validarCliente(ClienteRequestDom cliente){
        List<String> mensagens = new ArrayList<>();

        if (cliente.getNome() == null || cliente.getNome().equals("")){
            mensagens.add("Nome não informado");
        }
        if (cliente.getSobrenome() == null || cliente.getSobrenome().equals("")){
            mensagens.add("Sobrenome não informado");
        }
        if (cliente.getEmail() == null || cliente.getEmail().equals("")){
            mensagens.add("Email não informado");
        }
        if (cliente.getTelefone() == null || cliente.getTelefone().equals("")){
            mensagens.add("Telefone não informado");
        }
        return mensagens;
    }
}
