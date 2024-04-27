package br.com.senac.projetoecommerce.useCases.enderecos.implement;

import br.com.senac.projetoecommerce.entitys.Enderecos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecosRepository extends JpaRepository<Enderecos, Long> {
    List<Enderecos> findByClientesId(Long idCliente);
}
