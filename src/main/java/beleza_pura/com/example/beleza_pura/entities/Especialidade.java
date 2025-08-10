package beleza_pura.com.example.beleza_pura.entities;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class Especialidade {
    private UUID id;
    private String nome;

}