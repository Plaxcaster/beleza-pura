package beleza_pura.com.example.beleza_pura.repositories;

import beleza_pura.com.example.beleza_pura.entities.Profissional;
import beleza_pura.com.example.beleza_pura.repositories.jpa.ProfissionalJpaRepository;
import beleza_pura.com.example.beleza_pura.repositories.jpa.jpaEntities.EspecialidadeJpaEntity;
import beleza_pura.com.example.beleza_pura.repositories.jpa.jpaEntities.ProfissionalJpaEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Component
public class ProfissionalRepository {

    private final ProfissionalJpaRepository repository;

    public ProfissionalRepository(ProfissionalJpaRepository repository) {
        this.repository = repository;
    }

    public Profissional cadastrar(Profissional profissional) {

        ProfissionalJpaEntity profissionalTable = new ProfissionalJpaEntity(profissional);
        profissionalTable.setId(UUID.randomUUID());
        profissionalTable.setEspecialidades(Set.of());

        return repository.save(profissionalTable).toProfissionalEntidade();
    }

    public Optional<Profissional> buscaPorId(UUID id) {
        return repository.findById(id)
                .map(ProfissionalJpaEntity::toProfissionalEntidade);
    }

    public Profissional salvar(Profissional profissional) {
        ProfissionalJpaEntity profissionaTable = new ProfissionalJpaEntity(profissional);

        Set<EspecialidadeJpaEntity> especialidadesTable = new HashSet<>();
        profissional.getEspecialidades()
                .forEach(especialidade -> especialidadesTable.add(new EspecialidadeJpaEntity(especialidade)));
        profissionaTable.setEspecialidades(especialidadesTable);

        return repository.save(profissionaTable).toProfissionalEntidade();
    }



}
