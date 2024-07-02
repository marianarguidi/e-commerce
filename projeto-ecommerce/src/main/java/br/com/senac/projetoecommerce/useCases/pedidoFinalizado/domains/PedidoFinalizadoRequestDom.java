package br.com.senac.projetoecommerce.useCases.pedidoFinalizado.domains;

import br.com.senac.projetoecommerce.utils.Status;

import java.util.List;

public class PedidoFinalizadoRequestDom {
    private Status status;
    private String formaPagamento;
    private Long clienteId;
    private Long enderecoId;

    private List<PedidosFinalizadosCarrinhoDom> carrinho;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }

    public List<PedidosFinalizadosCarrinhoDom> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<PedidosFinalizadosCarrinhoDom> carrinho) {
        this.carrinho = carrinho;
    }
}
