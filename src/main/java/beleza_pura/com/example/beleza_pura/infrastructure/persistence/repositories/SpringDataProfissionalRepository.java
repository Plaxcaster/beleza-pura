package beleza_pura.com.example.beleza_pura.infrastructure.persistence.repositories;

import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.ProfissionalJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpringDataProfissionalRepository extends JpaRepository<ProfissionalJpaEntity, Long> {
    List<ProfissionalJpaEntity> findByEstabelecimentoId(Long estabelecimentoId);
}