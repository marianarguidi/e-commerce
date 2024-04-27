package br.com.senac.projetoecommerce.controllers;

import br.com.senac.projetoecommerce.useCases.Produtos.Categorias;
import br.com.senac.projetoecommerce.useCases.Produtos.ProdutoService;
import br.com.senac.projetoecommerce.useCases.Produtos.domains.ProdutosResponseDom;
import br.com.senac.projetoecommerce.useCases.clientes.ClientesService;
import br.com.senac.projetoecommerce.useCases.clientes.domains.ClienteResponseDom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
                // Se a categoria não existir, retornar um bad request
                return ResponseEntity.badRequest().body(null);
            } catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.badRequest().body(null);
            }
        }
    }
