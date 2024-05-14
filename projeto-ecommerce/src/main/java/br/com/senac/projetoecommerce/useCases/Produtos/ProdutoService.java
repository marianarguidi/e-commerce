package br.com.senac.projetoecommerce.useCases.Produtos;

import br.com.senac.projetoecommerce.entitys.Carrinho;
import br.com.senac.projetoecommerce.entitys.Produtos;
import br.com.senac.projetoecommerce.useCases.Produtos.implement.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produtos> carregarProdutosPorCategoria(Categorias categoria) {
        return produtoRepository.findByCategoria(categoria);
    }

    //atualiza estoque do produto e adiciona ao carrinho
    public void atualizarProduto(Produtos produto) {
        Carrinho carrinho = null;
        if ((carrinho.getQuantidadeProdutos() < 5) && (produto.getQuantidade() > 0)) {
            produto.setQuantidade(produto.getQuantidade() - 1);
            carrinho.setProdutos(carrinho.getProdutos());
            carrinho.setQuantidadeProdutos(carrinho.getQuantidadeProdutos() + 1);
            produtoRepository.save(produto);
        } else {
            throw new RuntimeException("Que pena! Este produto está indisponível no momento.");
        }
    }


    public Produtos obterProdutoPorId(Long produtoId) {
        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + produtoId));
    }
}
