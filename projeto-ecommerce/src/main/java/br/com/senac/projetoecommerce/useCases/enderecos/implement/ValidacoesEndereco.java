package br.com.senac.projetoecommerce.useCases.enderecos.implement;

import br.com.senac.projetoecommerce.useCases.enderecos.domains.EnderecosRequestDom;

import java.util.ArrayList;
import java.util.List;

public class ValidacoesEndereco {

    public static List<String> validarEndereco(EnderecosRequestDom endereco){
        List<String> mensagens = new ArrayList<>();

        EnderecosRepository enderecosRepository = null;
        if (enderecosRepository.findByClientesId(endereco.getClienteId()).size()>4){
            mensagens.add("Limite de endereços por clientes excedido");
        }
        return mensagens;
    }
}
