package beleza_pura.com.example.beleza_pura.repositories.jpa;

import beleza_pura.com.example.beleza_pura.repositories.jpa.jpaEntities.AvaliacaoTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AvaliacaoJpaRepository extends JpaRepository<AvaliacaoTableEntity, UUID> {
}