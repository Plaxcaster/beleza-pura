package beleza_pura.com.example.beleza_pura.domain.repositories;

import beleza_pura.com.example.beleza_pura.domain.models.Estabelecimento;
import java.util.Optional;

public interface EstabelecimentoRepository {
    Estabelecimento save(Estabelecimento estabelecimento);
    Optional<Estabelecimento> findById(Long id);
}