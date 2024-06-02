package br.com.senac.projetoecommerce.useCases.produtos;

import br.com.senac.projetoecommerce.entitys.Produtos;
import br.com.senac.projetoecommerce.utils.Categorias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produtos> listarProdutosPorCategoria(Categorias categoria) {
        return produtoRepository.findByCategoria(String.valueOf(categoria));
    }

    public List<ProdutosResponseDom> carregarProdutosByCategoria(Categorias categoria) {
        List<Produtos> result = produtoRepository.findByCategoria(categoria);
        if(!result.isEmpty()) {
            return result.stream().map(ProdutosMappers::produtosParaProdutosResponseDom).toList();
        }

        return null;
    }

    public Produtos buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public ProdutosResponseDom carregarProdutoById(Long id){
        Optional<Produtos> result = produtoRepository.findById(id);
        if(result.isPresent()){
            return ProdutosMappers.produtosParaProdutosResponseDom(result.get());
        }

        return null;
    }

    public List<ProdutosResponseDom> carregarProdutos(String descricao){
        List<ProdutosResponseDom> out = new ArrayList<>();
        List<Produtos> result = descricao == null ? produtoRepository.findAll() : produtoRepository.findByNomeContainingIgnoreCase(descricao);

        if(!result.isEmpty()) {
            out = result.stream().map(ProdutosMappers::produtosParaProdutosResponseDom).toList();
        }

        return out;
    }
}
