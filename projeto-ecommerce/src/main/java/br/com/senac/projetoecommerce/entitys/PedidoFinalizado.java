package br.com.senac.projetoecommerce.entitys;
import br.com.senac.projetoecommerce.utils.*;
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

    @OneToOne
    @JoinColumn(name = "clientes_id", nullable = false)
    private Clientes clientes;

    @OneToOne
    @JoinColumn(name = "enderecos_id", nullable = false)
    private Enderecos enderecos;

    @OneToOne
    @JoinColumn(name = "carrinho_id", nullable = false)
    private Carrinho carrinho;


    public void setId(Long id) {
    }
}
