package br.com.senac.projetoecommerce.useCases.carrinho.domains;

import br.com.senac.projetoecommerce.entitys.Clientes;
import br.com.senac.projetoecommerce.entitys.Produtos;

import java.util.List;

public class CarrinhoResponseDom {

    private Long id;
    private List<Produtos> produtos;
    private Clientes clientes;
    private Double valorTotal;
    private int quantidadeProdutos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getQuantidadeProdutos() {
        return quantidadeProdutos;
    }

    public void setQuantidadeProdutos(int quantidadeProdutos) {
        this.quantidadeProdutos = quantidadeProdutos;
    }
}
