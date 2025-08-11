package beleza_pura.com.example.beleza_pura.data;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MarcarAgendamentoRequisicao {
    private UUID clienteId;
    private UUID profissionalId;
    private UUID especialidadeId;
    private LocalDateTime dataHora;
}