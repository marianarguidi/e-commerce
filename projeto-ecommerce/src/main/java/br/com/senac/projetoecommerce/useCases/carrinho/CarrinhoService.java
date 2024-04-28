package br.com.senac.projetoecommerce.useCases.carrinho;

import br.com.senac.projetoecommerce.entitys.Carrinho;
import br.com.senac.projetoecommerce.entitys.Enderecos;
import br.com.senac.projetoecommerce.entitys.Produtos;
import br.com.senac.projetoecommerce.useCases.Produtos.domains.ProdutosResponseDom;
import br.com.senac.projetoecommerce.useCases.Produtos.implement.ProdutoRepository;
import br.com.senac.projetoecommerce.useCases.enderecos.domains.EnderecosResponseDom;
import br.com.senac.projetoecommerce.useCases.enderecos.implement.EnderecosRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    //carregar os produtos do carrinho
    public List<ProdutosResponseDom> carregarProdutosCarrinho(Long carrinho_id) {
        List<Produtos> resultado = produtoRepository.findByCarrinhoId(carrinho_id);
        List<ProdutosResponseDom> produtosResponseDomList = new ArrayList<>();

        for (Produtos dado : resultado) {
            ProdutosResponseDom produtosResponseDom = new ProdutosResponseDom();
            produtosResponseDom.setId(dado.getId());
            produtosResponseDom.setDescricao(dado.getDescricao());
            produtosResponseDom.setDetalhes(dado.getDetalhes());
            produtosResponseDom.setValor(dado.getValor());
            produtosResponseDom.setQuantidade(dado.getQuantidade());
            produtosResponseDom.setCategoria(dado.getCategoria());

            produtosResponseDomList.add(produtosResponseDom);
        }
        return produtosResponseDomList;
    }

    //adiciona um produto ao carrinho
    public void adicionarProdutoCarrinho(Produtos produto, Carrinho carrinho) {
        boolean produtoJaNoCarrinho = carrinho.getProdutos().contains(produto);
        if (produtoJaNoCarrinho) {
            for (Produtos p : carrinho.getProdutos()) {
                if (p.equals(produto)) {
                    p.setQuantidade(p.getQuantidade() + 1);
                    break;
                }
            }
        } else {
            produto.setQuantidade(1);
            carrinho.getProdutos().add(produto);
        }
    }

    //remove um produto do carrinho
        public void removerProdutoCarrinho(Produtos produto, Carrinho carrinho) {
            if (carrinho.getProdutos().contains(produto)) {
                carrinho.getProdutos().remove(produto);
            }
        }
}
