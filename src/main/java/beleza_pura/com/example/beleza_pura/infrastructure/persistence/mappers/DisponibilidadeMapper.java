package beleza_pura.com.example.beleza_pura.infrastructure.persistence.mappers;

import beleza_pura.com.example.beleza_pura.application.dtos.NovoProfissionalDTO;
import beleza_pura.com.example.beleza_pura.application.dtos.ProfissionalDTO;
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

    default Disponibilidade toDomain(NovoProfissionalDTO.DisponibilidadeRequest request) {
        Disponibilidade domain = new Disponibilidade();
        domain.setDiasTrabalho(request.diaSemana().toString());
        domain.setHoraInicio(request.horaInicio().toString());
        domain.setHoraFim(request.horaFim().toString());
        return domain;
    }

    default ProfissionalDTO.DisponibilidadeResponse toResponse(Disponibilidade domain) {
        return new ProfissionalDTO.DisponibilidadeResponse(
                DayOfWeek.valueOf(domain.getDiasTrabalho()),
                LocalTime.parse(domain.getHoraInicio()),
                LocalTime.parse(domain.getHoraFim())
        );
    }
}