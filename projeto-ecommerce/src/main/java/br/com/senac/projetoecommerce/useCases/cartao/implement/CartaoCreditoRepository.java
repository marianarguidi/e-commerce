package br.com.senac.projetoecommerce.useCases.cartao.implement;
import br.com.senac.projetoecommerce.entitys.Cartao;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CartaoCreditoRepository extends CrudRepository<Cartao, Long> {
    List<Cartao> findByTitular(String titular);
    List<Cartao> findByClienteId(Long clienteId);
}