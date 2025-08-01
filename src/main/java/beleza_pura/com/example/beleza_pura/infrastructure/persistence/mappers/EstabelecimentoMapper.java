package beleza_pura.com.example.beleza_pura.infrastructure.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import beleza_pura.com.example.beleza_pura.domain.models.Estabelecimento;
import beleza_pura.com.example.beleza_pura.domain.models.HorarioFuncionamento;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.EstabelecimentoJpaEntity;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.HorarioFuncionamentoEmbeddable;

@Mapper(componentModel = "spring")
public interface EstabelecimentoMapper {

    @Mapping(target = "horarioFuncionamento", source = "horarioFuncionamento", qualifiedByName = "toHorarioEmbeddable")
    EstabelecimentoJpaEntity toEntity(Estabelecimento domain);

    @Mapping(target = "horarioFuncionamento", source = "horarioFuncionamento", qualifiedByName = "toHorarioDomain")
    Estabelecimento toDomain(EstabelecimentoJpaEntity entity);

    @Named("toHorarioEmbeddable")
    default HorarioFuncionamentoEmbeddable toHorarioEmbeddable(HorarioFuncionamento horario) {
        if (horario == null) return null;
        HorarioFuncionamentoEmbeddable embeddable = new HorarioFuncionamentoEmbeddable();
        embeddable.setAbertura(horario.getAbertura());
        embeddable.setFechamento(horario.getFechamento());
        return embeddable;
    }

    @Named("toHorarioDomain")
    default HorarioFuncionamento toHorarioDomain(HorarioFuncionamentoEmbeddable embeddable) {
        if (embeddable == null) return null;
        HorarioFuncionamento horario = new HorarioFuncionamento();
        horario.setAbertura(embeddable.getAbertura());
        horario.setFechamento(embeddable.getFechamento());
        return horario;
    }
}