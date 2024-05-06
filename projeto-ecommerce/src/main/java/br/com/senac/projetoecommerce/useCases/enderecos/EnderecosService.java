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
    public EnderecosResponseDom carregarEnderecoById(Long id) {
        Optional<Enderecos> resultado = enderecosRepository.findById(id);
        if (resultado.isPresent()) {
            Enderecos enderecos = resultado.get();
            EnderecosResponseDom response = new EnderecosResponseDom();
            response.setClienteId(enderecos.getId());
            response.setId(enderecos.getId());
            response.setRua(enderecos.getRua());
            response.setBairro(enderecos.getBairro());
            response.setCidade(enderecos.getCidade());
            response.setEstado(enderecos.getEstado());


            return response;
        }
        return null;
    }


    //criar novos endereços
    public EnderecosResponseDom criarEnderecos(EnderecosRequestDom endereco) throws SenacExceptions {
        List<String> mensagens = ValidacoesEndereco.validarEndereco(endereco);
        if (!mensagens.isEmpty()){
            throw new SenacExceptions(mensagens);
        }

        Enderecos enderecosEntidade = new Enderecos();
        enderecosEntidade.setRua(endereco.getRua());
        enderecosEntidade.setComplemento(endereco.getComplemento());
        enderecosEntidade.setBairro(endereco.getBairro());
        enderecosEntidade.setCidade(endereco.getCidade());
        enderecosEntidade.setEstado(endereco.getEstado());

        // Verifica se o ID do cliente é válido
        Long clienteId = endereco.getClienteId();
        Optional<Clientes> clienteEncontrado = clientesRepository.findById(clienteId);
        Clientes clientes = clienteEncontrado.orElseThrow(() -> new SenacExceptions("Cliente não encontrado"));

        // Define o cliente associado ao endereço
        enderecosEntidade.setCliente(clientes);

        // Salva o endereço no banco de dados
        Enderecos resultadoEnderecos = enderecosRepository.save(enderecosEntidade);

        // Cria o objeto de resposta
        EnderecosResponseDom enderecosResponseDom = new EnderecosResponseDom();
        enderecosResponseDom.setId(resultadoEnderecos.getId());
        enderecosResponseDom.setRua(resultadoEnderecos.getRua());
        enderecosResponseDom.setComplemento(resultadoEnderecos.getComplemento());
        enderecosResponseDom.setBairro(resultadoEnderecos.getBairro());
        enderecosResponseDom.setCidade(resultadoEnderecos.getCidade());
        enderecosResponseDom.setEstado(resultadoEnderecos.getEstado());
        enderecosResponseDom.setClienteId(clienteId);

        return enderecosResponseDom;
    }


    //atualizar endereco
    public EnderecosResponseDom atualizarEndereco(Long id, EnderecosRequestDom endereco) {
        Optional<Enderecos> resultado = enderecosRepository.findById(id).map(record -> {
            record.setRua(endereco.getRua());
            record.setBairro(endereco.getBairro());
            record.setCidade(endereco.getCidade());
            record.setEstado(endereco.getEstado());

            return enderecosRepository.save(record);
        });
        if (resultado.isPresent()) {
            Enderecos enderecosEntidade = resultado.get();

            EnderecosResponseDom enderecosResponseDom = new EnderecosResponseDom();
            enderecosResponseDom.setId(enderecosEntidade.getId());
            enderecosResponseDom.setRua(enderecosEntidade.getRua());
            enderecosResponseDom.setCidade(enderecosEntidade.getCidade());
            enderecosResponseDom.setEstado(enderecosEntidade.getEstado());
            enderecosResponseDom.setClienteId(enderecosEntidade.getCliente().getId());

            return enderecosResponseDom;
        }
        return null;
    }

    //excluir endereço
    public void excluirEndereco(Long id) {
        enderecosRepository.deleteById(id);
    }
}
