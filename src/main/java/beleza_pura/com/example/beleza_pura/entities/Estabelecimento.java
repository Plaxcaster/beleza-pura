package beleza_pura.com.example.beleza_pura.entities;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Estabelecimento {
    private UUID id;
    private String nome;
    private String endereco;
    private Set<Profissional> profissionais;
    private HorarioAtendimento horarioAtendimento;
}
