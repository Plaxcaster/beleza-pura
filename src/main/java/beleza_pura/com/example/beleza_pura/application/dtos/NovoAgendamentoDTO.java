// src/main/java/beleza_pura/com/example/beleza_pura/application/dtos/NovoAgendamentoDTO.java
package beleza_pura.com.example.beleza_pura.application.dtos;

import java.time.LocalDateTime;

public record NovoAgendamentoDTO(
    Long clienteId,
    Long profissionalId,
    Long servicoId,
    LocalDateTime dataHora
) {}