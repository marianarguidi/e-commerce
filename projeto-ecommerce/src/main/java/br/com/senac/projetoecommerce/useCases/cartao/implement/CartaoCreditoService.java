package br.com.senac.projetoecommerce.useCases.cartao.implement;

import br.com.senac.projetoecommerce.useCases.cartao.domains.CartaoCreditoRequestDom;
import br.com.senac.projetoecommerce.entitys.Cartao;
import br.com.senac.projetoecommerce.useCases.cartao.domains.CartaoCreditoResponseDom;
import br.com.senac.projetoecommerce.useCases.cartao.implement.CartaoCreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Service
public class CartaoCreditoService {
    @Autowired
    private CartaoCreditoRepository repository;

    public CartaoCreditoResponseDom salvarCartaoCredito(CartaoCreditoRequestDom requestDom) {
        // Aqui você pode adicionar lógica de validação dos dados do cartão
        if (!validarDadosCartao(requestDom)) {
            throw new IllegalArgumentException("Dados do cartão de crédito inválidos.");
        }

        Cartao cartao = new Cartao();
        cartao.setNumero(requestDom.getNumero());
        cartao.setTitular(requestDom.getTitular());
        cartao.setDataValidade(requestDom.getDataValidade());
        cartao.setCvv(requestDom.getCvv());
        cartao.setClienteId(requestDom.getClienteId());

        Cartao cartaoSalvo = repository.save(cartao);

        return converterParaResponseDom(cartaoSalvo);
    }

    private boolean validarDadosCartao(CartaoCreditoRequestDom requestDom) {
        return validarNumeroCartao(requestDom.getNumero()) &&
                validarDataValidade(requestDom.getDataValidade()) &&
                validarCvv(requestDom.getCvv());
    }

    private boolean validarNumeroCartao(String numeroCartao) {
        // Implementação do algoritmo de Luhn para validação do número do cartão
        int soma = 0;
        boolean alternar = false;
        for (int i = numeroCartao.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(numeroCartao.substring(i, i + 1));
            if (alternar) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            soma += n;
            alternar = !alternar;
        }
        return (soma % 10 == 0);
    }

    private boolean validarDataValidade(String dataValidade) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
            YearMonth data = YearMonth.parse(dataValidade, formatter);
            return data.isAfter(YearMonth.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean validarCvv(String cvv) {
        return cvv.matches("\\d{3,4}");
    }


    private CartaoCreditoResponseDom converterParaResponseDom(Cartao cartao) {
        CartaoCreditoResponseDom responseDom = new CartaoCreditoResponseDom();
        responseDom.setId(cartao.getId());
        responseDom.setNumero(cartao.getNumero());
        responseDom.setTitular(cartao.getTitular());
        responseDom.setDataValidade(cartao.getDataValidade());
        return responseDom;
    }
}