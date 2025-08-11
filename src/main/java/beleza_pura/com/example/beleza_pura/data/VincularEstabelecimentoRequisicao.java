package beleza_pura.com.example.beleza_pura.data;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class VincularEstabelecimentoRequisicao {

    private UUID idEstabelecimento;
    private List<UUID> listaProfissionais;
}
