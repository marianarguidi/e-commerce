package br.com.senac.projetoecommerce.useCases.produtos;
import br.com.senac.projetoecommerce.entitys.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produtos, Long> {

    @Query(value = "SELECT * FROM produtos p WHERE p.categoria = CAST(:categoria AS categorias_enum)", nativeQuery = true)
    List<Produtos> findByCategoria(@Param("categoria") String categoria);
}