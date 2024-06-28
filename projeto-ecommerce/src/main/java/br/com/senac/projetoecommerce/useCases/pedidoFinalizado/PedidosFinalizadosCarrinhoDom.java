package br.com.senac.projetoecommerce.useCases.pedidoFinalizado;

public class PedidosFinalizadosCarrinhoDom {
    private Long produtoId;
    private int quantidade;
    private Double valorUnitario;

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
