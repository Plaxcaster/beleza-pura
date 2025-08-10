package beleza_pura.com.example.beleza_pura.gateways;

import beleza_pura.com.example.beleza_pura.data.RespostaConsultaEstabelecimento;
import beleza_pura.com.example.beleza_pura.data.RespostaConsultaProfissional;
import beleza_pura.com.example.beleza_pura.usecases.ConsultaUsecase;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/consulta")
public class ConsultaGateway {

    private final ConsultaUsecase usecase;

    public ConsultaGateway(ConsultaUsecase usecase) {
        this.usecase = usecase;
    }

    @Operation(summary = "Consultar dados de um estabelecimento a partir de um ID", tags = "Estabelecimento")
    @GetMapping("estabelecimento/{id_estabelecimento}")
    public ResponseEntity<RespostaConsultaEstabelecimento> estabelecimentoPorId(
            @PathVariable String id_estabelecimento) {
        try {
            return Optional.ofNullable(usecase.consultaEstabelecimentoPorId(id_estabelecimento))
                    .map(resultado -> ResponseEntity.ok()
                            .body(new RespostaConsultaEstabelecimento(resultado)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Consultar dados de um Profissional a partir de um ID", tags = "Profissional")
    @GetMapping("profissional/{id_profissional}")
    public ResponseEntity<RespostaConsultaProfissional> profissionalPorId(
            @PathVariable String id_profissional) {
        try {
            return Optional.ofNullable(usecase.consultaProfissionalPorId(id_profissional))
                    .map(resultado -> ResponseEntity.ok()
                            .body(new RespostaConsultaProfissional(resultado)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
