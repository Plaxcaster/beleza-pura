package beleza_pura.com.example.beleza_pura.infrastructure.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import beleza_pura.com.example.beleza_pura.application.dtos.NovoProfissionalDTO;
import beleza_pura.com.example.beleza_pura.application.dtos.ProfissionalDTO;
import beleza_pura.com.example.beleza_pura.application.services.ProfissionalService;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    public ProfissionalController(ProfissionalService profissionalService) {
        this.profissionalService = profissionalService;
    }

    @PostMapping
    public ResponseEntity<ProfissionalDTO> registrarProfissional(
            @RequestBody NovoProfissionalDTO dto) {
        ProfissionalDTO profissionalDTO = profissionalService.registrarProfissional(dto);
        return ResponseEntity.ok(profissionalDTO);
    }
}