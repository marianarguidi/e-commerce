package br.com.senac.projetoecommerce.useCases.pedidoFinalizado.implement;

import br.com.senac.projetoecommerce.entitys.PedidoFinalizado;
import br.com.senac.projetoecommerce.utils.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoFinalizadoRepository extends JpaRepository<PedidoFinalizado, Long> {
    List<PedidoFinalizado> findByClientesIdOrderByIdDesc(Long clienteId);
}
