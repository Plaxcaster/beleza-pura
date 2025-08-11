package beleza_pura.com.example.beleza_pura.repositories;

import beleza_pura.com.example.beleza_pura.entities.Especialidade;
import beleza_pura.com.example.beleza_pura.repositories.jpa.EspecialidadeJpaRepository;
import beleza_pura.com.example.beleza_pura.repositories.jpa.jpa_entities.EspecialidadeJpaEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class EspecialidadeRepository {

    public EspecialidadeRepository(EspecialidadeJpaRepository repository) {
        this.repository = repository;
    }

    private final EspecialidadeJpaRepository repository;

    public Especialidade cadastrar(Especialidade especialidade) {
        EspecialidadeJpaEntity especialidadeTable = new EspecialidadeJpaEntity(especialidade);
        especialidadeTable.setId(UUID.randomUUID());
        especialidadeTable = repository.save(especialidadeTable);
        return especialidadeTable.toEspecialidadeEntidade();
    }

    public Optional<Especialidade> buscaPorId(UUID idEspecialidade) {
        return repository.findById(idEspecialidade)
                .map(EspecialidadeJpaEntity::toEspecialidadeEntidade);
    }
}