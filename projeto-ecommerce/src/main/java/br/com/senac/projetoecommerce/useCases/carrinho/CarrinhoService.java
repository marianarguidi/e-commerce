package br.com.senac.projetoecommerce.useCases.carrinho;

import br.com.senac.projetoecommerce.entitys.Carrinho;
import br.com.senac.projetoecommerce.entitys.CarrinhoProduto;
import br.com.senac.projetoecommerce.entitys.Produtos;
import br.com.senac.projetoecommerce.useCases.produtos.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    private static final Logger LOGGER = Logger.getLogger(CarrinhoService.class.getName());

    public Carrinho adicionarProdutoAoCarrinho(Long carrinhoId, Long produtoId, Integer quantidade, Long clienteId) {
        LOGGER.info("Iniciando adicionarProdutoAoCarrinho");
        LOGGER.info("carrinhoId: " + carrinhoId + ", produtoId: " + produtoId + ", quantidade: " + quantidade + ", clienteId: " + clienteId);

        if (clienteId == null) {
            throw new RuntimeException("Cliente ID não pode ser nulo");
        }

        Carrinho carrinho;
        if (carrinhoId == null || carrinhoId <= 0) {
            carrinho = new Carrinho();
            carrinho.setClienteId(clienteId);
            LOGGER.info("Novo carrinho criado com clienteId: " + clienteId);
        } else {
            carrinho = carrinhoRepository.findById(carrinhoId).orElse(new Carrinho());
            LOGGER.info("Carrinho existente encontrado ou novo carrinho criado");
            if (carrinho.getClienteId() == null) {
                carrinho.setClienteId(clienteId);
            }
        }

        Produtos produto = produtoRepository.findById(produtoId).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (quantidade > produto.getQuantidade()) {
            throw new RuntimeException("Quantidade solicitada maior que a disponível no estoque");
        }

        Optional<CarrinhoProduto> carrinhoProdutoOpt = carrinho.getProdutos().stream()
                .filter(cp -> cp.getProduto().equals(produto))
                .findFirst();

        CarrinhoProduto carrinhoProduto;
        if (carrinhoProdutoOpt.isPresent()) {
            carrinhoProduto = carrinhoProdutoOpt.get();
            if (carrinhoProduto.getQuantidade() + quantidade > 4) {
                throw new RuntimeException("Não é possível adicionar mais de 4 itens iguais no carrinho");
            }
            carrinhoProduto.setQuantidade(carrinhoProduto.getQuantidade() + quantidade);
        } else {
            carrinhoProduto = new CarrinhoProduto();
            carrinhoProduto.setProduto(produto);
            carrinhoProduto.setQuantidade(quantidade);
            carrinho.getProdutos().add(carrinhoProduto);
        }

        produto.setQuantidade(produto.getQuantidade() - quantidade);
        carrinho.setQuantidadeProdutos(carrinho.getQuantidadeProdutos() + quantidade);
        carrinho.setValorTotal(carrinho.getValorTotal() + produto.getValor() * quantidade);

        produtoRepository.save(produto);
        Carrinho savedCarrinho = carrinhoRepository.save(carrinho);
        LOGGER.info("Produto adicionado ao carrinho com sucesso. Carrinho ID: " + savedCarrinho.getId());

        return savedCarrinho;
    }

    public Carrinho removerProdutoDoCarrinho(Long carrinhoId, Long produtoId, Integer quantidade) {
        Carrinho carrinho = carrinhoRepository.findById(carrinhoId).orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
        Produtos produto = produtoRepository.findById(produtoId).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Optional<CarrinhoProduto> carrinhoProdutoOpt = carrinho.getProdutos().stream()
                .filter(cp -> cp.getProduto().equals(produto))
                .findFirst();

        if (!carrinhoProdutoOpt.isPresent() || quantidade > carrinhoProdutoOpt.get().getQuantidade()) {
            throw new RuntimeException("Quantidade a remover maior que a quantidade no carrinho");
        }

        CarrinhoProduto carrinhoProduto = carrinhoProdutoOpt.get();
        carrinhoProduto.setQuantidade(carrinhoProduto.getQuantidade() - quantidade);

        if (carrinhoProduto.getQuantidade() == 0) {
            carrinho.getProdutos().remove(carrinhoProduto);
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
