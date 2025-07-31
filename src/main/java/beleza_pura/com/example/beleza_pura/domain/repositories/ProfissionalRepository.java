package beleza_pura.com.example.beleza_pura.domain.repositories;

import beleza_pura.com.example.beleza_pura.domain.models.Profissional;
import java.util.Optional;

public interface ProfissionalRepository {
    Profissional save(Profissional profissional);
    Optional<Profissional> findById(Long id);
}