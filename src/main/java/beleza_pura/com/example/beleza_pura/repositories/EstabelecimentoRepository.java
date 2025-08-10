package beleza_pura.com.example.beleza_pura.repositories;

import beleza_pura.com.example.beleza_pura.entities.Estabelecimento;
import beleza_pura.com.example.beleza_pura.repositories.jpa.EstabelecimentoJpaRepository;
import beleza_pura.com.example.beleza_pura.repositories.jpa.jpaEntities.EstabelecimentoTableEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.UUID;

@Component
public class EstabelecimentoRepository {

    private final EstabelecimentoJpaRepository repository;

    /**
     * @param repository
     */
    public EstabelecimentoRepository(EstabelecimentoJpaRepository repository) {
        this.repository = repository;
    }

    public Estabelecimento cadastrar(Estabelecimento estabelecimento) {
        EstabelecimentoTableEntity estabelecimentoTable = new EstabelecimentoTableEntity(estabelecimento);
        estabelecimentoTable.setId(UUID.randomUUID());
        estabelecimentoTable.setProfissionais(new HashSet<>());

        return repository.save(estabelecimentoTable).toEstabelecimento();
    }

    public Estabelecimento buscaPorId(UUID idEstabelecimento) {
        return repository.findById(idEstabelecimento).orElseThrow().toEstabelecimento();
    }

    public Estabelecimento salvar(Estabelecimento estabelecimento) {
        EstabelecimentoTableEntity estabelecimentoTable = new EstabelecimentoTableEntity(estabelecimento);
        return repository.save(estabelecimentoTable).toEstabelecimento();
    }

}