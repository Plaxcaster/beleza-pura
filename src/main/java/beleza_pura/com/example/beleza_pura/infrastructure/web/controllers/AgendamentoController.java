package beleza_pura.com.example.beleza_pura.infrastructure.web.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import beleza_pura.com.example.beleza_pura.application.dtos.NovoAgendamentoDTO;
import beleza_pura.com.example.beleza_pura.application.services.AgendamentoService;
import beleza_pura.com.example.beleza_pura.domain.models.Agendamento;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {
    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    @PostMapping
    public Agendamento criar(@RequestBody NovoAgendamentoDTO dto) {
        return service.criarAgendamento(dto);
    }
}