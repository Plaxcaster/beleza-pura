package beleza_pura.com.example.beleza_pura.gateways;

import beleza_pura.com.example.beleza_pura.data.*;
import beleza_pura.com.example.beleza_pura.entities.Cliente;
import beleza_pura.com.example.beleza_pura.entities.Especialidade;
import beleza_pura.com.example.beleza_pura.entities.Estabelecimento;
import beleza_pura.com.example.beleza_pura.entities.Profissional;
import beleza_pura.com.example.beleza_pura.usecases.CadastroUsecase;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/cadastro")
public class CadastroGateway {

    private final CadastroUsecase cadastroUsecase;

    public CadastroGateway(CadastroUsecase usecase) {
        this.cadastroUsecase = usecase;
    }

    @Operation(summary = "Cadastrar um profissional", tags = "Profissional")
    @PostMapping("/profissional")
    public ResponseEntity<Profissional> cadastrarProfissional(@RequestBody CadastrarProfissionalRequisicao requisicao) {
        try {
            return Optional.ofNullable(cadastroUsecase.cadastrarProfissional(requisicao))
                    .map(resultado -> ResponseEntity.ok()
                            .body(resultado))
                    .orElseGet(() -> ResponseEntity.internalServerError().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Cadastrar uma especialidade de trabalho", tags = "Profissional")
    @PostMapping("/especialidade")
    public ResponseEntity<Especialidade> cadastrarEspecialidade(
            @RequestBody CadastrarEspecialidadeRequisicao requisicao) {
        try {
            return Optional.ofNullable(cadastroUsecase.cadastrarEspecialidade(requisicao))
                    .map(resultado -> ResponseEntity.ok()
                            .body(resultado))
                    .orElseGet(() -> ResponseEntity.internalServerError().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Cadastrar um novo cliente", tags = "Cliente")
    @PostMapping("/cliente")
    public ResponseEntity<Cliente> cadastrarEspecialidade(
            @RequestBody CadastrarClienteRequisicao requisicao) {
        try {
            return Optional.ofNullable(cadastroUsecase.cadastrarCliente(requisicao))
                    .map(resultado -> ResponseEntity.ok()
                            .body(resultado))
                    .orElseGet(() -> ResponseEntity.internalServerError().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Vincular uma especialidade a um profissional", tags = "Profissional")
    @PostMapping("/vincular_especialidade")
    public ResponseEntity<Profissional> vincularEspecialidade(@RequestBody VincularEspecialidadeRequisicao requisicao) {
        try {
            return Optional.ofNullable(cadastroUsecase.vincularEspecialidades(requisicao))
                    .map(resultado -> ResponseEntity.ok()
                            .body(resultado))
                    .orElseGet(() -> ResponseEntity.internalServerError().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @Operation(summary = "Vincular um Profissional a um Estabelecimento", tags = "Estabelecimento")
    @PostMapping("/vincular_estabelecimento")
    public ResponseEntity<Estabelecimento> vincularEstabelecimento(@RequestBody VincularEstabelecimentoRequisicao requisicao) {
        try {
            return Optional.ofNullable(cadastroUsecase.vincularEstabelecimento(requisicao))
                    .map(resultado -> ResponseEntity.ok()
                            .body(resultado))
                    .orElseGet(() -> ResponseEntity.internalServerError().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}