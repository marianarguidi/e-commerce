package br.com.senac.projetoecommerce.useCases.carrinho;

import br.com.senac.projetoecommerce.entitys.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
}
