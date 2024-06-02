package br.com.senac.projetoecommerce.useCases.produtos;
import br.com.senac.projetoecommerce.entitys.Produtos;

public class ProdutosMappers {
    public static ProdutosResponseDom produtosParaProdutosResponseDom(Produtos in){
        ProdutosResponseDom out = new ProdutosResponseDom();
        out.setId(in.getId());
        //out.setCodigoProduto(in.getCodigoProduto());
        out.setDescricao(in.getDescricao());
        //out.setNome(in.getNome());
        out.setImagemPequena(in.getImagemPequena());
        //out.setImagemGrande(in.getImagemGrande());
        //out.setCreatedAt(in.getCreatedAt());
        out.setPreco(!in.getPrecos().isEmpty() ? in.getPrecos().get(0).getPreco() : 0.0);
        //out.setPreco(in.getPreco());

        return out;
    }
}
