package beleza_pura.com.beleza_pura.domain;



import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profissional_id", nullable = false)
    private Profissional profissional;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servico_id", nullable = false)
    private Servico servico;
    
    @Column(nullable = false)
    private LocalDateTime dataHoraInicio;
    
    private LocalDateTime dataHoraFim; // Calculado a partir da duração
    
    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;
    
    // Integração com calendário
    private String eventoCalendarioId; // ID do Google Calendar/Outlook
}

enum StatusAgendamento {
    CONFIRMADO,
    CONCLUIDO,
    CANCELADO,
    NAO_COMPARECEU
}