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

    public PedidoFinalizado atualizar(Long id, PedidoFinalizado pedidoAtualizado) {
        Optional<PedidoFinalizado> pedidoExistente = pedidoFinalizadoRepository.findById(id);
        if (pedidoExistente.isPresent()) {
            pedidoAtualizado.setId(id); // Certifica que o ID é o mesmo
            return pedidoFinalizadoRepository.save(pedidoAtualizado);
        } else {
            throw new RuntimeException("Pedido não encontrado");
        }
    }

    public boolean deletar(Long id) {
        Optional<PedidoFinalizado> pedido = pedidoFinalizadoRepository.findById(id);
        if (pedido.isPresent()) {
            pedidoFinalizadoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
