package br.com.senac.projetoecommerce.useCases.enderecos;

import br.com.senac.projetoecommerce.useCases.enderecos.domains.EnderecosRequestDom;
import br.com.senac.projetoecommerce.useCases.enderecos.implement.EnderecosRepository;

import java.util.ArrayList;
import java.util.List;

public class ValidacoesEndereco {

    public static List<String> validarEndereco(EnderecosRequestDom endereco, EnderecosRepository enderecosRepository){
        List<String> mensagens = new ArrayList<>();

        if (enderecosRepository.findByClienteId(endereco.getClienteId()).size() >= 4){
            mensagens.add("Limite de endereços por clientes excedido");
        }

        if (endereco.getRua() == null || endereco.getRua().isEmpty()){
            mensagens.add("Rua não informada");
        }
        if (endereco.getBairro() == null || endereco.getBairro().isEmpty()){
            mensagens.add("Bairro não informado");
        }
        if (endereco.getCidade() == null || endereco.getCidade().isEmpty()){
            mensagens.add("Cidade não informada");
        }
        if (endereco.getEstado() == null || endereco.getEstado().isEmpty()){
            mensagens.add("Estado não informado");
        }
        if (endereco.getClienteId() == null || endereco.getClienteId().describeConstable().isEmpty()){
            mensagens.add("Cliente não informado");
        }

        return mensagens;
    }
}
