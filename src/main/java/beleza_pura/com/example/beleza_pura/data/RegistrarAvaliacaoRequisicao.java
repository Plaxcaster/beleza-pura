package beleza_pura.com.example.beleza_pura.data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class RegistrarAvaliacaoRequisicao {
    private UUID estabelecimentoId;
    private UUID profissionalId;

    @Min(1) @Max(10)
    private int nota;

    @Size(max = 500)
    private String comentario;
}