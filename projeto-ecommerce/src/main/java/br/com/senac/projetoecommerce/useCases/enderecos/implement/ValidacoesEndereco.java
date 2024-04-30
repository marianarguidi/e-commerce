package br.com.senac.projetoecommerce.useCases.enderecos.implement;

import br.com.senac.projetoecommerce.useCases.enderecos.domains.EnderecosRequestDom;

import java.util.ArrayList;
import java.util.List;

public class ValidacoesEndereco {

    public static List<String> validarEndereco(EnderecosRequestDom endereco){
        List<String> mensagens = new ArrayList<>();

        EnderecosRepository enderecosRepository = null;
        if (enderecosRepository.findByClienteId(endereco.getClienteId()).size()>4){
            mensagens.add("Limite de endereços por clientes excedido");
        }

        if (endereco.getRua() == null || endereco.getRua().equals("")){
            mensagens.add("Rua não informada");
        }
        if (endereco.getBairro() == null || endereco.getBairro().equals("")){
            mensagens.add("Bairro não informado");
        }
        if (endereco.getCidade() == null || endereco.getCidade().equals("")){
            mensagens.add("Cidade não informada");
        }
        if (endereco.getEstado() == null || endereco.getEstado().equals("")){
            mensagens.add("Estado não informado");
        }

        return mensagens;
    }
}
