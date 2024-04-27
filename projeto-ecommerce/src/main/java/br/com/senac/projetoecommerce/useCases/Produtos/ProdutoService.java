package br.com.senac.projetoecommerce.useCases.Produtos;

import br.com.senac.projetoecommerce.entitys.Carrinho;
import br.com.senac.projetoecommerce.entitys.Enderecos;
import br.com.senac.projetoecommerce.entitys.Produtos;
import br.com.senac.projetoecommerce.useCases.Produtos.domains.ProdutosResponseDom;
import br.com.senac.projetoecommerce.useCases.Produtos.implement.ProdutoRepository;
import br.com.senac.projetoecommerce.useCases.enderecos.domains.EnderecosResponseDom;
import br.com.senac.projetoecommerce.useCases.enderecos.implement.EnderecosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    //carregar os produtos de apenas uma categoria
    public List<ProdutosResponseDom> carregarProdutosCategoria(Categorias categorias) {
        List<Produtos> resultado = produtoRepository.findByProdutoCategoria(categorias);
        List<ProdutosResponseDom> produtosResponseDomList = new ArrayList<>();

        for (Produtos dado : resultado) {
            ProdutosResponseDom produtosResponseDom = new ProdutosResponseDom();
            produtosResponseDom.setId(dado.getId());
            produtosResponseDom.setValor(dado.getValor());
            produtosResponseDom.setDescricao(dado.getDescricao());
            produtosResponseDom.setDetalhes(dado.getDetalhes());
            produtosResponseDom.setQuantidade(dado.getQuantidade());

            produtosResponseDomList.add(produtosResponseDom);
        }
        return produtosResponseDomList;
    }

    //atualiza estoque do produto
    public void atualizarProduto(Produtos produto, Carrinho carrinho) {
        if ((carrinho.getQuantidadeProdutos() < 5) && (produto.getQuantidade() > 0)) {
            produto.setQuantidade(produto.getQuantidade() - 1);
            carrinho.setProdutos(carrinho.getProdutos());
            carrinho.setQuantidadeProdutos(carrinho.getQuantidadeProdutos() + 1);
            produtoRepository.save(produto);
        } else {
            throw new RuntimeException("Que pena! Este produto está indisponível no momento.");
        }
    }
}
