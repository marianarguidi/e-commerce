package br.com.senac.projetoecommerce.entitys;
import br.com.senac.projetoecommerce.utils.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class PedidoFinalizado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonIgnoreProperties({"pedidos"})  // Ignora a serialização da lista de pedidos do cliente
    private Clientes clientes;

    @ManyToOne
    @JoinColumn(name = "endereco_id", nullable = false)
    @JsonIgnoreProperties({"clientes"})  // Ignora a serialização do cliente do endereço
    private Enderecos enderecos;

    /*@OneToOne
    @JoinColumn(name = "carrinho_id", nullable = false)
    private Carrinho carrinho;*/

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carrinho_id")
    @JsonIgnore
    private Carrinho carrinho;


    public void setId(Long id) {

    }

    public Long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public Enderecos getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Enderecos enderecos) {
        this.enderecos = enderecos;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }
}
