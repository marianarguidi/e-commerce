package br.com.senac.projetoecommerce.entitys;

import br.com.senac.projetoecommerce.utils.Categorias;
import jakarta.persistence.*;

@Entity
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Double valor;
    private Integer quantidade;
    private String detalhes;

    @Enumerated(EnumType.STRING)
    private Categorias categoria;

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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }
}
