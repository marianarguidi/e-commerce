/*
package br.com.senac.projetoecommerce.entitys;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class CarrinhoProduto implements Serializable {
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produtos produto;
    private int quantidade;

    // Getters e Setters
    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
*/
package br.com.senac.projetoecommerce.entitys;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class CarrinhoProduto implements Serializable {
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produtos produto;
    private int quantidade;


    // Getters e Setters
    public Produtos getProduto() {
        return produto;
    }

    public void setProduto(Produtos produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }}