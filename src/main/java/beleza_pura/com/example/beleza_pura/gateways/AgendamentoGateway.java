package beleza_pura.com.example.beleza_pura.gateways;

import beleza_pura.com.example.beleza_pura.data.*;
import beleza_pura.com.example.beleza_pura.exceptions.AgendamentoOperationException;
import beleza_pura.com.example.beleza_pura.exceptions.EntityNotFoundException;
import beleza_pura.com.example.beleza_pura.usecases.AgendamentoUsecase;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Operation(summary = "Cancelar um agendamento", tags = "Agendamento")
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarAgendamento(
            @PathVariable Long id) {
        try {
            return ResponseEntity.ok()
                    .body(new MarcarAgendamentoResposta(
                            agendamentoUsecase.cancelarAgendamento(id)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (AgendamentoOperationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Listar agendamentos por cliente", tags = "Agendamento")
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<MarcarAgendamentoResposta>> listarPorCliente(
            @PathVariable UUID clienteId) {
        try {
            List<MarcarAgendamentoResposta> agendamentos = agendamentoUsecase
                    .listarAgendamentosPorCliente(clienteId).stream()
                    .map(MarcarAgendamentoResposta::new)
                    .toList(); // Replaced collect(Collectors.toList()) with toList()
            return ResponseEntity.ok(agendamentos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


}