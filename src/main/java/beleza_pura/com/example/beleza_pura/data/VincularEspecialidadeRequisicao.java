package beleza_pura.com.example.beleza_pura.data;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class VincularEspecialidadeRequisicao {

    private UUID idProfissional;
    private List<UUID> listaEspecialidades;
}
