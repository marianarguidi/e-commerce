package br.com.senac.projetoecommerce.useCases.enderecos;

import br.com.senac.projetoecommerce.entitys.Clientes;
import br.com.senac.projetoecommerce.entitys.Enderecos;
import br.com.senac.projetoecommerce.useCases.clientes.implement.ClientesRepository;
import br.com.senac.projetoecommerce.useCases.enderecos.domains.EnderecosRequestDom;
import br.com.senac.projetoecommerce.useCases.enderecos.domains.EnderecosResponseDom;
import br.com.senac.projetoecommerce.useCases.enderecos.implement.EnderecosRepository;
import br.com.senac.projetoecommerce.useCases.enderecos.ValidacoesEndereco;
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
        List<Enderecos> resultado = enderecosRepository.findByClienteId(idCliente);
        List<EnderecosResponseDom> enderecosResponseDomList = new ArrayList<>();

        for (Enderecos dado : resultado) {
            EnderecosResponseDom enderecosResponseDom = new EnderecosResponseDom();
            enderecosResponseDom.setId(dado.getId());
            enderecosResponseDom.setRua(dado.getRua());
            enderecosResponseDom.setBairro(dado.getBairro());
            enderecosResponseDom.setCidade(dado.getCidade());
            enderecosResponseDom.setEstado(dado.getEstado());

            enderecosResponseDomList.add(enderecosResponseDom);
        }
        return enderecosResponseDomList;
    }

    //criar
    public EnderecosResponseDom criarEnderecos(EnderecosRequestDom endereco) throws SenacExceptions {
        List<String> mensagens = ValidacoesEndereco.validarEndereco(endereco, enderecosRepository);
        if (!mensagens.isEmpty()){
            throw new SenacExceptions(mensagens);
        }

        Enderecos enderecosEntidade = new Enderecos();
        enderecosEntidade.getCliente();
        enderecosEntidade.setRua(endereco.getRua());
        enderecosEntidade.setBairro(endereco.getBairro());
        enderecosEntidade.setCidade(endereco.getCidade());
        enderecosEntidade.setEstado(endereco.getEstado());

        Optional<Clientes> clienteEncontrado = clientesRepository.findById(endereco.getClienteId()); //carrega o cliente para ser possível cadastrar um endereço pois cada clientes terá varios endereços
        Clientes clientes = clienteEncontrado.get();
        enderecosEntidade.setCliente(clientes);

        Enderecos resultadoEnderecos = enderecosRepository.save(enderecosEntidade);

        EnderecosResponseDom enderecosResponseDom = new EnderecosResponseDom(); //retorno no postman
        enderecosResponseDom.setClienteId(resultadoEnderecos.getCliente().getId());
        enderecosResponseDom.setId(resultadoEnderecos.getId());
        enderecosResponseDom.setRua(resultadoEnderecos.getRua());
        enderecosResponseDom.setBairro(resultadoEnderecos.getBairro());
        enderecosResponseDom.setCidade(resultadoEnderecos.getCidade());
        enderecosResponseDom.setEstado(resultadoEnderecos.getEstado());

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
