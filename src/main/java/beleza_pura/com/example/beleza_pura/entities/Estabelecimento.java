package beleza_pura.com.example.beleza_pura.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Estabelecimento {
    private UUID id;
    private String nome;
    private String endereco;
    private Set<Profissional> profissionais;
    private HorarioAtendimento horarioAtendimento;
}
