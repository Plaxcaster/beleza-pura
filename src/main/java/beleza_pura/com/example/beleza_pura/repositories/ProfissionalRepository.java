package beleza_pura.com.example.beleza_pura.repositories;

import beleza_pura.com.example.beleza_pura.entities.Profissional;
import beleza_pura.com.example.beleza_pura.repositories.jpa.ProfissionalJpaRepository;
import beleza_pura.com.example.beleza_pura.repositories.jpa.jpaEntities.EspecialidadeTableEntity;
import beleza_pura.com.example.beleza_pura.repositories.jpa.jpaEntities.ProfissionalTableEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class ProfissionalRepository {

    private final ProfissionalJpaRepository repository;

    public ProfissionalRepository(ProfissionalJpaRepository repository) {
        this.repository = repository;
    }

    public Profissional cadastrar(Profissional profissional) {

        ProfissionalTableEntity profissionalTable = new ProfissionalTableEntity(profissional);
        profissionalTable.setId(UUID.randomUUID());
        profissionalTable.setEspecialidades(Set.of());

        return repository.save(profissionalTable).toProfissionalEntidade();
    }

    public Profissional buscaPorId(UUID id) {
        return repository.findById(id).get().toProfissionalEntidade();
    }

    public Profissional salvar(Profissional profissional) {
        ProfissionalTableEntity profissionaTable = new ProfissionalTableEntity(profissional);

        Set<EspecialidadeTableEntity> especialidadesTable = new HashSet<>();
        profissional.getEspecialidades()
                .forEach(especialidade -> especialidadesTable.add(new EspecialidadeTableEntity(especialidade)));
        profissionaTable.setEspecialidades(especialidadesTable);

        return repository.save(profissionaTable).toProfissionalEntidade();
    }

}
