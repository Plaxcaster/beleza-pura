package beleza_pura.com.example.beleza_pura.infrastructure.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import beleza_pura.com.example.beleza_pura.domain.models.Disponibilidade;
import beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities.ProfissionalDisponibilidade;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface DisponibilidadeMapper {

    @Mapping(target = "dia", source = "domain", qualifiedByName = "parseDiaSemana")
    @Mapping(target = "horario.inicio", source = "domain.horaInicio")
    @Mapping(target = "horario.fim", source = "domain.horaFim")
    @Mapping(target = "profissional", ignore = true)
    ProfissionalDisponibilidade toEntity(Disponibilidade domain);

    @Named("parseDiaSemana")
    default DayOfWeek parseDiaSemana(Disponibilidade domain) {
        // Implement your logic to convert diasTrabalho to DayOfWeek
        // This is a simple example - adjust based on your actual data format
        return DayOfWeek.valueOf(domain.getDiasTrabalho().toUpperCase());
    }

    default Set<ProfissionalDisponibilidade> toEntities(Disponibilidade domain) {
        // Implement logic to convert multiple days if needed
        Set<ProfissionalDisponibilidade> entities = new HashSet<>();
        entities.add(toEntity(domain));
        return entities;
    }

    @Mapping(target = "diasTrabalho", source = "dia")
    @Mapping(target = "horaInicio", source = "horario.inicio")
    @Mapping(target = "horaFim", source = "horario.fim")
    Disponibilidade toDomain(ProfissionalDisponibilidade entity);
}