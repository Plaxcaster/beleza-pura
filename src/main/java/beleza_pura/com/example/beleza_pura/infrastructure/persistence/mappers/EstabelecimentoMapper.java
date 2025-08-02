package beleza_pura.com.example.beleza_pura.infrastructure.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import beleza_pura.com.example.beleza_pura.domain.models.Estabelecimento;
import beleza_pura.com.example.beleza_pura.domain.models.HorarioFuncionamento;
import beleza_pura.com.example.beleza_pura.domain.models.Profissional;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.EstabelecimentoJpaEntity;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.HorarioFuncionamentoEmbeddable;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.ProfissionalJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {ProfissionalMapper.class})
public abstract class EstabelecimentoMapper {
    @Autowired
    protected ProfissionalMapper profissionalMapper;

    @Mapping(target = "horarioFuncionamento", source = "horarioFuncionamento", qualifiedByName = "toHorarioEmbeddable")
    @Mapping(target = "profissionais", ignore = true)
    public abstract EstabelecimentoJpaEntity toEntity(Estabelecimento domain);

    @Mapping(target = "horarioFuncionamento", source = "horarioFuncionamento", qualifiedByName = "toHorarioDomain")
    @Mapping(target = "profissionais", ignore = true)
    public abstract Estabelecimento toDomain(EstabelecimentoJpaEntity entity);

    @Named("toHorarioEmbeddable")
    public HorarioFuncionamentoEmbeddable toHorarioEmbeddable(HorarioFuncionamento horario) {
        if (horario == null) return null;
        HorarioFuncionamentoEmbeddable embeddable = new HorarioFuncionamentoEmbeddable();
        embeddable.setAbertura(horario.getAbertura());
        embeddable.setFechamento(horario.getFechamento());
        return embeddable;
    }

    @Named("toHorarioDomain")
    public HorarioFuncionamento toHorarioDomain(HorarioFuncionamentoEmbeddable embeddable) {
        if (embeddable == null) return null;
        HorarioFuncionamento horario = new HorarioFuncionamento();
        horario.setAbertura(embeddable.getAbertura());
        horario.setFechamento(embeddable.getFechamento());
        return horario;
    }

    @AfterMapping
    public void afterMapping(EstabelecimentoJpaEntity entity, @MappingTarget Estabelecimento domain) {
        if (entity.getProfissionais() != null) {
            Set<Profissional> profissionais = profissionalMapper.toDomainSetWithoutEstabelecimento(
                    entity.getProfissionais()
            );
            domain.setProfissionais(profissionais);

            // Set back-reference safely
            for (Profissional p : profissionais) {
                p.setEstabelecimento(domain);
            }
        }
    }
}