package beleza_pura.com.example.beleza_pura.usecases;

import beleza_pura.com.example.beleza_pura.data.*;
import beleza_pura.com.example.beleza_pura.entities.*;
import beleza_pura.com.example.beleza_pura.exceptions.EntityNotFoundException;
import beleza_pura.com.example.beleza_pura.repositories.*;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CadastroUsecase {

    private final ProfissionalRepository repositoryProfissional;
    private final EspecialidadeRepository repositoryEspecialidade;
    private final EstabelecimentoRepository repositoryEstabelecimento;
    private final ClienteRepository repositoryCliente;

    public CadastroUsecase(ProfissionalRepository repositoryProfissional,
                           EspecialidadeRepository repositoryEspecialidade,
                           EstabelecimentoRepository repositoryEstabelecimento,
                           ClienteRepository repositoryCliente) {
        this.repositoryProfissional = repositoryProfissional;
        this.repositoryEspecialidade = repositoryEspecialidade;
        this.repositoryEstabelecimento = repositoryEstabelecimento;
        this.repositoryCliente = repositoryCliente;
    }

    public Profissional cadastrarProfissional(CadastrarProfissionalRequisicao requisicao) {
        HorarioAtendimento horario = new HorarioAtendimento(
                converteHorario(requisicao.getHorarioInicio()),
                converteHorario(requisicao.getHorarioFim())
        );

        Profissional profissional = Profissional.builder()
                .especialidades(List.of())
                .nome(requisicao.getNome())
                .horario(horario)
                .tarifa(requisicao.getTarifa())
                .build();

        return repositoryProfissional.cadastrar(profissional);
    }

    public Cliente cadastrarCliente(CadastrarClienteRequisicao requisicao) {
        Cliente cliente = Cliente.builder()
                .nome(requisicao.getNome())
                .email(requisicao.getEmail())
                .telefone(requisicao.getTelefone())
                .build();

        return repositoryCliente.cadastrar(cliente);
    }

    public Especialidade cadastrarEspecialidade(CadastrarEspecialidadeRequisicao requisicao) {
        Especialidade especialidade = Especialidade.builder()
                .nome(requisicao.getNome())
                .build();

        return repositoryEspecialidade.cadastrar(especialidade);
    }

    public Profissional vincularEspecialidades(VincularEspecialidadeRequisicao requisicao) {
        Profissional profissional = repositoryProfissional.buscaPorId(requisicao.getIdProfissional())
                .orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado"));

        List<Especialidade> especialidades = requisicao.getListaEspecialidades().stream()
                .map(id -> repositoryEspecialidade.buscaPorId(id)
                        .orElseThrow(() -> new EntityNotFoundException("Especialidade não encontrada")))
                .collect(Collectors.toList());

        profissional.setEspecialidades(especialidades);
        return repositoryProfissional.salvar(profissional);
    }

    public Estabelecimento cadastrarEstabelecimento(CadastrarEstabelecimentoRequisicao requisicao) {
        HorarioAtendimento horario = new HorarioAtendimento(
                converteHorario(requisicao.getHorarioInicio()),
                converteHorario(requisicao.getHorarioFim())
        );

        Estabelecimento estabelecimento = Estabelecimento.builder()
                .endereco(requisicao.getEndereco())
                .nome(requisicao.getNome())
                .horarioAtendimento(horario)
                .profissionais(Set.of())
                .build();

        return repositoryEstabelecimento.cadastrar(estabelecimento);
    }

    public Estabelecimento vincularEstabelecimento(VincularEstabelecimentoRequisicao requisicao) {
        Estabelecimento estabelecimento = repositoryEstabelecimento.buscaPorId(requisicao.getIdEstabelecimento())
                .orElseThrow(() -> new EntityNotFoundException("Estabelecimento não encontrado"));

        Set<Profissional> profissionais = requisicao.getListaProfissionais().stream()
                .map(id -> repositoryProfissional.buscaPorId(id))
                .map(opt -> opt.orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado")))
                .collect(Collectors.toSet());

        estabelecimento.setProfissionais(profissionais);
        return repositoryEstabelecimento.salvar(estabelecimento);
    }

    private LocalTime converteHorario(String horario) {
        try {
            return LocalTime.parse(horario);
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato de horário inválido", e);
        }
    }
}