package br.com.senac.projetoecommerce.entitys;

import br.com.senac.projetoecommerce.useCases.pagamento.FormaDePagamento;
import jakarta.persistence.*;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormaDePagamento formaDePagamento;

    @Column(nullable = false)
    private Double valorTotal;
}
