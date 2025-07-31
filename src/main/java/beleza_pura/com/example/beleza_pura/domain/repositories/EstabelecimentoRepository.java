package beleza_pura.com.example.beleza_pura.domain.repositories;


import java.util.Optional;

import beleza_pura.com.example.beleza_pura.domain.models.Estabelecimento;
import org.springframework.stereotype.Repository;

@Repository
public interface EstabelecimentoRepository {
    Estabelecimento salvar(Estabelecimento estabelecimento);

    Optional<Estabelecimento> buscarPorId(Long id);

    void deletar(Long id);
}