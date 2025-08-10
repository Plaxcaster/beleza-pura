package beleza_pura.com.example.beleza_pura.usecases;

import beleza_pura.com.example.beleza_pura.data.*;
import beleza_pura.com.example.beleza_pura.entities.*;
import beleza_pura.com.example.beleza_pura.repositories.EspecialidadeRepository;
import beleza_pura.com.example.beleza_pura.repositories.EstabelecimentoRepository;
import beleza_pura.com.example.beleza_pura.repositories.ProfissionalRepository;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CadastroUsecase {

    public CadastroUsecase(ProfissionalRepository repositoryProfissional,
            EspecialidadeRepository repositoryEspecialidade, EstabelecimentoRepository repositoryEstabelecimento) {
        this.repositoryProfissional = repositoryProfissional;
        this.repositoryEspecialidade = repositoryEspecialidade;
        this.repositoryEstabelecimento = repositoryEstabelecimento;
    }

    private final ProfissionalRepository repositoryProfissional;
    private final EspecialidadeRepository repositoryEspecialidade;
    private final EstabelecimentoRepository repositoryEstabelecimento;

    public Profissional cadastrarProfissional(CadastrarProfissionalRequisicao requisicao) {

        HorarioAtendimento horario = new HorarioAtendimento(converteHorario(requisicao.getHorarioAbertura()),
                converteHorario(requisicao.getHorarioFechamento()));

        Profissional profissional = Profissional.builder().especialidades(List.of()).nome(requisicao.getNome())
                .horario(horario).build();
        return repositoryProfissional.cadastrar(profissional);
    }

    public Especialidade cadastrarEspecialidade(CadastrarEspecialidadeRequisicao requisicao) {

        Especialidade especialidade = Especialidade.builder().nome(requisicao.getNome())
                .build();

        return repositoryEspecialidade.cadastrar(especialidade);
    }

    public Profissional vincularEspecialidades(VincularEspecialidadeRequisicao requisicao) {

        Profissional profissional = repositoryProfissional.buscaPorId(requisicao.getIdProfissional());

        List<Especialidade> especialidades = new ArrayList<>();

        requisicao.getListaEspecialidades()
                .forEach(idEspecialidade -> especialidades.add(repositoryEspecialidade.buscaPorId(idEspecialidade)));

        profissional.setEspecialidades(especialidades);
        return repositoryProfissional.salvar(profissional);
    }

    public Estabelecimento cadastrarEstabelecimento(CadastrarEstabelecimentoRequisicao requisicao) {

        HorarioAtendimento horario = new HorarioAtendimento(converteHorario(requisicao.getHorarioAbertura()),
                converteHorario(requisicao.getHorarioFechamento()));

        Estabelecimento estabelecimento = Estabelecimento.builder().endereco(requisicao.getEndereco())
                .nome(requisicao.getNome()).horarioAtendimento(horario).profissionais(Set.of()).build();
        return repositoryEstabelecimento.cadastrar(estabelecimento);
    }

    public Estabelecimento vincularEstabelecimento(VincularEstabelecimentoRequisicao requisicao) {
        Estabelecimento estabelecimento = repositoryEstabelecimento.buscaPorId(requisicao.getIdEstabelecimento());

        Set<Profissional> profissionais = new HashSet<>();

        requisicao.getListaProfissionais()
                .forEach(idProfissional -> profissionais.add(repositoryProfissional.buscaPorId(idProfissional)));

        estabelecimento.setProfissionais(profissionais);                

        return repositoryEstabelecimento.salvar(estabelecimento);
    }

    private LocalTime converteHorario(String horario) {
        try {
            return LocalTime.parse(horario);
        } catch (Exception e) {
            return LocalTime.MIDNIGHT;
        }
    }
}
