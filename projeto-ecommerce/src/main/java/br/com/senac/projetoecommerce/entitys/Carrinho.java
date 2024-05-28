package br.com.senac.projetoecommerce.entitys;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clienteId;
    private int quantidadeProdutos;
    private double valorTotal;

    @ElementCollection
    @CollectionTable(name = "carrinho_produtos", joinColumns = @JoinColumn(name = "carrinho_id"))
    @MapKeyJoinColumn(name = "produto_id")
    @Column(name = "quantidade")
    private Map<Produtos, Integer> produtos = new HashMap<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public int getQuantidadeProdutos() {
        return quantidadeProdutos;
    }

    public void setQuantidadeProdutos(int quantidadeProdutos) {
        this.quantidadeProdutos = quantidadeProdutos;
    }

    public Map<Produtos, Integer> getProdutos() {
        return produtos;
    }

    public void setProdutos(Map<Produtos, Integer> produtos) {
        this.produtos = produtos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
