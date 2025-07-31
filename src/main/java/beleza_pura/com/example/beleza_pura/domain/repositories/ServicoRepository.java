package beleza_pura.com.example.beleza_pura.domain.repositories;

import beleza_pura.com.example.beleza_pura.domain.models.Servico;
import java.util.Optional;

public interface ServicoRepository {
    Servico save(Servico servico);
    Optional<Servico> findById(Long id);
}