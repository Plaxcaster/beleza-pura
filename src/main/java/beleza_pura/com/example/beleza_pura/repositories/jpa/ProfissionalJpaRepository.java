package beleza_pura.com.example.beleza_pura.repositories.jpa;

import beleza_pura.com.example.beleza_pura.repositories.jpa.jpaEntities.ProfissionalTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface ProfissionalJpaRepository extends JpaRepository<ProfissionalTableEntity, UUID> {

}
