package beleza_pura.com.example.beleza_pura.repositories.jpa;

import beleza_pura.com.example.beleza_pura.repositories.jpa.jpaEntities.EstabelecimentoTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EstabelecimentoJpaRepository extends JpaRepository<EstabelecimentoTableEntity, UUID> {

}
