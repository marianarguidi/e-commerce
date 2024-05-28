package br.com.senac.projetoecommerce.useCases.carrinho;

import br.com.senac.projetoecommerce.entitys.Carrinho;
import br.com.senac.projetoecommerce.useCases.carrinho.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (!carrinhoRepository.existsById(1L)) {
            Carrinho carrinho = new Carrinho();
            carrinho.setId(1L);
            carrinho.setValorTotal(0.0);
            carrinho.setQuantidadeProdutos(0);
            carrinho.setClienteId(1L); // Exemplo de ID do cliente
            carrinhoRepository.save(carrinho);
        }
    }
}