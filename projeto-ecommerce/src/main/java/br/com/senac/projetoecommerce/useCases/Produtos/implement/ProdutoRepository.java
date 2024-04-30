package br.com.senac.projetoecommerce.useCases.Produtos.implement;

import br.com.senac.projetoecommerce.entitys.Produtos;
import br.com.senac.projetoecommerce.useCases.Produtos.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produtos, Long> {

    List<Produtos> findByCategoria(Categorias categoria);

    List<Produtos> findByCarrinhoId(Long carrinhoId);
}
