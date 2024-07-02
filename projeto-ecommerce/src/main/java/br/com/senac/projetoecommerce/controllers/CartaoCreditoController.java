package br.com.senac.projetoecommerce.controllers;

import br.com.senac.projetoecommerce.entitys.Cartao;
import br.com.senac.projetoecommerce.useCases.cartao.domains.CartaoCreditoRequestDom;
import br.com.senac.projetoecommerce.useCases.cartao.domains.CartaoCreditoResponseDom;
import br.com.senac.projetoecommerce.useCases.cartao.implement.CartaoCreditoRepository;
import br.com.senac.projetoecommerce.useCases.cartao.implement.CartaoCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class CartaoCreditoController {
    @Autowired
    private CartaoCreditoRepository cartaoCreditoRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<CartaoCreditoResponseDom> cadastrarCartao(@RequestBody CartaoCreditoRequestDom requestDom) {

        Cartao cartao = new Cartao();
        cartao.setNumero(requestDom.getNumero());
        cartao.setTitular(requestDom.getTitular());
        cartao.setDataValidade(requestDom.getDataValidade());
        cartao.setCvv(requestDom.getCvv());
        cartao.setClienteId(requestDom.getClienteId());

        Cartao cartaoSalvo = cartaoCreditoRepository.save(cartao);

        CartaoCreditoResponseDom responseDom = new CartaoCreditoResponseDom();
        responseDom.setId(cartaoSalvo.getId());
        responseDom.setNumero(cartaoSalvo.getNumero());
        responseDom.setTitular(cartaoSalvo.getTitular());
        responseDom.setDataValidade(cartaoSalvo.getDataValidade());
        // Não inclua o CVV na resposta

        return ResponseEntity.ok(responseDom);
    }


    @GetMapping("/carregar/{id}")
    public ResponseEntity<CartaoCreditoResponseDom> carregarCartao(@PathVariable Long id) {
        Optional<Cartao> cartao = cartaoCreditoRepository.findById(id);
        if (cartao.isPresent()) {
            CartaoCreditoResponseDom responseDom = new CartaoCreditoResponseDom();
            responseDom.setId(cartao.get().getId());
            responseDom.setNumero(cartao.get().getNumero());
            responseDom.setTitular(cartao.get().getTitular());
            responseDom.setDataValidade(cartao.get().getDataValidade());


            return ResponseEntity.ok(responseDom);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<CartaoCreditoResponseDom> editarCartao(@PathVariable Long id, @RequestBody CartaoCreditoRequestDom requestDom) {
        Optional<Cartao> cartaoExistente = cartaoCreditoRepository.findById(id);
        if (cartaoExistente.isPresent()) {
            Cartao cartao = cartaoExistente.get();
            cartao.setNumero(requestDom.getNumero());
            cartao.setTitular(requestDom.getTitular());
            cartao.setDataValidade(requestDom.getDataValidade());
            cartao.setCvv(requestDom.getCvv());
            // Não esqueça de atualizar o clienteId se necessário

            Cartao cartaoAtualizado = cartaoCreditoRepository.save(cartao);

            CartaoCreditoResponseDom responseDom = new CartaoCreditoResponseDom();
            responseDom.setId(cartaoAtualizado.getId());
            responseDom.setNumero(cartaoAtualizado.getNumero());
            responseDom.setTitular(cartaoAtualizado.getTitular());
            responseDom.setDataValidade(cartaoAtualizado.getDataValidade());
            // Não inclua o CVV na resposta

            return ResponseEntity.ok(responseDom);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarCartao(@PathVariable Long id) {
        Optional<Cartao> cartao = cartaoCreditoRepository.findById(id);
        if (cartao.isPresent()) {
            cartaoCreditoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}