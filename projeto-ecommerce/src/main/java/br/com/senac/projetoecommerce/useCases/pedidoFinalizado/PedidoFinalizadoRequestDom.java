package br.com.senac.projetoecommerce.useCases.pedidoFinalizado;

import br.com.senac.projetoecommerce.entitys.Carrinho;
import br.com.senac.projetoecommerce.entitys.Clientes;
import br.com.senac.projetoecommerce.entitys.Enderecos;
import br.com.senac.projetoecommerce.utils.FormaPagamento;
import br.com.senac.projetoecommerce.utils.Status;
import jakarta.persistence.*;

import java.util.List;

public class PedidoFinalizadoRequestDom {
    private Long id;
    private Status status;
    private String formaPagamento;
    private Long clienteId;
    private Long enderecoId;

    private List<PedidosFinalizadosCarrinhoDom> carrinho;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
