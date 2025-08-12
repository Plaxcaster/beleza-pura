package beleza_pura.com.example.beleza_pura.repositories.jpa;

import beleza_pura.com.example.beleza_pura.repositories.jpa.jpa_entities.AvaliacaoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AvaliacaoJpaRepository extends JpaRepository<AvaliacaoJpaEntity, UUID> {

    @Query("SELECT a FROM AvaliacaoJpaEntity a WHERE a.estabelecimento.id = :estabelecimentoId")
    List<AvaliacaoJpaEntity> findByEstabelecimentoId(@Param("estabelecimentoId") UUID estabelecimentoId);

    @Query("SELECT a FROM AvaliacaoJpaEntity a WHERE a.profissional.id = :profissionalId")
    List<AvaliacaoJpaEntity> findByProfissionalId(@Param("profissionalId") UUID profissionalId);
}