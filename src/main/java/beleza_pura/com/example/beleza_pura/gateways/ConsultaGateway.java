package beleza_pura.com.example.beleza_pura.gateways;

import beleza_pura.com.example.beleza_pura.data.RespostaConsultaAgendamento;
import beleza_pura.com.example.beleza_pura.data.RespostaConsultaEstabelecimento;
import beleza_pura.com.example.beleza_pura.data.RespostaConsultaProfissional;
import beleza_pura.com.example.beleza_pura.entities.StatusAgendamento;
import beleza_pura.com.example.beleza_pura.usecases.ConsultaUsecase;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consulta")
public class ConsultaGateway {

    private final ConsultaUsecase usecase;

    public ConsultaGateway(ConsultaUsecase usecase) {
        this.usecase = usecase;
    }

    @Operation(summary = "Consultar dados de um estabelecimento a partir de um ID", tags = "Estabelecimento")
    @GetMapping("estabelecimento/{idEstabelecimento}")
    public ResponseEntity<RespostaConsultaEstabelecimento> estabelecimentoPorId(
            @PathVariable String idEstabelecimento) {
        try {
            return Optional.ofNullable(usecase.consultaEstabelecimentoPorId(idEstabelecimento))
                    .map(resultado -> ResponseEntity.ok()
                            .body(new RespostaConsultaEstabelecimento(resultado)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Consultar dados de um Profissional a partir de um ID", tags = "Profissional")
    @GetMapping("profissional/{idProfissional}")
    public ResponseEntity<RespostaConsultaProfissional> profissionalPorId(
            @PathVariable String idProfissional) {
        try {
            return Optional.ofNullable(usecase.consultaProfissionalPorId(idProfissional))
                    .map(resultado -> ResponseEntity.ok()
                            .body(new RespostaConsultaProfissional(resultado)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Consultar agendamentos por cliente", tags = "Agendamento")
    @GetMapping("/agendamentos/cliente/{clienteId}")
    public ResponseEntity<List<RespostaConsultaAgendamento>> agendamentosPorCliente(
            @PathVariable UUID clienteId) {
        try {
            List<RespostaConsultaAgendamento> agendamentos = usecase.consultarAgendamentosPorCliente(clienteId)
                    .stream()
                    .map(RespostaConsultaAgendamento::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(agendamentos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Consultar agendamentos por profissional", tags = "Agendamento")
    @GetMapping("/agendamentos/profissional/{profissionalId}")
    public ResponseEntity<List<RespostaConsultaAgendamento>> agendamentosPorProfissional(
            @PathVariable UUID profissionalId) {
        try {
            List<RespostaConsultaAgendamento> agendamentos = usecase.consultarAgendamentosPorProfissional(profissionalId)
                    .stream()
                    .map(RespostaConsultaAgendamento::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(agendamentos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Consultar agendamentos por status", tags = "Agendamento")
    @GetMapping("/agendamentos/status/{status}")
    public ResponseEntity<List<RespostaConsultaAgendamento>> agendamentosPorStatus(
            @PathVariable StatusAgendamento status) {
        try {
            List<RespostaConsultaAgendamento> agendamentos = usecase.consultarAgendamentosPorStatus(status)
                    .stream()
                    .map(RespostaConsultaAgendamento::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(agendamentos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Consultar agendamentos por cliente e status", tags = "Agendamento")
    @GetMapping("/agendamentos/cliente/{clienteId}/status/{status}")
    public ResponseEntity<List<RespostaConsultaAgendamento>> agendamentosPorClienteEStatus(
            @PathVariable UUID clienteId,
            @PathVariable StatusAgendamento status) {
        try {
            List<RespostaConsultaAgendamento> agendamentos = usecase.consultarAgendamentosPorClienteEStatus(clienteId, status)
                    .stream()
                    .map(RespostaConsultaAgendamento::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(agendamentos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Consultar agendamentos por profissional e status", tags = "Agendamento")
    @GetMapping("/agendamentos/profissional/{profissionalId}/status/{status}")
    public ResponseEntity<List<RespostaConsultaAgendamento>> agendamentosPorProfissionalEStatus(
            @PathVariable UUID profissionalId,
            @PathVariable StatusAgendamento status) {
        try {
            List<RespostaConsultaAgendamento> agendamentos = usecase.consultarAgendamentosPorProfissionalEStatus(profissionalId, status)
                    .stream()
                    .map(RespostaConsultaAgendamento::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(agendamentos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
