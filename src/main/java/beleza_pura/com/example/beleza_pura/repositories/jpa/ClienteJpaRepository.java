package beleza_pura.com.example.beleza_pura.repositories.jpa;

import beleza_pura.com.example.beleza_pura.repositories.jpa.jpa_entities.ClienteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface ClienteJpaRepository extends JpaRepository<ClienteJpaEntity, UUID> {
}
