package beleza_pura.com.example.beleza_pura.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.ProfissionalJpaEntity;

public interface SpringDataProfissionalRepository
        extends JpaRepository<ProfissionalJpaEntity, Long> {
}