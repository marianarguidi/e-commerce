package br.com.senac.projetoecommerce.useCases.pedidoFinalizado;

import br.com.senac.projetoecommerce.entitys.*;
import br.com.senac.projetoecommerce.useCases.clientes.implement.ClientesRepository;
import br.com.senac.projetoecommerce.useCases.pedidoFinalizado.domains.PedidoFinalizadoRequestDom;
import br.com.senac.projetoecommerce.useCases.pedidoFinalizado.domains.PedidoFinalizadoResponseDom;
import br.com.senac.projetoecommerce.useCases.pedidoFinalizado.domains.PedidosFinalizadosCarrinhoDom;
import br.com.senac.projetoecommerce.useCases.pedidoFinalizado.implement.PedidoFinalizadoRepository;
import br.com.senac.projetoecommerce.utils.FormaPagamento;
import br.com.senac.projetoecommerce.utils.SenacExceptions;
import br.com.senac.projetoecommerce.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PedidoFinalizadoService {

    @Autowired
    private PedidoFinalizadoRepository pedidoFinalizadoRepository;

    @Autowired
    private ClientesRepository clientesRepository;

    public PedidoFinalizadoResponseDom criarPedido(Long clienteId, PedidoFinalizadoRequestDom pedidoRequest) throws SenacExceptions {
        // 1. Validação da Forma de Pagamento
        FormaPagamento formaPagamento;
        try {
            formaPagamento = FormaPagamento.valueOf(pedidoRequest.getFormaPagamento().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SenacExceptions("Forma de pagamento inválida: " + pedidoRequest.getFormaPagamento());
        }

        // 2. Busca e Validação do Cliente
        Clientes cliente = clientesRepository.findById(pedidoRequest.getClienteId())
                .orElseThrow(() -> new SenacExceptions("Cliente não encontrado com ID: " + pedidoRequest.getClienteId()));

        // 3. Construção do Endereço
        Enderecos endereco = new Enderecos();
        endereco.setId(pedidoRequest.getEnderecoId());

        // 4. Construção dos Itens do Carrinho
        Set<CarrinhoProduto> produtos = new HashSet<>();
        for (PedidosFinalizadosCarrinhoDom item : pedidoRequest.getCarrinho()) {
            CarrinhoProduto carrinhoProduto = new CarrinhoProduto();
            Produtos produto = new Produtos();
            produto.setId(item.getProdutoId());
            carrinhoProduto.setProduto(produto);
            carrinhoProduto.setQuantidade(item.getQuantidade());
            produtos.add(carrinhoProduto);
        }

        // Construção do Carrinho
        Carrinho carrinho = new Carrinho();
        carrinho.setClienteId(pedidoRequest.getClienteId());
        carrinho.setProdutos(produtos);
        carrinho.setQuantidadeProdutos(produtos.size());
        carrinho.setValorTotal(produtos.stream().mapToDouble(produto -> produto.getQuantidade() * 100.0).sum());

        // 5. Construção do Pedido
        PedidoFinalizado pedido = new PedidoFinalizado();
        pedido.setClientes(cliente);
        pedido.setEnderecos(endereco);
        pedido.setFormaPagamento(formaPagamento);
        pedido.setStatus(Status.PENDENTE);
        pedido.setCarrinho(carrinho);

        // 6. Salvar Pedido
        PedidoFinalizado resultadoPedido = pedidoFinalizadoRepository.save(pedido);

        // 7. Construção da Resposta
        PedidoFinalizadoResponseDom response = new PedidoFinalizadoResponseDom();
        response.setId(resultadoPedido.getId());
        response.setClienteId(clienteId);
        response.setEnderecoId(endereco.getId());
        response.setFormaPagamento(FormaPagamento.valueOf(resultadoPedido.getFormaPagamento().name()));
        response.setCarrinho(resultadoPedido.getCarrinho());

        return response;
    }

    // Método auxiliar para converter PedidoFinalizado em PedidoFinalizadoResponseDom
    private PedidoFinalizadoResponseDom converterParaResponseDom(PedidoFinalizado pedido) {
        PedidoFinalizadoResponseDom response = new PedidoFinalizadoResponseDom();
        response.setId(pedido.getId());
        response.setStatus(pedido.getStatus());
        response.setClienteId(pedido.getClientes().getId());
        response.setEnderecoId(pedido.getEnderecos().getId());
        response.setFormaPagamento(FormaPagamento.valueOf(pedido.getFormaPagamento().name()));
        response.setCarrinho(pedido.getCarrinho());
        return response;
    }

    public List<PedidoFinalizadoResponseDom> listarUltimosPedidosPorCliente(Long clienteId) throws SenacExceptions {
        // Verifica se o cliente existe
        if (!clientesRepository.existsById(clienteId)) {
            throw new SenacExceptions("Cliente não encontrado com ID: " + clienteId);
        }

        // Busca os pedidos do cliente e ordena pelos mais recentes
        List<PedidoFinalizado> pedidos = pedidoFinalizadoRepository.findByClientesIdOrderByIdDesc(clienteId);

        // Verifica se existem pedidos
        if (pedidos.isEmpty()) {
            throw new SenacExceptions("Nenhum pedido encontrado para o cliente com ID: " + clienteId);
        }

        // Converte cada pedido para PedidoFinalizadoResponseDom
        return pedidos.stream()
                .map(this::converterParaResponseDom)
                .collect(Collectors.toList());
    }
}
