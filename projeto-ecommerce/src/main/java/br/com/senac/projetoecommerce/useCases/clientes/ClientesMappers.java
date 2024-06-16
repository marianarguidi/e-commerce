package br.com.senac.projetoecommerce.useCases.clientes;

import br.com.senac.projetoecommerce.entitys.Clientes;
import br.com.senac.projetoecommerce.useCases.clientes.domains.ClienteRequestDom;
import br.com.senac.projetoecommerce.useCases.clientes.domains.ClienteResponseDom;
import br.com.senac.projetoecommerce.utils.SenacExceptions;

public class ClientesMappers {
    public static Clientes clientesRequestDomToClientes(ClienteRequestDom in){
        Clientes out = new Clientes();
        out.setNome(in.getNome());
        out.setSobrenome(in.getSobrenome());
        out.setEmail(in.getEmail());
        /*out.setDataNascimento(in.getDataNascimento());
        out.setSexo(in.getSexo());*/
        out.setCpf(in.getCpf());
        out.setTelefone(in.getTelefone());

        return out;
    }

    public static Clientes criarClientesRequestDomToClientes(ClienteRequestDom in) throws SenacExceptions {
        Clientes out = clientesRequestDomToClientes(in);

        out.setSenha(in.getSenha());

        return out;
    }

    public static ClienteResponseDom clientesToClientesResponseDom(Clientes in){
        ClienteResponseDom out = new ClienteResponseDom();
        out.setNome(in.getNome());
        out.setSobrenome(in.getSobrenome());
        out.setEmail(in.getEmail());
       /* out.setDataNascimento(in.getDataNascimento());
        out.setSexo(in.getSexo());*/
        out.setId(in.getId());

        return out;
    }
}
