package beleza_pura.com.example.beleza_pura.entities;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {
    private UUID id;
    private Estabelecimento estabelecimento;
    private Profissional profissional;
    private int nota; // 1-10
    private String comentario;
}