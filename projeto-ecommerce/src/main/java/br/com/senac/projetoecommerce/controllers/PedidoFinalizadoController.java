package br.com.senac.projetoecommerce.controllers;

import br.com.senac.projetoecommerce.entitys.PedidoFinalizado;
import br.com.senac.projetoecommerce.useCases.enderecos.domains.EnderecosRequestDom;
import br.com.senac.projetoecommerce.useCases.enderecos.domains.EnderecosResponseDom;
import br.com.senac.projetoecommerce.useCases.pedidoFinalizado.PedidoFinalizadoRequestDom;
import br.com.senac.projetoecommerce.useCases.pedidoFinalizado.PedidoFinalizadoResponseDom;
import br.com.senac.projetoecommerce.useCases.pedidoFinalizado.PedidoFinalizadoService;
import br.com.senac.projetoecommerce.utils.ResponseUtil;
import br.com.senac.projetoecommerce.utils.SenacExceptions;
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
  /*  @PostMapping("/criar/{id}")
    public ResponseEntity<PedidoFinalizado> criar(@RequestBody PedidoFinalizado pedido) {
        PedidoFinalizado novoPedido = pedidoFinalizadoService.criarPedido(pedido);
        return ResponseEntity.ok(novoPedido);
    }*/

    @PostMapping("/criarPedido")
    public ResponseEntity<?> criarPedido(@RequestBody PedidoFinalizadoRequestDom pedido) throws SenacExceptions {
        try {
            PedidoFinalizadoResponseDom pedidoFinalizadoResponseDom = pedidoFinalizadoService.criarPedido(pedido);
            return ResponseEntity.status(201).body(pedidoFinalizadoResponseDom);
        }catch (SenacExceptions es){
            es.printStackTrace();
            return ResponseEntity.badRequest().body(ResponseUtil.responseMap(es.getMessages()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(ResponseUtil.responseMap("Erro não mapeado: " + e.getMessage()));
        }
    }

}
