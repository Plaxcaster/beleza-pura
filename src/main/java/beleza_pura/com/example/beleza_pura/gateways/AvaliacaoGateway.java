package beleza_pura.com.example.beleza_pura.gateways;

import beleza_pura.com.example.beleza_pura.data.RegistrarAvaliacaoRequisicao;
import beleza_pura.com.example.beleza_pura.data.RegistrarAvaliacaoResposta;
import beleza_pura.com.example.beleza_pura.exceptions.EntityNotFoundException;
import beleza_pura.com.example.beleza_pura.usecases.AvaliacaoUsecase;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoGateway {

    private final AvaliacaoUsecase avaliacaoUsecase;

    public AvaliacaoGateway(AvaliacaoUsecase avaliacaoUsecase) {
        this.avaliacaoUsecase = avaliacaoUsecase;
    }

    @Operation(summary = "Registrar uma nova avaliação", tags = "Avaliação")
    @PostMapping
    public ResponseEntity<RegistrarAvaliacaoResposta> registrarAvaliacao(
            @RequestBody RegistrarAvaliacaoRequisicao requisicao) {
        try {
            return ResponseEntity.ok()
                    .body(new RegistrarAvaliacaoResposta(
                            avaliacaoUsecase.registrarAvaliacao(requisicao)));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}