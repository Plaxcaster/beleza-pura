package beleza_pura.com.beleza_pura.domain;


import lombok.*;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profissional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @ElementCollection
    @CollectionTable(name = "profissional_especialidades", joinColumns = @JoinColumn(name = "profissional_id"))
    @Column(name = "especialidade")
    private Set<String> especialidades = new HashSet<>();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estabelecimento_id", nullable = false)
    private Estabelecimento estabelecimento;
    
    @Embedded
    private Disponibilidade disponibilidade;
    
    // Tarifas seriam definidas no Servico
}

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Disponibilidade {
    private String diasTrabalho; // Ex: "SEG,QUA,SEX"
    private String horaInicio;   // Ex: "09:00"
    private String horaFim;      // Ex: "18:00"
}