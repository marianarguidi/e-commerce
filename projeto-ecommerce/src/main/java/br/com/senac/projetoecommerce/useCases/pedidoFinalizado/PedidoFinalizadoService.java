package br.com.senac.projetoecommerce.useCases.pedidoFinalizado;

import br.com.senac.projetoecommerce.entitys.PedidoFinalizado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoFinalizadoService {

    @Autowired
    private PedidoFinalizadoRepository pedidoFinalizadoRepository;

    public List<PedidoFinalizado> listarTodos() {
        return pedidoFinalizadoRepository.findAll();
    }

    public Optional<PedidoFinalizado> buscarPorId(Long id) {
        return pedidoFinalizadoRepository.findById(id);
    }

    public PedidoFinalizado criar(PedidoFinalizado pedido) {
        return pedidoFinalizadoRepository.save(pedido);
    }
}
