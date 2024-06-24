package br.com.senac.projetoecommerce.useCases.pedidoFinalizado;

import br.com.senac.projetoecommerce.entitys.PedidoFinalizado;
import br.com.senac.projetoecommerce.utils.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoFinalizadoRepository extends JpaRepository<PedidoFinalizado, Long> {
    /**
     * Encontra pedidos finalizados por status.
     * @param status O status dos pedidos.
     * @return Uma lista de pedidos finalizados com o status especificado.
     */
    List<PedidoFinalizado> findByStatus(Status status);

    /**
     * Encontra pedidos finalizados por cliente.
     * @param clienteId O ID do cliente.
     * @return Uma lista de pedidos finalizados para o cliente especificado.
     */
    List<PedidoFinalizado> findByClientesId(Long clienteId);

    /**
     * Encontra pedidos finalizados por forma de pagamento.
     * @param formaPagamento A forma de pagamento dos pedidos.
     * @return Uma lista de pedidos finalizados com a forma de pagamento especificada.
     */
    List<PedidoFinalizado> findByFormaPagamento(String formaPagamento);

    /**
     * Encontra um pedido finalizado por ID e status.
     * @param id O ID do pedido.
     * @param status O status do pedido.
     * @return Um Optional contendo o pedido, se encontrado.
     */
    Optional<PedidoFinalizado> findByIdAndStatus(Long id, Status status);
}
