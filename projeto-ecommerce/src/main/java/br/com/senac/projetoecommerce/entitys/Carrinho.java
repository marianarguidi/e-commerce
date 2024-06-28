/*
package br.com.senac.projetoecommerce.entitys;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private Set<CarrinhoProduto> produtos = new HashSet<>();

    // Getters e Setters
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

    public Set<CarrinhoProduto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<CarrinhoProduto> produtos) {
        this.produtos = produtos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
*/

        package br.com.senac.projetoecommerce.entitys;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private Set<CarrinhoProduto> produtos = new HashSet<>();

    // Getters e Setters
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

    public Set<CarrinhoProduto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<CarrinhoProduto> produtos) {
        this.produtos = produtos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}