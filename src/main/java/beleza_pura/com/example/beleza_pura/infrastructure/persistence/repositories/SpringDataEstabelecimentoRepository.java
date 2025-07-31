// src/main/java/beleza_pura/com/example/beleza_pura/infrastructure/persistence/repositories/SpringDataEstabelecimentoRepository.java
package beleza_pura.com.example.beleza_pura.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.EstabelecimentoJpaEntity;

public interface SpringDataEstabelecimentoRepository
        extends JpaRepository<EstabelecimentoJpaEntity, Long> {
}