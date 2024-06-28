package br.com.senac.projetoecommerce.useCases.produtos;

import java.time.LocalDateTime;

public class ProdutosResponseDom {
    private Long id;
    private String nome;
<<<<<<< HEAD
    //private String codigoProduto;
=======
>>>>>>> 801d1b13f5c69474b4ca563be05cd89aa263ab66
    private String descricao;
    private String imagemPequena;
    private String imagemGrande;
    private Double preco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
<<<<<<< HEAD
/*
    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }*/

=======
>>>>>>> 801d1b13f5c69474b4ca563be05cd89aa263ab66
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

   public String getImagemPequena() {
        return imagemPequena;
    }

    public void setImagemPequena(String imagemPequena) {
        this.imagemPequena = imagemPequena;
    }

   public String getImagemGrande() {
        return imagemGrande;
    }

    public void setImagemGrande(String imagemGrande) {
        this.imagemGrande = imagemGrande;
    }

   public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}