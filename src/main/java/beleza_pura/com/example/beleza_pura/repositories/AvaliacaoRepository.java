package beleza_pura.com.example.beleza_pura.repositories;

import beleza_pura.com.example.beleza_pura.entities.Avaliacao;
import beleza_pura.com.example.beleza_pura.repositories.jpa.AvaliacaoJpaRepository;
import beleza_pura.com.example.beleza_pura.repositories.jpa.jpa_entities.AvaliacaoJpaEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AvaliacaoRepository {
    private final AvaliacaoJpaRepository repository;

    public AvaliacaoRepository(AvaliacaoJpaRepository repository) {
        this.repository = repository;
    }

    public Avaliacao salvar(Avaliacao avaliacao) {
        AvaliacaoJpaEntity entity = new AvaliacaoJpaEntity(avaliacao);
        entity = repository.save(entity);
        return entity.toAvaliacao();
    }

    public List<Avaliacao> listarPorEstabelecimento(UUID estabelecimentoId) {
        return repository.findByEstabelecimentoId(estabelecimentoId).stream()
                .map(AvaliacaoJpaEntity::toAvaliacao)
                .toList();
    }

    public List<Avaliacao> listarPorProfissional(UUID profissionalId) {
        return repository.findByProfissionalId(profissionalId).stream()
                .map(AvaliacaoJpaEntity::toAvaliacao)
                .toList();
    }
}