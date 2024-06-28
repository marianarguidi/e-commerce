package br.com.senac.projetoecommerce.entitys;

import br.com.senac.projetoecommerce.useCases.produtos.ProdutosPrecos;
import br.com.senac.projetoecommerce.utils.Categorias;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Double preco = 9.90;
    private int quantidade;
    private String imagemPequena;
    private String imagemGrande;
<<<<<<< HEAD
    //private String codigoProduto;
=======
>>>>>>> 801d1b13f5c69474b4ca563be05cd89aa263ab66

    @Enumerated(EnumType.STRING)
    private Categorias categoria;

    @OneToMany(mappedBy = "produto")
    private List<ProdutosPrecos> precos;

<<<<<<< HEAD
    //private String detalhes;

=======
>>>>>>> 801d1b13f5c69474b4ca563be05cd89aa263ab66
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double valor) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

<<<<<<< HEAD
   /* public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }*/

=======
>>>>>>> 801d1b13f5c69474b4ca563be05cd89aa263ab66
    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ProdutosPrecos> getPrecos() {
        return precos;
    }

    public void setPrecos(List<ProdutosPrecos> precos) {
        this.precos = precos;
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

<<<<<<< HEAD
    public Optional<Produtos> findById(Long id) {
        return findById(id);
    }

    /*public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }*/
=======
>>>>>>> 801d1b13f5c69474b4ca563be05cd89aa263ab66
}



