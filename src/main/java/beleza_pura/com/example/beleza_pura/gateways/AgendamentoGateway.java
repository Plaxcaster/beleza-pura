package beleza_pura.com.example.beleza_pura.gateways;

import beleza_pura.com.example.beleza_pura.data.MarcarAgendamentoRequisicao;
import beleza_pura.com.example.beleza_pura.data.MarcarAgendamentoResposta;
import beleza_pura.com.example.beleza_pura.usecases.AgendamentoUsecase;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoGateway {

    private final AgendamentoUsecase agendamentoUsecase;

    public AgendamentoGateway(AgendamentoUsecase agendamentoUsecase) {
        this.agendamentoUsecase = agendamentoUsecase;
    }

    @Operation(summary = "Marcar um novo agendamento", tags = "Agendamento")
    @PostMapping
    public ResponseEntity<MarcarAgendamentoResposta> marcarAgendamento(
            @RequestBody MarcarAgendamentoRequisicao requisicao) {
        try {
            return ResponseEntity.ok()
                    .body(new MarcarAgendamentoResposta(
                            agendamentoUsecase.marcarAgendamento(requisicao)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}