package br.com.senac.projetoecommerce.useCases.pedidoFinalizado;

import br.com.senac.projetoecommerce.entitys.*;
import br.com.senac.projetoecommerce.useCases.clientes.implement.ClientesRepository;
import br.com.senac.projetoecommerce.useCases.enderecos.ValidacoesEndereco;
import br.com.senac.projetoecommerce.useCases.enderecos.domains.EnderecosRequestDom;
import br.com.senac.projetoecommerce.useCases.enderecos.domains.EnderecosResponseDom;
import br.com.senac.projetoecommerce.utils.FormaPagamento;
import br.com.senac.projetoecommerce.utils.SenacExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PedidoFinalizadoService {

    @Autowired
    private PedidoFinalizadoRepository pedidoFinalizadoRepository;
    @Autowired
    private ClientesRepository clientesRepository;

    public List<PedidoFinalizado> listarTodos() {
        return pedidoFinalizadoRepository.findAll();
    }

    public Optional<PedidoFinalizado> buscarPorId(Long id) {
        return pedidoFinalizadoRepository.findById(id);
    }

    //criar
    public PedidoFinalizadoResponseDom criarPedido(PedidoFinalizadoRequestDom pedido) throws SenacExceptions {
      /*  List<String> mensagens = ValidacoesEndereco.validarPedido(pedido, pedidoFinalizadoRepository);
        if (!mensagens.isEmpty()){
            throw new SenacExceptions(mensagens);
        }*/

        PedidoFinalizado pedidoEntidade = new PedidoFinalizado();
        //pedidoEntidade.setFormaPagamento(pedido.getFormaPagamento());
        Enderecos endereco = new Enderecos();
        endereco.setId(pedido.getEnderecoId());
        System.out.println(endereco);
        Clientes cliente = new Clientes();
        cliente.setId(pedido.getClienteId());
        System.out.println(cliente);

        pedidoEntidade.setEnderecos(endereco);
        pedidoEntidade.setClientes(cliente);

        PedidoFinalizado resultadoPedido = pedidoFinalizadoRepository.save(pedidoEntidade);

        List<Carrinho> carrinhoList = new ArrayList<>();

        for(PedidosFinalizadosCarrinhoDom carrinho : pedido.getCarrinho()){
            Carrinho c = new Carrinho();
            Produtos produto = new Produtos();
            produto.setId(carrinho.getProdutoId());
            c.setProdutos((Set<CarrinhoProduto>) produto);


        }

        PedidoFinalizadoResponseDom pedidoFinalizadoResponseDom = new PedidoFinalizadoResponseDom(); //retorno no postman
        pedidoFinalizadoResponseDom.setClientes(resultadoPedido.getClientes());
        pedidoFinalizadoResponseDom.setId(resultadoPedido.getId());
        pedidoFinalizadoResponseDom.setCarrinho(resultadoPedido.getCarrinho());
        pedidoFinalizadoResponseDom.setFormaPagamento(resultadoPedido.getFormaPagamento());
        pedidoFinalizadoResponseDom.setEnderecos(resultadoPedido.getEnderecos());



        return pedidoFinalizadoResponseDom;
    }

    public PedidoFinalizado atualizar(Long id, PedidoFinalizado pedidoAtualizado) {
        Optional<PedidoFinalizado> pedidoExistente = pedidoFinalizadoRepository.findById(id);
        if (pedidoExistente.isPresent()) {
            pedidoAtualizado.setId(id); // Certifica que o ID é o mesmo
            return pedidoFinalizadoRepository.save(pedidoAtualizado);
        } else {
            throw new RuntimeException("Pedido não encontrado");
        }
    }

    public boolean deletar(Long id) {
        Optional<PedidoFinalizado> pedido = pedidoFinalizadoRepository.findById(id);
        if (pedido.isPresent()) {
            pedidoFinalizadoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
