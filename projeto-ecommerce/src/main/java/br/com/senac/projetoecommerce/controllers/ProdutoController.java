package br.com.senac.projetoecommerce.controllers;

import br.com.senac.projetoecommerce.entitys.Produtos;
import br.com.senac.projetoecommerce.useCases.produtos.ProdutoService;
import br.com.senac.projetoecommerce.useCases.produtos.ProdutosResponseDom;
import br.com.senac.projetoecommerce.utils.Categorias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<?> carregarProdutosByCategoria(@PathVariable Categorias categoria) {
        try {
            List<ProdutosResponseDom> out = produtoService.carregarProdutosByCategoria(categoria);

            if (out != null) {
                return ResponseEntity.ok(out);
            }

            return ResponseEntity.noContent().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro não mapeado: " + e.getMessage());
        }
    }

    @GetMapping("busca/{id}")
    public Produtos buscarProdutoPorId(@PathVariable Long id) {
        return produtoService.buscarProdutoPorId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> carregarProdutoById(@PathVariable Long id) {
        try {
            ProdutosResponseDom out = produtoService.carregarProdutoById(id);

            if (out != null) {
                return ResponseEntity.ok(out);
            }

            return ResponseEntity.noContent().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro não mapeado: " + e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> carregarProdutos(@RequestParam(required = false) String descricao) {
        try {
            List<ProdutosResponseDom> out = produtoService.carregarProdutos(descricao);

            if (!out.isEmpty()) {
                return ResponseEntity.ok(out);
            }

            return ResponseEntity.noContent().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro não mapeado: " + e.getMessage());
        }
    }
}
