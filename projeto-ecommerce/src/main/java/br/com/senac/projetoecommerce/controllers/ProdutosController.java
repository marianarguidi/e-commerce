package br.com.senac.projetoecommerce.controllers;

import br.com.senac.projetoecommerce.entitys.Carrinho;
import br.com.senac.projetoecommerce.entitys.Produtos;
import br.com.senac.projetoecommerce.useCases.Produtos.Categorias;
import br.com.senac.projetoecommerce.useCases.Produtos.ProdutoService;
import br.com.senac.projetoecommerce.useCases.Produtos.domains.ProdutosResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
    @Controller
    @RequestMapping("/listaProdutos")
    public class ProdutosController {
        @Autowired
        private ProdutoService produtosService;

        //carregar todos os produtos por categoria
        @GetMapping("/carregar/{categoria}")
        public ResponseEntity<ProdutosResponseDom> carregarProdutos(@PathVariable Categorias categoria){
            try {
                ProdutosResponseDom responseDom = (ProdutosResponseDom) produtosService.carregarProdutosCategoria(categoria);
                if (responseDom != null){
                    return ResponseEntity.ok(responseDom);
                }
                return ResponseEntity.badRequest().body(null);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(null);
            } catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.badRequest().body(null);
            }
        }

        //adicionar produto ao carrinho
        @PostMapping("/adicionarAoCarrinho/{produtoId}")
        public ResponseEntity<String> adicionarAoCarrinho(@PathVariable Long produtoId, @RequestBody Carrinho carrinho) {
            try {
                ProdutoService produtoService = null;
                Produtos produto = produtoService.obterProdutoPorId(produtoId);
                produtoService.atualizarProduto(produto, carrinho);
                return ResponseEntity.ok("Produto adicionado ao carrinho com sucesso.");
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body("Erro ao adicionar produto ao carrinho.");
            }
        }
    }
