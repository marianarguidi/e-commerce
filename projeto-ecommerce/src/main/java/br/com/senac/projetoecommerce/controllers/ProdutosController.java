package br.com.senac.projetoecommerce.controllers;

import br.com.senac.projetoecommerce.entitys.Carrinho;
import br.com.senac.projetoecommerce.entitys.Produtos;
import br.com.senac.projetoecommerce.useCases.Produtos.Categorias;
import br.com.senac.projetoecommerce.useCases.Produtos.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/listaProdutos") // Mapeie o prefixo do caminho para todos os endpoints neste controlador
public class ProdutosController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/carregar/{categoria}")
    public ResponseEntity<List<Produtos>> carregarProdutos(@PathVariable String categoria) {
        try {
            // Convertendo a string da categoria para enumeração
            Categorias categoriaEnum = Categorias.valueOf(categoria.toUpperCase());

            // Carregar os produtos por categoria usando o serviço
            List<Produtos> produtos = produtoService.carregarProdutosPorCategoria(categoriaEnum);

            // Verifica se a lista de produtos não está vazia
            if (!produtos.isEmpty()) {
                return ResponseEntity.ok(produtos);
            } else {
                // Retorna um erro 404 caso não existam produtos para a categoria especificada
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            // Retorna um erro 400 caso a categoria fornecida não exista
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            // Retorna um erro 500 caso ocorra algum erro interno no servidor
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/adicionarAoCarrinho/{produtoId}")
    public ResponseEntity<String> adicionarAoCarrinho(@PathVariable Long produtoId, @RequestBody Carrinho carrinho) {
        try {
            // Verifica se o carrinho já existe ou cria um novo carrinho
            if (carrinho == null) {
                carrinho = new Carrinho();
                carrinho.setValorTotal(0.0); // Define o valor total inicial como 0
                carrinho.setQuantidadeProdutos(0); // Define a quantidade de produtos inicial como 0
            }

            // Obtém o produto pelo ID
            Produtos produto = produtoService.obterProdutoPorId(produtoId);

            // Adiciona o produto ao carrinho
            produto.setCarrinho(carrinho);
            carrinho.getProdutos().add(produto);

            // Atualiza o valor total e a quantidade de produtos do carrinho
            double valorTotalCarrinho = carrinho.getValorTotal() + produto.getValor();
            int quantidadeProdutosCarrinho = carrinho.getQuantidadeProdutos() + 1;
            carrinho.setValorTotal(valorTotalCarrinho);
            carrinho.setQuantidadeProdutos(quantidadeProdutosCarrinho);

            // Atualiza o produto no serviço
            produtoService.atualizarProduto(produto);

            return ResponseEntity.ok("Produto adicionado ao carrinho com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao adicionar produto ao carrinho.");
        }
    }

}
