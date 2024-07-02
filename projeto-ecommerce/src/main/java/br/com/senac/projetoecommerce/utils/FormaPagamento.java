package br.com.senac.projetoecommerce.utils;

public enum FormaPagamento {
    CARTAO,
    PIX,
    BOLETO;

    public String toUpperCase() {
        return this.name().toUpperCase();
    }
}
