package beleza_pura.com.example.beleza_pura.entities;

import java.util.Set;
import java.util.UUID;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Profissional {
    private UUID id;
    private String nome;
    private Set<Especialidade> especialidades;
    private Estabelecimento estabelecimento;
    private Disponibilidade disponibilidade;

    // Domain logic
    public boolean possuiEspecialidade(String especialidade) {
        return especialidades.contains(especialidade);
    }

}