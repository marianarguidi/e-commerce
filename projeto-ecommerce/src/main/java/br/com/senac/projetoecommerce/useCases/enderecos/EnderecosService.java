package br.com.senac.projetoecommerce.useCases.enderecos;

import br.com.senac.projetoecommerce.entitys.Clientes;
import br.com.senac.projetoecommerce.entitys.Enderecos;
import br.com.senac.projetoecommerce.useCases.clientes.implement.ClientesRepository;
import br.com.senac.projetoecommerce.useCases.enderecos.domains.EnderecosRequestDom;
import br.com.senac.projetoecommerce.useCases.enderecos.domains.EnderecosResponseDom;
import br.com.senac.projetoecommerce.useCases.enderecos.implement.EnderecosRepository;
import br.com.senac.projetoecommerce.useCases.enderecos.implement.ValidacoesEndereco;
import br.com.senac.projetoecommerce.utils.SenacExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecosService {
    @Autowired
    private EnderecosRepository enderecosRepository;
    @Autowired
    private ClientesRepository clientesRepository;

    //carregar os enderecos de apenas um cliente
    public List<EnderecosResponseDom> carregarEnderecosCliente(Long idCliente) {
        List<Enderecos> resultado = enderecosRepository.findByClientesId(idCliente);
        List<EnderecosResponseDom> enderecosResponseDomList = new ArrayList<>();

        for (Enderecos dado : resultado) {
            EnderecosResponseDom enderecosResponseDom = new EnderecosResponseDom();
            enderecosResponseDom.setId(dado.getId());
            enderecosResponseDom.setRua(dado.getRua());
            enderecosResponseDom.setComplemento(dado.getComplemento());
            enderecosResponseDom.setBairro(dado.getBairro());
            enderecosResponseDom.setCidade(dado.getCidade());
            enderecosResponseDom.setEstado(dado.getEstado());

            enderecosResponseDomList.add(enderecosResponseDom);
        }
        return enderecosResponseDomList;
    }

    //criar novos endereços
    public EnderecosResponseDom criarEnderecos(EnderecosRequestDom endereco) throws SenacExceptions {
        List<String> mensagens = ValidacoesEndereco.validarEndereco(endereco);
        if (!mensagens.isEmpty()){
            throw new SenacExceptions(mensagens);
        }

        Enderecos enderecosEntidade = new Enderecos();
        enderecosEntidade.getClientes();
        enderecosEntidade.setRua(endereco.getRua());
        enderecosEntidade.setComplemento(endereco.getComplemento());
        enderecosEntidade.setBairro(endereco.getBairro());
        enderecosEntidade.setCidade(endereco.getCidade());
        enderecosEntidade.setEstado(endereco.getEstado());

        Optional<Clientes> clienteEncontrado = clientesRepository.findById(endereco.getClienteId());
        Clientes clientes = clienteEncontrado.get();
        enderecosEntidade.setClientes(clientes);

        Enderecos resultadoEnderecos = enderecosRepository.save(enderecosEntidade);

        EnderecosResponseDom enderecosResponseDom = new EnderecosResponseDom(); //retorno no postman
        enderecosResponseDom.setClienteId(resultadoEnderecos.getClientes().getId());
        enderecosResponseDom.setId(resultadoEnderecos.getId());
        enderecosResponseDom.setRua(resultadoEnderecos.getRua());
        enderecosResponseDom.setComplemento(resultadoEnderecos.getComplemento());
        enderecosResponseDom.setBairro(resultadoEnderecos.getBairro());
        enderecosResponseDom.setCidade(resultadoEnderecos.getCidade());
        enderecosResponseDom.setEstado(resultadoEnderecos.getEstado());

        return enderecosResponseDom;
    }

    //excluir endereço
    public void excluirEndereco(Long id) {
        enderecosRepository.deleteById(id);
    }
}
