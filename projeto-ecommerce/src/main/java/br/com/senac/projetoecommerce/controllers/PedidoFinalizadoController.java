package br.com.senac.projetoecommerce.controllers;

import br.com.senac.projetoecommerce.entitys.PedidoFinalizado;
import br.com.senac.projetoecommerce.useCases.pedidoFinalizado.PedidoFinalizadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoFinalizadoController {

    @Autowired
    private PedidoFinalizadoService pedidoFinalizadoService;

    // Listar todos os pedidos finalizados
    @GetMapping
    public ResponseEntity<List<PedidoFinalizado>> listarTodos() {
        List<PedidoFinalizado> pedidos = pedidoFinalizadoService.listarTodos();
        return ResponseEntity.ok(pedidos);
    }

    // Buscar pedido finalizado por ID
    @GetMapping("/{id}")
    public ResponseEntity<PedidoFinalizado> buscarPorId(@PathVariable Long id) {
        Optional<PedidoFinalizado> pedido = pedidoFinalizadoService.buscarPorId(id);
        if (pedido.isPresent()) {
            return ResponseEntity.ok(pedido.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Criar um novo pedido finalizado
    @PostMapping
    public ResponseEntity<PedidoFinalizado> criar(@RequestBody PedidoFinalizado pedido) {
        PedidoFinalizado novoPedido = pedidoFinalizadoService.criar(pedido);
        return ResponseEntity.ok(novoPedido);
    }
}
