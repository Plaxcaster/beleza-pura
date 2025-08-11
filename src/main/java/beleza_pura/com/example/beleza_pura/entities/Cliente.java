package beleza_pura.com.example.beleza_pura.entities;

import lombok.*;

import java.util.UUID;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private UUID id;
    private String nome;
    private String email;
    private String telefone;

}
