
package beleza_pura.com.example.beleza_pura.data;

import beleza_pura.com.example.beleza_pura.entities.Profissional;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class RespostaConsultaProfissional {
    private String nome;
    private String horarioInicio;
    private String horarioFim;
    private Set<EspecialidadeDto> especialidades;

    public RespostaConsultaProfissional(Profissional profissional) {
        this.nome = profissional.getNome();
        this.horarioInicio = profissional.getHorario().getHorarioInicio().toString();
        this.horarioFim = profissional.getHorario().getHorarioFim().toString();
        this.especialidades = profissional.getEspecialidades().stream()
                .map(especialidade -> new EspecialidadeDto(especialidade)).collect(Collectors.toSet());
    }

}
