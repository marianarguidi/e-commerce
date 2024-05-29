package br.com.senac.projetoecommerce.useCases.carrinho;
import br.com.senac.projetoecommerce.entitys.Carrinho;
import br.com.senac.projetoecommerce.entitys.Produtos;
import br.com.senac.projetoecommerce.useCases.produtos.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public Carrinho adicionarProdutoAoCarrinho(Long carrinhoId, Long produtoId, int quantidade) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId).orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
        Produtos produto = produtoRepository.findById(produtoId).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (quantidade > produto.getQuantidade()) {
            throw new RuntimeException("Quantidade solicitada maior que a disponível no estoque");
        }

        if (carrinho.getProdutos().containsKey(produto) && carrinho.getProdutos().get(produto) + quantidade > 4) {
            throw new RuntimeException("Não é possível adicionar mais de 4 itens iguais no carrinho");
        }

        carrinho.getProdutos().put(produto, carrinho.getProdutos().getOrDefault(produto, 0) + quantidade);
        produto.setQuantidade(produto.getQuantidade() - quantidade);
        carrinho.setQuantidadeProdutos(carrinho.getQuantidadeProdutos() + quantidade);
        carrinho.setValorTotal(carrinho.getValorTotal() + produto.getValor() * quantidade);

        produtoRepository.save(produto);
        return carrinhoRepository.save(carrinho);
    }

    public Carrinho removerProdutoDoCarrinho(Long carrinhoId, Long produtoId, int quantidade) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId).orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
        Produtos produto = produtoRepository.findById(produtoId).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (!carrinho.getProdutos().containsKey(produto) || quantidade > carrinho.getProdutos().get(produto)) {
            throw new RuntimeException("Quantidade a remover maior que a quantidade no carrinho");
        }

        carrinho.getProdutos().put(produto, carrinho.getProdutos().get(produto) - quantidade);
        if (carrinho.getProdutos().get(produto) == 0) {
            carrinho.getProdutos().remove(produto);
        }

        produto.setQuantidade(produto.getQuantidade() + quantidade);
        carrinho.setQuantidadeProdutos(carrinho.getQuantidadeProdutos() - quantidade);
        carrinho.setValorTotal(carrinho.getValorTotal() - produto.getValor() * quantidade);

        produtoRepository.save(produto);
        return carrinhoRepository.save(carrinho);
    }

    public double calcularValorTotal(Long carrinhoId) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId).orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
        return carrinho.getValorTotal();
    }
}
