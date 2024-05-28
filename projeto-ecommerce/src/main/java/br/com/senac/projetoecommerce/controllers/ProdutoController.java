package br.com.senac.projetoecommerce.controllers;

import br.com.senac.projetoecommerce.entitys.Produtos;
import br.com.senac.projetoecommerce.useCases.produtos.ProdutoService;
import br.com.senac.projetoecommerce.utils.Categorias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/categoria/{categoria}")
    public List<Produtos> listarProdutosPorCategoria(@PathVariable Categorias categoria) {
        return produtoService.listarProdutosPorCategoria(categoria);
    }

    @GetMapping("/{id}")
    public Produtos buscarProdutoPorId(@PathVariable Long id) {
        return produtoService.buscarProdutoPorId(id);
    }
}
