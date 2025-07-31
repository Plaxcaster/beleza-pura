// src/main/java/beleza_pura/com/example/beleza_pura/infrastructure/persistence/repositories/SpringDataServicoRepository.java
package beleza_pura.com.example.beleza_pura.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.ServicoJpaEntity;

public interface SpringDataServicoRepository
        extends JpaRepository<ServicoJpaEntity, Long> {
}