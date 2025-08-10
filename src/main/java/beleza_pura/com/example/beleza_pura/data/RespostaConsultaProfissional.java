
package beleza_pura.com.example.beleza_pura.data;

import beleza_pura.com.example.beleza_pura.entities.Profissional;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class RespostaConsultaProfissional {
    private String nome;
    private String horarioAbertura;
    private String horarioFechamento;
    private Set<EspecialidadeDto> especialidades;

    public RespostaConsultaProfissional(Profissional profissional) {
        this.nome = profissional.getNome();
        this.horarioAbertura = profissional.getHorario().getAbertura().toString();
        this.horarioFechamento = profissional.getHorario().getFechamento().toString();
        this.especialidades = profissional.getEspecialidades().stream()
                .map(especialidade -> new EspecialidadeDto(especialidade)).collect(Collectors.toSet());
    }

}
