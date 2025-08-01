package beleza_pura.com.example.beleza_pura.infrastructure.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import beleza_pura.com.example.beleza_pura.application.dtos.EstabelecimentoDTO;
import beleza_pura.com.example.beleza_pura.application.dtos.NovoEstabelecimentoDTO;
import beleza_pura.com.example.beleza_pura.application.services.EstabelecimentoService;

@RestController
@RequestMapping("/api/estabelecimentos")
public class EstabelecimentoController {

    private final EstabelecimentoService estabelecimentoService;

    public EstabelecimentoController(EstabelecimentoService estabelecimentoService) {
        this.estabelecimentoService = estabelecimentoService;
    }

    @PostMapping
    public ResponseEntity<EstabelecimentoDTO> registrarEstabelecimento(
            @RequestBody NovoEstabelecimentoDTO dto) {
        EstabelecimentoDTO response = estabelecimentoService.registrarEstabelecimento(dto);
        return ResponseEntity.ok(response);
    }
}