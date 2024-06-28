package br.com.senac.projetoecommerce.controllers;

import br.com.senac.projetoecommerce.entitys.Carrinho;
import br.com.senac.projetoecommerce.useCases.carrinho.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/carrinhos")
public class CarrinhoController {
    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping("/adicionar/{produtoId}/{quantidade}")
    public Carrinho adicionarProdutoAoCarrinho(@RequestParam(required = false) Long carrinhoId, @RequestParam Long clienteId, @PathVariable Long produtoId, @PathVariable Integer quantidade) {
        return carrinhoService.adicionarProdutoAoCarrinho(carrinhoId, produtoId, quantidade, clienteId);
    }

    @PostMapping("/{carrinhoId}/remover/{produtoId}/{quantidade}")
    public Carrinho removerProdutoDoCarrinho(@PathVariable Long carrinhoId, @PathVariable Long produtoId, @PathVariable Integer quantidade) {
        return carrinhoService.removerProdutoDoCarrinho(carrinhoId, produtoId, quantidade);
    }

    @GetMapping("/{carrinhoId}/total")
    public double calcularValorTotal(@PathVariable Long carrinhoId) {
        return carrinhoService.calcularValorTotal(carrinhoId);
    }
}
