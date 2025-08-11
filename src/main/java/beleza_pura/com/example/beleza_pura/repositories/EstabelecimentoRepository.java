package beleza_pura.com.example.beleza_pura.repositories;

import beleza_pura.com.example.beleza_pura.entities.Estabelecimento;
import beleza_pura.com.example.beleza_pura.repositories.jpa.EstabelecimentoJpaRepository;
import beleza_pura.com.example.beleza_pura.repositories.jpa.jpaEntities.EstabelecimentoJpaEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Component
public class EstabelecimentoRepository {

    private final EstabelecimentoJpaRepository repository;

    public EstabelecimentoRepository(EstabelecimentoJpaRepository repository) {
        this.repository = repository;
    }

    public Estabelecimento cadastrar(Estabelecimento estabelecimento) {
        EstabelecimentoJpaEntity estabelecimentoTable = new EstabelecimentoJpaEntity(estabelecimento);
        estabelecimentoTable.setId(UUID.randomUUID());
        estabelecimentoTable.setProfissionais(new HashSet<>());

        return repository.save(estabelecimentoTable).toEstabelecimento();
    }

    public Optional<Estabelecimento> buscaPorId(UUID idEstabelecimento) {
        return repository.findById(idEstabelecimento)
                .map(EstabelecimentoJpaEntity::toEstabelecimento);
    }

    public Estabelecimento salvar(Estabelecimento estabelecimento) {
        EstabelecimentoJpaEntity estabelecimentoTable = new EstabelecimentoJpaEntity(estabelecimento);
        return repository.save(estabelecimentoTable).toEstabelecimento();
    }
}